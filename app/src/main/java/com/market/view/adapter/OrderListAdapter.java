package com.market.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.market.R;
import com.market.bean.OrderListResp;
import com.market.http.ApiService;
import com.market.viewholder.CommonViewHolder;

import java.util.List;

/**
 * Created by zdf on 2017/1/8.
 */

public class OrderListAdapter extends BaseAdapter {
    private List<OrderListResp.OrderListBean> orderList;
    private Context context;

    public OrderListAdapter(List<OrderListResp.OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public void setOrderList(List<OrderListResp.OrderListBean> orderList) {
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList == null ? 0 : orderList.size();
    }

    @Override
    public List<OrderListResp.OrderListBean> getItem(int position) {
        return orderList == null ? null : (List<OrderListResp.OrderListBean>) orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.createCVH(convertView, parent, R.layout.item_order_my);
        OrderListResp.OrderListBean order = orderList.get(position);
        holder.getTv(R.id.ding_dan_bian_hao).setText("订单编号:  "+order.getOrderId());
        holder.getTv(R.id.ding_dan_zong_e).setText("总额:  ￥"+order.getPrice());
        holder.getTv(R.id.zhuang_tai).setText("状态:  "+order.getStatus());
        holder.getTv(R.id.shi_jian).setText(order.getTime());
        Glide.with(parent.getContext()).load(ApiService.BASE_URL+order.getProduct().getPic()).fitCenter().into(holder.getIv(R.id.iv_pro));
        return holder.convertView;
    }
}
