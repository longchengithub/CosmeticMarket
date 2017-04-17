package com.market.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.market.R;

/**
 * Created by Administrator on 2017/1/8.
 */
public class Panicbuying extends RecyclerView.ViewHolder {

    public  ImageView iv;
    public  TextView tv;
    public Panicbuying(View itemView) {
        super(itemView);
        iv = (ImageView) itemView.findViewById(R.id.iv_ceshi);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}
