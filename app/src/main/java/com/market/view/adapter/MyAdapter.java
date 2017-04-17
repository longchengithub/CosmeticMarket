package com.market.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.market.R;
import com.market.utils.GlideImageLoader;
import com.market.utils.MyViewHolder;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/7.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>  {
    public int item1= R.drawable.sz;
    public   int item1_1=R.drawable.sz2_2;
    private Context context;
    private  int a=0;
    private RelativeLayout list_item;
    private View view;
    private List<String> list;
    public MyAdapter(Context context , List<String> list){
        this.context=context;
        this.list=list;
    };
    @Override
    //加载布局
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.recycleview_item,null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }
        //绑定数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //获取网络地址
                    String s = list.get(a);
                    a++;
                    s=list.get(position);
                    subscriber.onNext(s);
                }

            }
            //事件发生的线程             //回调后的线程
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Observer<String>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("test",e.toString());
                    }
                    @Override
                    public void onNext(String strings) {
                       new GlideImageLoader().displayImage(context,strings,holder.iv);
                    }
                }
        );
        holder.title.setImageResource(item1_1);
        //给条目设置点击事件
//        RxView.clicks(view).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }


    @Override
    public int getItemCount() {

        return list==null?0:list.size();
    }

}
