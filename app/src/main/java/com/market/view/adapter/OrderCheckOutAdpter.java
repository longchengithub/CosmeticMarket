package com.market.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.market.R;
import com.market.bean.OrdercheckoutResp;
import com.market.http.ApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zdf on 2017/1/11.
 */

public class OrderCheckOutAdpter extends RecyclerView.Adapter<OrderCheckOutAdpter.ViewHolder> {



    private OrdercheckoutResp mOrdercheckoutResp;

    private Context mContext;

    public OrderCheckOutAdpter(Context mContext, OrdercheckoutResp ordercheckoutResp) {

        this.mContext = mContext;
        mOrdercheckoutResp = ordercheckoutResp;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goods_pic)
        ImageView mIvGoodsPic;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.textView6)
        TextView mTextView6;
        @BindView(R.id.price)
        TextView mPrice;
        @BindView(R.id.prodNum)
        TextView mProdNum;
        @BindView(R.id.subtotal)
        TextView mSubtotal;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_checkout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<OrdercheckoutResp.ProductListBean> mProductList = mOrdercheckoutResp.getProductList();
        Log.i("test", "mProductList");
        List<OrdercheckoutResp.PaymentListBean> mPaymentList = mOrdercheckoutResp.getPaymentList();
        List<OrdercheckoutResp.DeliveryListBean> mDeliveryList = mOrdercheckoutResp.getDeliveryList();
        OrdercheckoutResp.ProductListBean productListBean = mProductList.get(position);

        OrdercheckoutResp.ProductListBean.ProductBean product = productListBean.getProduct();
        holder.mProdNum.setText("数量:" + productListBean.getProdNum());
        holder.mName.setText(product.getName());
        holder.mPrice.setText("￥  " + product.getPrice());
        holder.mPrice.setTextColor(Color.RED);
        holder.mSubtotal.setText("￥  " + product.getPrice() * productListBean.getProdNum());
        Glide.with(mContext).load(ApiService.BASE_URL + product.getPic()).fitCenter().into(holder.mIvGoodsPic);


        //holder.setTAG(position);
       /*holder.mPaymentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCheckOutListclikedListener!=null){
                    int i = mOnCheckOutListclikedListener.setpaymentListclikedListener(v);
                    switch (i){
                        case 1:
                            holder.mPaymentList.setText("到付-现金");
                            break;
                        case 2:
                            holder.mPaymentList.setText("到付-POS机");
                            break;
                        case 3:
                            holder.mPaymentList.setText("支付宝");
                            break;
                    }
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        Log.i("test", " mOrdercheckoutResp.getProductList().size()" + mOrdercheckoutResp.getProductList().size());
        return mOrdercheckoutResp.getProductList().size();

    }



}
