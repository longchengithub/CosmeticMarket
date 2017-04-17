package com.market.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.market.R;
import com.market.bean.Qiangou;
import com.market.http.ApiService;
import com.market.utils.FlashSale1Holder;
import com.market.utils.GlideImageLoader;
import com.market.view.activity.ProductDetailActivity;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/1/9.
 */

public class FlashSale1Adapter extends RecyclerView.Adapter<FlashSale1Holder>implements View.OnClickListener
{
    private  Context context;

    List<Qiangou.ProductListEntity> list;
    public FlashSale1Adapter(Context context, List<Qiangou.ProductListEntity> list) {
        this.context=context;
        this.list=list;
    }
    @Override
    public FlashSale1Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.flash_sale1, null);
        FlashSale1Holder flashSale1Holder = new FlashSale1Holder(view);
        return flashSale1Holder;
    }
    @Override
    public void onBindViewHolder(FlashSale1Holder holder, int position) {
        holder.iv.setOnClickListener(this);
        String s = ApiService.BASE_URL + list.get(0).getPic();
//        s="http://192.168.56.1:8080/market//images/product/detail/c11.jpg";
        String s1 = ApiService.BASE_URL + list.get(1).getPic();
//        s1="http://192.168.56.1:8080/market//images/product/detail/b11.jpg";
        String s2 = ApiService.BASE_URL + list.get(2).getPic();
//        s2="http://192.168.56.1:8080/market//images/product/detail/a11.jpg";
//        holder.tv.setText("SKll护肤套装\n"+"嫩肤露215ML+神仙水215ML\n"+"¥969");
        if (position==0) {
            new GlideImageLoader().displayImage(context,s,holder.iv);
        }else  if (position==1) {
            new GlideImageLoader().displayImage(context,s1,holder.iv);
        }else if (position==2){
            new GlideImageLoader().displayImage(context,s2,holder.iv);
        }else {
//            new GlideImageLoader().displayImage(context, ApiService.HONE_URL + list.get(position).getPic(), holder.iv);
//            Log.i("test",ApiService.HONE_URL+list.get(position).getPic());
            String s3 = ApiService.BASE_URL + list.get(position).getPic();
//            s3="http://192.168.56.1:8080/market//images/product/detail/c11.jpg";
            new GlideImageLoader().displayImage(context,s3,holder.iv);
        }
    }

    @Override
    public int getItemCount() {

        return list==null?0:list.size();
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case  R.id.iv:
                Intent intent = new Intent(context, ProductDetailActivity.class);
                int i = new Random().nextInt(4)+1;
                intent.putExtra("pid",i);
                context.startActivity(intent);
            break;
        }
    }
}
