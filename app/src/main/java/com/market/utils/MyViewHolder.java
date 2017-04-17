package com.market.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.market.R;

/**
 * Created by Administrator on 2017/1/7.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView iv;
    public ImageView title;

    public MyViewHolder(View itemView) {
        super(itemView);
        iv = (ImageView) itemView.findViewById(R.id.iv);
        title= (ImageView) itemView.findViewById(R.id.title);
    }



}
