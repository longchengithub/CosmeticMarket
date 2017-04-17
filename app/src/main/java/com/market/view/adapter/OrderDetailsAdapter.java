package com.market.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.market.R;
import com.market.bean.OrderDetailResp;
import com.market.http.ApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zdf on 2017/1/9.
 */

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {

    private Context mContext;


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pic)
        ImageView mPic;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.number)
        TextView mNumber;
        @BindView(R.id.price)
        TextView mPrice;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private List<OrderDetailResp.ProductListBean> mOrderInfos;

    public OrderDetailsAdapter(Context mContext,List<OrderDetailResp.ProductListBean> OrderInfos) {
        mOrderInfos = OrderInfos;
      this.mContext=mContext ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_goods_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrderDetailResp.ProductListBean productListBean = mOrderInfos.get(position);
            holder.mName.setText(productListBean.getProduct().getName());
        holder.mNumber.setText(productListBean.getProdNum()+"");
        holder.mPrice.setText(productListBean.getProduct().getPrice()+"");
        Glide.with(mContext).load(ApiService.BASE_URL+productListBean.getProduct().getPic()).fitCenter().into(holder.mPic);
    }

    @Override
    public int getItemCount() {
        return mOrderInfos.size();
    }
}
