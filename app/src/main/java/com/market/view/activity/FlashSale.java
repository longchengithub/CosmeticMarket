package com.market.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.market.R;
import com.market.bean.Topic;
import com.market.http.ApiService;
import com.market.http.RetrofitHelper;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.adapter.PanicbuyingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/7.
 */
public class FlashSale extends BaseCompatActivity implements View.OnClickListener {

    //临时集合
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.RecyclerView_Ry)
    RecyclerView ry;
    private PanicbuyingAdapter adapter;
    private int lastItemPosition;
    private int firstItemPosition;
    private int start=1;
    private int end=8;
    private List<String> list;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    adapter.notifyDataSetChanged();
                    //Log.i("test","刷新了");
                    break;
            }
        }
    };
    @Override
    protected void initToolBar() {

    }

    @Override   //加载布局后
    protected int getLayoutId() {

        return R.layout.flash_sale;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        back.setOnClickListener(this);
        //第一次加载数据
        iniData(start,end);
        initRecyclerView();

    }

//    private void iniData(int start,int end) {
////        list=new ArrayList<>();
////        RetrofitHelper.getApiServiceHome().getTopics(start,end).compose(bindToLifecycle()).subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread()).subscribe(topic -> {
////            List<Topic.TopicEntity> topic1 = topic.getTopic();
////            for (int i = 0; i < topic1.size(); i++) {
////                Topic.TopicEntity topicEntity = topic1.get(i);
////                String ulr = topicEntity.getPic();
////                list.add(ApiService.HONE_URL+ulr);
////                Log.i("test",list.get(i)+"我");
////            }
////        },throwable -> {
////            Toast.makeText(getBaseContext(),"你的网络消失在异次元中",Toast.LENGTH_SHORT).show();
////        }
////        );
//    }
    //初始化界面
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ry.setLayoutManager(layoutManager);
        ry.setItemAnimator(new DefaultItemAnimator());
        ry.setHasFixedSize(true);

        adapter = new PanicbuyingAdapter(FlashSale.this, list);
        ry.setAdapter(adapter);
        Toast.makeText(this,"哈哈",Toast.LENGTH_SHORT).show();
        //Ry.setNestedScrollingEnabled(false);
        //滑动监听
        ry.setOnScrollListener(onscrollisttenr);



    }
    private RecyclerView.OnScrollListener onscrollisttenr=new RecyclerView.OnScrollListener() {

        private int lastItemCount;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

            // 当滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                // 判断是否滚动到底部
                if (lastVisibleItem == (totalItemCount - 1) ) {
                    //加载更多功能的代码
                   // Toast.makeText(getBaseContext(),"向上滑动",Toast.LENGTH_SHORT).show();
                    //获取最后可见一个
                    int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                    //当前第一个可见
                    int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                    //当滑到最后的item的时候
                    //往集合添加数据
                    list.add(ApiService.IMAGE_URL+"/images/topic/3.png");
                    list.add(ApiService.IMAGE_URL+"/images/topic/4.png");
                    list.add(ApiService.IMAGE_URL+"/images/topic/6.png");
                    list.add(ApiService.IMAGE_URL+"/images/topic/7.png");
                    list.add(ApiService.IMAGE_URL+"/images/topic/8.png");
                    handler.sendEmptyMessage(0);
                }else if (lastVisibleItem<totalItemCount-1){
                    //Toast.makeText(getBaseContext(),"向下滑动",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getBaseContext(),"刷新了",Toast.LENGTH_SHORT).show();
            }

        }


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

        }


    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                 break;
        }
    }

    private List<String> iniData(int start,int end) {
        list=new ArrayList<>();
        RetrofitHelper.getApiService().getTopics(start,end).compose(bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(topic -> {
                    List<Topic.TopicEntity> topic1 = topic.getTopic();
                    for (int i = 0; i < topic1.size(); i++) {
                        Topic.TopicEntity topicEntity = topic1.get(i);
                        String ulr = topicEntity.getPic();
                        list.add(ApiService.IMAGE_URL+ulr);
                        Log.i("test",list.get(i)+"我");
                    }
                },throwable -> {
                    Toast.makeText(getBaseContext(),"你的网络消失在异次元中",Toast.LENGTH_SHORT).show();
                }
        );
        return list;
    }
}
