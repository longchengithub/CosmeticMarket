package com.market.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.market.R;
import com.market.bean.OrderListResp;
import com.market.http.RetrofitHelper;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.activity.base.LoginActivity;
import com.market.view.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zdf on 2017/1/7.
 */

public class OrderMyActivity extends BaseCompatActivity {
    @BindView(R.id.one_month_order)
    TextView mOneMonthOrder;
    @BindView(R.id.cancel_order)
    TextView mCancelOrder;
    @BindView(R.id.iv_no_order)
    TextView mIvNoOrder;
    @BindView(R.id.framelayout_order_details)
    FrameLayout mFramelayoutOrderDetails;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.goto_shop)
    TextView mGotoShop;
    @BindView(R.id.empty_order)
    LinearLayout mEmptyOrder;

    @BindView(R.id.one_month_ago_order)
    TextView mOneMonthAgoOrder;
    @BindView(R.id.order_listview)
    ListView mOrderListview;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_my;
    }

    private List<OrderListResp.OrderListBean> mOrderListBeens;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mOrderListBeens = new ArrayList<>();
        //(String price, String orderId, String status, String time
        loadOrderData();
        mOneMonthAgoOrder.setSelected(false);
        mOneMonthOrder.setSelected(true);
        mCancelOrder.setSelected(false);
    }



    private int type = 1;
    private int page = 1;
    private int pageNum = 10;
    private OrderListAdapter mOrderListAdapter;

    @OnClick({R.id.back, R.id.one_month_ago_order, R.id.one_month_order, R.id.cancel_order, R.id.iv_no_order, R.id.goto_shop, R.id.framelayout_order_details})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                //// TODO: 2017/1/12 跳转到结算界面
//                loadOrdercheckoutData();
                onBackPressed();
                finish();
                break;
            //一个月前的订单
            case R.id.one_month_ago_order:
                type = 2;
                mOneMonthAgoOrder.setSelected(true);
                mOneMonthOrder.setSelected(false);
                mCancelOrder.setSelected(false);
                loadOrderData();
                break;
            //近一个月订单
            case R.id.one_month_order:
                type = 1;
                mOneMonthAgoOrder.setSelected(false);
                mOneMonthOrder.setSelected(true);
                mCancelOrder.setSelected(false);
                loadOrderData();
                break;
            //取消的订单
            case R.id.cancel_order:
                type = 3;
                mOneMonthAgoOrder.setSelected(false);
                mOneMonthOrder.setSelected(false);
                mCancelOrder.setSelected(true);
                loadOrderData();
                break;
            case R.id.iv_no_order:
                break;
            case R.id.goto_shop:
                //TODO 跳转到主页面还要更改下面的图标
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("id",0);
                startActivity(intent1);
                finish();
                break;
        }
    }


    private void loadOrderData() {
        RetrofitHelper.getApiService().getOrderListApi(type, page, pageNum,Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                .getString(LoginActivity.USERId_LOGIN,"")))
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderListResp -> {
                    List<OrderListResp.OrderListBean> data = orderListResp.getOrderList();
                    mOrderListBeens.clear();
                    mOrderListBeens.addAll(data);
                    mOrderListAdapter = new OrderListAdapter(mOrderListBeens);
                    setAdapterData();
                }, throwable -> {
                    Toast.makeText(OrderMyActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                });
    }

    private void setAdapterData() {
        if (mOrderListAdapter == null) {
            return;
        }
        mOrderListview.setAdapter(mOrderListAdapter);
        mOrderListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderMyActivity.this, OrderDetailsActivity.class);
                intent.putExtra("orderid", mOrderListBeens.get(position).getOrderId());
                intent.putExtra("type", type);
                startActivityForResult(intent, 200);
            }
        });
        mOrderListview.setEmptyView(findViewById(R.id.empty_order));
    }
    private String sku = "1:3:1,3|2:2:2,3";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("test", "4444");
        if (requestCode == 200 && resultCode == 250) {
            loadOrderData();
        } else if (requestCode == 200 && resultCode == 500) {
            Toast.makeText(this, "对不起取消订单失败", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: 2017/1/12 要改的
    private void loadOrdercheckoutData() {
        RetrofitHelper.getApiService().getOrdercheckoutRespApi(sku,Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                .getString(LoginActivity.USERId_LOGIN,"")))
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ordercheckoutResp -> {
                    Log.i("test","ordercheckoutResp"+ordercheckoutResp.toString());
                    if (ordercheckoutResp.getProductList().size()!=0){
                        Intent intent = new Intent(this, OrderCheckOutActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, throwable -> {
                    Toast.makeText(OrderMyActivity.this, "获取结算信息失败", Toast.LENGTH_SHORT).show();
                });
    }

}
