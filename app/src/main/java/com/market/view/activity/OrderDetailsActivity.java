package com.market.view.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.market.R;
import com.market.bean.OrderDetailResp;
import com.market.http.RetrofitHelper;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.activity.base.LoginActivity;
import com.market.view.adapter.OrderDetailsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zdf on 2017/1/9.
 */

public class OrderDetailsActivity extends BaseCompatActivity {

    @BindView(R.id.daohang_fanhui)
    ImageView mDaohangFanhui;
    @BindView(R.id.orderId)
    TextView mOrderId;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.tel_num)
    TextView mTelNum;
    @BindView(R.id.addressArea_And_addressDetail)
    TextView mAddressAreaAndAddressDetail;
    @BindView(R.id.status)
    TextView mStatus;
    @BindView(R.id.shipping_method)
    TextView mShippingMethod;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.open_according)
    TextView mOpenAccording;
    @BindView(R.id.invoiceTitle)
    TextView mInvoiceTitle;
    @BindView(R.id.Delivery_requirements)
    TextView mDeliveryRequirements;
    @BindView(R.id.goods_recyclerView)
    RecyclerView mGoodsRecyclerView;
    @BindView(R.id.totalCount)
    TextView mTotalCount;
    @BindView(R.id.freight)
    TextView mFreight;
    @BindView(R.id.checkoutProm)
    TextView mCheckoutProm;
    @BindView(R.id.totalPrice)
    TextView mTotalPrice;
    @BindView(R.id.bt_cancle_order)
    Button mBtCancleOrder;
    @BindView(R.id.bt_checkout_order)
    Button mBtCheckoutOrder;
    private OrderDetailsAdapter mOrderDetailsAdapter;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
    }

    private String orderid;
    private List<OrderDetailResp.ProductListBean> mProductListBeen;
    private int type;

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        type = intent.getIntExtra("type", 1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mGoodsRecyclerView.setLayoutManager(linearLayoutManager);

        loadOrderData();
        //mGoodsList.setAdapter(mOrderDetailsAdapter);
    }

    @OnClick(R.id.daohang_fanhui)
    public void onClick() {
        onBackPressed();
        finish();
    }


    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int itemCount = mOrderDetailsAdapter.getItemCount();
            int pos = parent.getChildAdapterPosition(view);

            outRect.left = 10;
            outRect.right = 10;
            outRect.bottom = 10;

            if (pos != 0) {
                outRect.top = mSpace;
            } else {
                outRect.top = 0;
            }
        }
    }

    /**
     * 联网获取javabean，设置值
     */
    private void loadOrderData() {
        Log.i("test", "loadOrderData111");
        RetrofitHelper.getApiService().getOrderDetailRespApi(orderid,Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                .getString(LoginActivity.USERId_LOGIN,"")))
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderDetailResp -> {
                    Log.i("test", "orderDetailResp"+orderDetailResp.toString());
                    OrderDetailResp.AddressInfoBean addressInfo = orderDetailResp.getAddressInfo();
                    OrderDetailResp.CheckoutAddupBean checkoutAddup = orderDetailResp.getCheckoutAddup();
                    OrderDetailResp.DeliveryInfoBean deliveryInfo = orderDetailResp.getDeliveryInfo();
                    OrderDetailResp.InvoiceInfoBean invoiceInfo = orderDetailResp.getInvoiceInfo();
                    OrderDetailResp.OrderInfoBean orderInfo = orderDetailResp.getOrderInfo();
                    OrderDetailResp.PaymentInfoBean paymentInfo = orderDetailResp.getPaymentInfo();
                    mProductListBeen = orderDetailResp.getProductList();
                    String response = orderDetailResp.getResponse();
                    mOrderId.setText(orderid);
                    mName.setText(addressInfo.getName());
                    mAddressAreaAndAddressDetail.setText(addressInfo.getAddressArea() + addressInfo.getAddressDetail());
                    mStatus.setText(orderInfo.getStatus());
                    if (type != 3 && orderInfo.getStatus().equals("未处理")) {
                        mBtCancleOrder.setVisibility(View.VISIBLE);
                        mBtCancleOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadCancleOrderDate();
                            }
                        });
                        mBtCheckoutOrder.setVisibility(View.VISIBLE);
                       mBtCheckoutOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(OrderDetailsActivity.this,"哈哈，你是不可能付款成功的",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }else if(type != 3&&!orderInfo.getStatus().equals("未处理")){
                        mBtCancleOrder.setVisibility(View.VISIBLE);
                        mBtCancleOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadCancleOrderDate();
                            }
                        });
                    }

                    switch (paymentInfo.getType()) {
                        case 1:
                            mShippingMethod.setText("货到付款");
                            break;
                        case 2:
                            mShippingMethod.setText("POS机");
                            break;
                        case 3:
                            mShippingMethod.setText("支付宝");
                            break;
                    }
                    mTime.setText(orderInfo.getTime());
                    switch (Integer.parseInt(deliveryInfo.getType())) {
                        case 1:
                            mDeliveryRequirements.setText("周一至周五送货");
                            break;
                        case 2:
                            mDeliveryRequirements.setText(" 双休日及公众假期送货");
                            break;
                        case 3:
                            mDeliveryRequirements.setText("时间不限，工作日双休日及公众假期均可送货");
                            break;
                    }
                    mInvoiceTitle.setText(invoiceInfo.getInvoiceTitle());
                    mTotalCount.setText(checkoutAddup.getTotalCount() + "");
                    /*String freight="10";
                    if (mCheckoutProm.getText().equals("促销信息二")){
                        freight="20";
                    }*/
                    mFreight.setText(checkoutAddup.getFreight() + "");
                    int totalCount = checkoutAddup.getTotalPrice();
                    mTotalPrice.setText(totalCount + "");
                    mOrderDetailsAdapter = new OrderDetailsAdapter(this, mProductListBeen);
                    mGoodsRecyclerView.addItemDecoration(new SpaceItemDecoration(30));
                    mGoodsRecyclerView.setAdapter(mOrderDetailsAdapter);
                }, throwable -> {
                    Toast.makeText(OrderDetailsActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * post请求
     * 获取响应码是否为
     */
    private void loadCancleOrderDate() {
        Log.i("test", "2222");
        RetrofitHelper.getApiService().getOrncelResponseApi(orderid,Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                .getString(LoginActivity.USERId_LOGIN,"")))
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orncelResponse -> {
                    String response = orncelResponse.getResponse();
                    Log.i("test", "1111");
                    Toast.makeText(this, "可以获取响应11", Toast.LENGTH_SHORT).show();
                    if ("orderCancel".equals(response)) {
                        setResult(250);
                        finish();
                    } else {
                        setResult(500);
                        finish();
                    }
                }, throwable -> {
                    Toast.makeText(OrderDetailsActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                });
    }
}
