package com.market.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.market.R;
import com.market.bean.AddressBean;
import com.market.bean.OrderSumbitResp;
import com.market.bean.OrdercheckoutResp;
import com.market.http.RetrofitHelper;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.activity.base.LoginActivity;
import com.market.view.adapter.OrderCheckOutAdpter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by GaoLeihua on 2017/1/8.
 */
public class OrderCheckOutActivity extends BaseCompatActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.top_bar)
    LinearLayout mTopBar;
    @BindView(R.id.iv_weizhi)
    ImageView mIvWeizhi;
    @BindView(R.id.use_name)
    TextView mUseName;
    @BindView(R.id.tel_num)
    TextView mTelNum;
    @BindView(R.id.isDefult)
    ImageView mIsDefult;
    @BindView(R.id.address)
    TextView mAddress;
    @BindView(R.id.RecyclerView_settle_accounts)
    RecyclerView mRecyclerViewSettleAccounts;
    @BindView(R.id.paymentList)
    TextView mPaymentList;
    @BindView(R.id.deliveryList)
    TextView mDeliveryList;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.totalCount)
    TextView mTotalCount;
    @BindView(R.id.checkout)
    Button mCheckout;
    @BindView(R.id.total_price)
    TextView mTotalPrice;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.setting_address_wu)
    RelativeLayout mSettingAddressWu;
    @BindView(R.id.setting_address_you)
    RelativeLayout mSettingAddressYou;
    @BindView(R.id.setting_address)
    FrameLayout mSettingAddress;


    private String sku = "1:3:1,3|2:2:2,3";
    private int addressId = 0;
    private int type = 1;
    protected OrderCheckOutAdpter mOrderCheckOutAdpter;
    protected List<OrdercheckoutResp.PaymentListBean> mPaymentList1;
    protected List<OrdercheckoutResp.DeliveryListBean> mDeliveryList1;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_checkout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        loadOrderData();

    }

    //加载数据
    private void loadOrderData() {
        RetrofitHelper.getApiService().getOrdercheckoutRespApi(sku,Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                .getString(LoginActivity.USERId_LOGIN,"")))
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ordercheckoutResp -> {

                    OrdercheckoutResp.AddressInfoBean addressInfo = ordercheckoutResp.getAddressInfo();
                    OrdercheckoutResp.CheckoutAddupBean checkoutAddup = ordercheckoutResp.getCheckoutAddup();
                    List<String> checkoutProm = ordercheckoutResp.getCheckoutProm();
                    List<OrdercheckoutResp.ProductListBean> productList = ordercheckoutResp.getProductList();
                    mPaymentList1 = ordercheckoutResp.getPaymentList();
                    mDeliveryList1 = ordercheckoutResp.getDeliveryList();
                    initAddressInfo(addressInfo);//设置地址信息
                    initAddup(checkoutAddup);//设置总计
                    // TODO: 2017/1/12 设置商品列表
                    initproductList(ordercheckoutResp);//设置商品列表
                }, throwable -> {
                    Toast.makeText(OrderCheckOutActivity.this, "获取结算信息失败", Toast.LENGTH_SHORT).show();
                });
    }

    //  2017/1/12 设置商品列表
    private void initproductList(OrdercheckoutResp ordercheckoutResp) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewSettleAccounts.setLayoutManager(layoutManager);
        mOrderCheckOutAdpter = new OrderCheckOutAdpter(this, ordercheckoutResp);
        mRecyclerViewSettleAccounts.setAdapter(mOrderCheckOutAdpter);
    }

    private void initAddup(OrdercheckoutResp.CheckoutAddupBean checkoutAddup) {
        mTotalCount.setText("总件数:" + checkoutAddup.getTotalCount());
        mTotalPrice.setText("总价:" + checkoutAddup.getTotalPrice());
    }

    //设置总计

    private void initAddressInfo(OrdercheckoutResp.AddressInfoBean addressInfo) {
        if (addressInfo == null) {
            mSettingAddressWu.setVisibility(View.VISIBLE);
            mSettingAddressYou.setVisibility(View.GONE);
        } else {
            mSettingAddressYou.setVisibility(View.VISIBLE);
            mSettingAddressWu.setVisibility(View.GONE);
            mTelNum.setText(addressInfo.getPhoneNumber());
            mUseName.setText(addressInfo.getName());
            mAddress.setText(addressInfo.getProvince() + addressInfo.getCity() + addressInfo.getAddressArea() + addressInfo.getAddressDetail());
            addressId = addressInfo.getId();
            if (!(addressInfo.getIsDefault() == 1)) {
                mIsDefult.setVisibility(View.GONE);
            }
        }

    }

    //// TODO: 2017/1/12 有问题
    @OnClick({R.id.checkout, R.id.back, R.id.paymentList, R.id.setting_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkout://提交订单
                Log.i("test", "111" + addressId);
                Log.i("test", "111" + type);
                //提交订单
                /*
                * @Field("sku") String sku,                                 sku=1:3:1,3,3,4|2:2:2
                  @Field("addressId") int addressId,                         addressId=134
                  @Field("paymentType") int paymentType,                      paymentType=1
                  @Field("deliveryType") int deliveryType,                      deliveryType=1
                  @Field("invoiceType") int invoiceType,                        invoiceType=1
                  @Field("invoiceTitle") String invoiceTitle,                   invoiceTitle=传智慧播客教育科技有限公司
                  @Field("invoiceContent") String invoiceContent              invoiceContent=1*/
                //sku=1:3:1,3,3,4|2:2:2,3&addressId=134&paymentType=1&
                // deliveryType=1&invoiceType=1&invoiceTitle=传智慧播客教育科技有限公司&invoiceContent=1
                /*RetrofitHelper.getApiService().getOrncelResponseApi(orderid)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orncelResponse -> {
                * */
                RetrofitHelper.getApiService().getOrderSumbitRespApi(sku, addressId, type, 1, 1,
                        "传智慧播客教育科技有限公司", "1",Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                                .getString(LoginActivity.USERId_LOGIN,"")))
                        .compose(bindToLifecycle())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mOrderSumbitResp -> {
                            Log.i("test", "222" + addressId);
                            Log.i("test", "222" + type);
                            String response = mOrderSumbitResp.getResponse();
                            OrderSumbitResp.OrderInfoBean orderInfo = mOrderSumbitResp.getOrderInfo();
                            String orderId = orderInfo.getOrderId();

                            Toast.makeText(this, "订单已生成，就是不让你支付，哈哈", Toast.LENGTH_SHORT).show();
                            //// TODO: 2017/1/12 跳转到主页面
                            /*Intent intent=new Intent();
                            intent.putExtra("orderid",orderId);
                            startActivity(intent);*/
                            gotoOrderDetaialActivity(orderId);
                            finish();
                        }, throwable -> {
                            Toast.makeText(this, "订单提交失败，又给你节约了不少钱", Toast.LENGTH_SHORT).show();
                            finish();
                            //Toast.makeText(OrderCheckOutActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                        });
                break;
            case R.id.back:
                //标题栏的返回 返回到购物车fragment的界面
                BackToShoppinggFregment();
                break;
            case R.id.paymentList:
                //设置付款方式
                View contentView = View.inflate(OrderCheckOutActivity.this, R.layout.item_order_pop_payment, null);
                final PopupWindow pw = new PopupWindow(contentView, LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                //定义一个数组,0索引表示x轴的位置,1表示y轴的位置
                int[] location = new int[2];
                //获取在屏幕上的位置
                view.getLocationOnScreen(location);
                // 这是规定
                pw.setOutsideTouchable(true);
                // 整个屏幕都是pw的焦点
                pw.setFocusable(true);
                pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //指定窗口怎么弹出和消失
                pw.setAnimationStyle(R.style.ShowPopupWindowStyleAnimation);

                pw.showAtLocation(view, Gravity.TOP | Gravity.LEFT, location[0] + 120, location[1] + 10);
                TextView t1 = (TextView) contentView.findViewById(R.id.type1);
                TextView t2 = (TextView) contentView.findViewById(R.id.type2);
                TextView t3 = (TextView) contentView.findViewById(R.id.type3);
                View.OnClickListener onClickListener = new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.type1:
                                type = mPaymentList1.get(0).getType();
                                mPaymentList.setText(mPaymentList1.get(0).getDes());
                                break;
                            case R.id.type2:
                                type = mPaymentList1.get(1).getType();
                                mPaymentList.setText(mPaymentList1.get(1).getDes());
                                break;
                            case R.id.type3:
                                type = mPaymentList1.get(2).getType();
                                mPaymentList.setText(mPaymentList1.get(2).getDes());
                                break;
                        }
                        pw.dismiss();
                    }
                };
                t1.setOnClickListener(onClickListener);
                t2.setOnClickListener(onClickListener);
                t3.setOnClickListener(onClickListener);
                break;
            case R.id.setting_address:
                Intent intent = new Intent(OrderCheckOutActivity.this, AddressManageActivity.class);
                startActivityForResult(intent, 100);
                break;
        }

    }

    //提交订单
    public void gotoOrderDetaialActivity(String orderId) {
        Toast.makeText(this, "订单已生成，就是不让你支付，哈哈", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,OrderDetailsActivity.class);
        intent.putExtra("orderid", orderId);
        startActivity(intent);
        finish();
    }

    //标题栏的返回 返回到购物车fragment的界面
    private void BackToShoppinggFregment() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //提交订单


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //// TODO: 2017/1/11
        if (requestCode == 100 && resultCode == 101) {
            AddressBean.AddressListBean data1 = (AddressBean.AddressListBean) data.getSerializableExtra("data");
            mTelNum.setText(data1.getPhoneNumber());
            mUseName.setText(data1.getName());
            mAddress.setText(data1.getProvince() + data1.getCity() + data1.getAddressArea() + data1.getAddressDetail());

            if (!(data1.getIsDefault() == 1)) {
                mIsDefult.setVisibility(View.GONE);
            }
        }
    }

}