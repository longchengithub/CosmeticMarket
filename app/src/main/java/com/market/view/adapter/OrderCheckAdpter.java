package com.market.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.market.bean.OrdercheckoutResp;

/**
 * Created by zdf on 2017/1/11.
 */

public class OrderCheckAdpter extends RecyclerView.Adapter<OrderCheckAdpter.ViewHolder> {
    private  OrdercheckoutResp ordercheckoutResp;
    private Context context;

    public OrderCheckAdpter(Context context, OrdercheckoutResp ordercheckoutResp) {
        this.context = context;
        this.ordercheckoutResp = ordercheckoutResp;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("test","111");
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("test","222");
    }

    @Override
    public int getItemCount() {
        Log.i("test","333");
        return 0;
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
            Log.i("test","444");


        }
    }
}
