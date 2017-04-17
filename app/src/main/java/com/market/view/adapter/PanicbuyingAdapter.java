package com.market.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.market.R;
import com.market.utils.GlideImageLoader;
import com.market.utils.Panicbuying;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/8.
 */         //..限时抢购

public class PanicbuyingAdapter extends RecyclerView.Adapter<Panicbuying> {
    private Context context;
    private View view;
    private int a=0;
    private  List<String> list;
    public PanicbuyingAdapter(Context context, List<String> list) {
        this.context = context;
        this.list=list;
        Log.i("test","我被初始化了");
    }


    @Override
    public Panicbuying onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载条目布局
            view = View.inflate(context, R.layout.panicbuying_item, null);
            Panicbuying panicbuying = new Panicbuying(view);
            Log.i("test", "布局加载成功");
            return panicbuying;
    }

    @Override
    public void onBindViewHolder(Panicbuying holder, int position) {
        Log.i("test",list.size()+"");
        if (holder!=null) {
//        for (int i = 0; i <list.size() ; i++) {
//            DrawableTypeRequest<String> stringDrawableTypeRequest = list.get(i);
//        }
            //遍历集合
//        Observable.just(list).subscribe(new Action1<DrawableTypeRequest<String>>() {
//            @Override
//            public void call(DrawableTypeRequest<String> stringDrawableTypeRequest) {
//                Toast.makeText(context,"1",Toast.LENGTH_SHORT).show();
//                 stringDrawableTypeRequest = list.get(position);
//
//
//            }
//        });
//        Observable.just(list).subscribe(new Action1<List<DrawableTypeRequest<String>>>() {
//            @Override
//            public void call(List<DrawableTypeRequest<String>> drawableTypeRequests) {
//                for (int i = 0; i < drawableTypeRequests.size(); i++) {
//                    DrawableTypeRequest<String> stringDrawableTypeRequest = drawableTypeRequests.get(i);
//                    stringDrawableTypeRequest = list.get(position);
//                    stringDrawableTypeRequest.into(holder.iv);
//
//                }
//            }
//        });
           // Log.i("test",position+"");
//            view.setTag(position);
//            final Panicbuying tag = (Panicbuying) view.getTag();

            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                                //获取网络地址
                                String s = list.get(a);
                                a++;
                                Log.i("test",s+"网络地址");
                                s=list.get(position);
                                subscriber.onNext(s);
                                Log.i("test", "试试");
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
        }

    }
    @Override
    public int getItemCount () {

        return list==null?0:list.size();
    }

}
