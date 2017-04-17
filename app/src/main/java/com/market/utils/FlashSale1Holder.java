package com.market.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.market.R;

/**
 * Created by Administrator on 2017/1/9.
 */

public class FlashSale1Holder extends RecyclerView.ViewHolder {


    public ImageView iv;
   // public   TextView tv;

    public FlashSale1Holder(View itemView) {
        super(itemView);
        iv = (ImageView) itemView.findViewById(R.id.iv);
        //tv = (TextView) itemView.findViewById(tv);

    }
}
