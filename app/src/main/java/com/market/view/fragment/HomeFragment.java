package com.market.view.fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.market.R;
import com.market.bean.BannerResp;
import com.market.http.ApiService;
import com.market.http.RetrofitHelper;
import com.market.utils.GlideImageLoader;
import com.market.view.fragment.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlong on 2016/12/20.
 */

public class HomeFragment extends BaseFragment implements OnBannerClickListener
{


    @BindView(R.id.banner)
    Banner mBanner;

    @Override
    protected void onLazyLoad()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        loadBannerData();   //联网加载banner的数据


    }

    private void loadBannerData()
    {
        RetrofitHelper.getApiService().getBannerApi()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bannerResp -> {
                    List<BannerResp.DataBean> data = bannerResp.getData();
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
}
