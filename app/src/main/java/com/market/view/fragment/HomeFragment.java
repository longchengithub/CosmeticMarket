package com.market.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.market.R;
import com.market.bean.BannerResp;
import com.market.bean.Hotproduct;
import com.market.bean.Tijian;
import com.market.http.ApiService;
import com.market.http.RetrofitHelper;
import com.market.utils.GlideImageLoader;
import com.market.view.activity.FlashSale;
import com.market.view.activity.FlashSale1;
import com.market.view.activity.NewArrivalsActivity;
import com.market.view.activity.ProductDetailActivity;
import com.market.view.activity.SingleProductActivity;
import com.market.view.adapter.MyAdapter;
import com.market.view.fragment.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlong on 2016/12/20.
 */

public class HomeFragment extends BaseFragment implements OnBannerClickListener, View.OnClickListener
{
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.RecyclerView_Ry)
    RecyclerView ry;
    @BindView(R.id.xsqg)
    ImageView xsqg;
    @BindView(R.id.cxkb)
    ImageView cxkb;
    @BindView(R.id.xpsj)
    ImageView xpsj;
    @BindView(R.id.rmsp)
    ImageView rmsp;
    @BindView(R.id.tv_cxkb)
    TextView tv_cxkb;
    @BindView(R.id.tv_xpsj)
    TextView tv_xpsj;
    @BindView(R.id.tv_rmsp)
    TextView tv_rmsp;
    @BindView(R.id.tv_xsqg)
    TextView tv_xsqg;
    @BindView(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;
    @BindView(R.id.skinNursing)
    FrameLayout skinNursing;
    @BindView(R.id.banner1)
    ImageView banner1;
    @BindView(R.id.shampoo)
    ImageView shampoo;
    @BindView(R.id.cat)
    ImageView cat;
    @BindView(R.id.xiangshuihao)
    ImageView xiangshuihao;
    private List<String> listurl;
    @BindView(R.id.yashua1)
    ImageView yashua1;
    @BindView(R.id.shougongmu)
    ImageView shougongmu;
    @BindView(R.id.kou_hong)
    ImageView kou_hong;
    @BindView(R.id.kou_hong1)
    ImageView kou_hong1;
    @BindView(R.id.kou_hong2)
    ImageView kou_hong2;
    @BindView(R.id.nest)
    NestedScrollView nest;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    private List<String> list;
    private List<String> Mylist;
    private MyAdapter adapter;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onLazyLoad()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        loadBannerData();   //联网加载banner的数据
        loadBannerData1();   //联网加载banner1的数据
        loadBannerTijian(); //联网加载推荐商品
        xsqg.setOnClickListener(this);
        cxkb.setOnClickListener(this);
        xpsj.setOnClickListener(this);
        rmsp.setOnClickListener(this);
        cxkb.setOnClickListener(this);
        tv_xpsj.setOnClickListener(this);
        tv_rmsp.setOnClickListener(this);
        tv_xsqg.setOnClickListener(this);
        //图片点击事件
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        banner1.setOnClickListener(this);
        shampoo.setOnClickListener(this);
        kou_hong.setOnClickListener(this);
        kou_hong1.setOnClickListener(this);
        kou_hong2.setOnClickListener(this);
        yashua1.setOnClickListener(this);
        shougongmu.setOnClickListener(this);
        kou_hong.setOnClickListener(this);
        ry.setOnClickListener(this);
        initRecyclerView(); //初始化 RecycleView 解决与NestedScrollView冲突
//        nest.setNestedScrollingEnabled(true);


    }

    private void loadBannerData1()
    {
        listurl = new ArrayList<>();
        RetrofitHelper.getApiService().getHotproduct().compose(bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(hotproduct -> {
                    List<Hotproduct.RecommendpicsEntity> data = hotproduct.getRecommendpics();
                    for (int i = 0; i < data.size(); i++)
                    {
                        listurl.add(ApiService.BASE_URL + data.get(i).getImg_url());
                    }
                    initBanner1();
                }, throwable -> {
                    Toast.makeText(mActivity, "你的网路消失在异次元，请检查你的网路", Toast.LENGTH_SHORT).show();
                }

        );
    }

    private void initBanner1()
    {
        new GlideImageLoader().displayImage(getContext(), listurl.get(0), banner1);
        new GlideImageLoader().displayImage(getContext(), listurl.get(1), shampoo);
        new GlideImageLoader().displayImage(getContext(), listurl.get(2), cat);
        new GlideImageLoader().displayImage(getContext(), listurl.get(3), xiangshuihao);
        Toast.makeText(getContext(), listurl.get(0), Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();

        Log.i("test", listurl.get(0));
    }

    private void loadBannerTijian()
    {
        list = new ArrayList<>();
        Log.i("test", list.size() + "");
        RetrofitHelper.getApiService().getTijian().compose(bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(tijian -> {
                    List<Tijian.BrandEntity> brand = tijian.getBrand();
                    for (int i = 0; i < brand.size(); i++)
                    {
                        Tijian.BrandEntity brandEntity = brand.get(i);
                        List<Tijian.BrandEntity.ValueEntity> value = brandEntity.getValue();
                        for (int i1 = 0; i1 < value.size(); i1++)
                        {
                            //4次循环
                            String url = value.get(i1).getPic();
                            list.add(ApiService.IMAGE_URL + url);
                        }
                        if (i == 1) break;
                    }
                    initTijian();
                }, throwable -> {

                }
        );
    }

    private void initTijian()
    {
        new GlideImageLoader().displayImage(getContext(), list.get(0), yashua1);
        new GlideImageLoader().displayImage(getContext(), list.get(1), shougongmu);
        new GlideImageLoader().displayImage(getContext(), list.get(2), kou_hong);
        new GlideImageLoader().displayImage(getContext(), list.get(3), kou_hong1);
        new GlideImageLoader().displayImage(getContext(), list.get(3), kou_hong2);
        for (int i = 0; i < list.size(); i++)
        {
            Log.i("test", list.get(i));
        }
        Log.i("test", list.size() + "");
    }

    private void initRecyclerView()
    {
        Mylist = new ArrayList<>();
        for (int i = 0; i < 17; i++)
        {
            Mylist.add(ApiService.IMAGE_URL + "/images/topic/33.png");
            Mylist.add(ApiService.IMAGE_URL + "/images/topic/sz.png");
            Mylist.add(ApiService.IMAGE_URL + "/images/topic/tz.png");
            Mylist.add(ApiService.IMAGE_URL + "/images/topic/33.png");
            Mylist.add(ApiService.IMAGE_URL + "/images/topic/sz.png");
            Mylist.add(ApiService.IMAGE_URL + "/images/topic/tz.png");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //layoutManager.setSmoothScrollbarEnabled(true);
        // layoutManager.setAutoMeasureEnabled(true);
        ry.setLayoutManager(layoutManager);
        ry.setHasFixedSize(true);
        //嵌套滑动
        ry.setNestedScrollingEnabled(false);
        ry.setLayoutManager(layoutManager);
        adapter = new MyAdapter(getContext(), Mylist);
        ScaleInAnimationAdapter scale=new ScaleInAnimationAdapter(adapter,0.2f);
        scale.setFirstOnly(false);
        scale.setDuration(1500);
        ry.setAdapter(scale);
        ry.addOnScrollListener(listener);
        //设置上拉点击事件
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                Toast.makeText(getContext(), "上拉刷新", Toast.LENGTH_SHORT).show();
//                handler.sendEmptyMessage(0);
                swipeRefreshLayout.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //3秒后结束刷新
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "消失吧", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);

            }
        });
    }

    private RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener()
    {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState)
        {
            Log.i("test", "获取最后一个完全显示的ItemPosition");
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            //获取最后一个完全显示的ItemPosition
//            int lastVisibleItem1 = manager.findLastCompletelyVisibleItemPosition();
//            int totalItemCount1 = manager.getItemCount();
            //Toast.makeText(getContext(),lastVisibleItem1+","+totalItemCount1+"",Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(),newState+"",Toast.LENGTH_SHORT).show();
//            // 当滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE)
            {
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                // 判断是否滚动到底部
//                if (lastVisibleItem-2 == (totalItemCount - 2) ) {
                //加载更多功能的代码
                // Toast.makeText(getBaseContext(),"向上滑动",Toast.LENGTH_SHORT).show();
                //获取最后可见一个
                int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                //当前第一个可见
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                //当滑到最后的item的时候
                //往集合添加数据
                Mylist.add(ApiService.IMAGE_URL+"/images/topic/3.png");
                Mylist.add(ApiService.IMAGE_URL+"/images/topic/4.png");
                handler.sendEmptyMessage(0);
                // Toast.makeText(getContext(),"蛤",Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getContext(), "刷新了", Toast.LENGTH_SHORT).show();
            //}

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
        {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    private void loadBannerData()
    {
        RetrofitHelper.getApiService().getBannerApi()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bannerResp -> {
                    List<BannerResp.HomeTopicBean> data = bannerResp.getHomeTopic();
                    for (int i = 0; i < data.size(); i++)
                    {
                        mBannerImages.add(ApiService.BASE_URL + data.get(i).getPic());
                    }
                    initBanner();
                }, throwable -> {
                    Toast.makeText(mActivity, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                });
    }

    private List<String> mBannerImages = new ArrayList<>();

    /**
     * 初始化banner
     */
    private void initBanner()
    {
        mBanner.setImageLoader(new GlideImageLoader())
                .setImages(mBannerImages)
                .setBannerAnimation(Transformer.Accordion)
                .setDelayTime(7000)
                .start();
        mBanner.setOnBannerClickListener(this);
    }

    @Override
    protected int getLayoutId()
    {

        return R.layout.fragment_home;
    }

    public static HomeFragment newInstance()
    {

        return new HomeFragment();
    }

    /**
     * banner的点击事件
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position)
    {
        Toast.makeText(mActivity, "position:" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v)
    {
        Log.i("test", "我被点击了");
        Intent intent = null;
        switch (v.getId())
        {
            //限时抢购
            case R.id.tv_xsqg:
            case R.id.xsqg:
                Log.i("test", "限时抢购");
//                Intent intent2 = intent.setClass(getContext(), FlashSale1.class);
//                startActivity(intent2);
                intent = new Intent(getContext(), FlashSale1.class);
                startActivity(intent);
                break;
            //快报
            case R.id.tv_cxkb:
            case R.id.cxkb:
//                //这是促销快报
//                Intent intent1 = intent.setClass(getContext(), FlashSale.class);
//                startActivity(intent1);
                intent = new Intent(getContext(), FlashSale.class);
                startActivity(intent);
                break;
            //新品上架
            case R.id.tv_xpsj:
            case R.id.xpsj:
//                Intent intent4 = intent.setClass(getContext(), NewArrivalsActivity.class);
//                startActivity(intent4);
                intent = new Intent(getContext(), NewArrivalsActivity.class);
                startActivity(intent);
                break;
            //热门商品
            case R.id.tv_rmsp:
            case R.id.rmsp:
//                Intent intent3 = intent.setClass(getContext(), SingleProductActivity.class);
//                startActivity(intent3);
                intent = new Intent(getContext(), SingleProductActivity.class);
                startActivity(intent);
                break;
            //点击跳转商品详情
            case R.id.banner1:
//                intent.putExtra("pid", productlist.get(position).getId());
                //  intent=new Intent(getContext(), ProductDetailActivity.class);
            case R.id.imageView:
            case R.id.imageView2:
            case R.id.iv1:
            case R.id.iv2:
            case R.id.shampoo:
            case R.id.kou_hong:
            case R.id.kou_hong1:
            case R.id.kou_hong2:
            case R.id.yashua1:
            case R.id.shougongmu:
            case R.id.RecyclerView_Ry:
                intent = new Intent(mActivity, ProductDetailActivity.class);
                int i = new Random().nextInt(4)+1;
                intent.putExtra("pid",i);
                startActivity(intent);
                break;
        }

    }
}
