package com.market.view.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.market.R;
import com.market.bean.Qiangou;
import com.market.http.ApiService;
import com.market.http.RetrofitHelper;
import com.market.utils.GlideImageLoader;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.adapter.FlashSale1Adapter;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.market.R.id.rcv_flashsale;

/**
 * Created by Administrator on 2017/1/9.
 */ //限时抢购
public class FlashSale1  extends BaseCompatActivity implements OnBannerClickListener,OnClickListener {
    private RecyclerView rcy;
    private ImageView back;
    @BindView(R.id.back)
    ImageView back1;
    @BindView(R.id.fbanner)
    Banner fbanner;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    private  int start=0;
    private int end=6;
    private List<String> list;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {

        return R.layout.flashsale_1activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        back1.setOnClickListener(this);
        button1.setSelected(true);
        //获取网络数据加载到适配器
        loadBannerData();
        rcy = (RecyclerView) findViewById(rcv_flashsale);
        back = (ImageView) findViewById(R.id.back);
        rcy.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        ininData(start,end);

    }

    private List<String> ininData(int start, int end) {
        list=new ArrayList<>();
        RetrofitHelper.getApiService().getQianggouimges(2,6).compose(bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(qiangou -> {
            List<Qiangou.ProductListEntity> productList = qiangou.getProductList();
            FlashSale1Adapter flashSale1Adapter = new FlashSale1Adapter(this, productList);
            rcy.setAdapter(flashSale1Adapter);
        },throwable -> {
            Toast.makeText(this,"加载失败",Toast.LENGTH_SHORT).show();
        }
        );
        return list;
    }


    private void loadBannerData()
    {
        RetrofitHelper.getApiService().getQianggouimges(1,5)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(qiangou ->   {
                    List<Qiangou.ProductListEntity> productList = qiangou.getProductList();
                    for (int i = 0; i <productList.size() ; i++) {
                        Qiangou.ProductListEntity productListEntity = productList.get(i);
                        String pic = productListEntity.getPic();
                        mBannerImages.add(ApiService.IMAGE_URL+pic);
                    }
                    initBanner();
                }, throwable -> {
                    Toast.makeText(getBaseContext(), "加载失败了,请检查你的网络", Toast.LENGTH_SHORT).show();
                });
    }
    private List<String> mBannerImages = new ArrayList<>();

    /**
     * 初始化banner
     */
    private void initBanner()
    {
        fbanner.setImageLoader(new GlideImageLoader())
                .setImages(mBannerImages)
                .setBannerAnimation(Transformer.Accordion)
                .setDelayTime(7000)
                .start();
        fbanner.setOnBannerClickListener(this);
    }
    /**
     * banner的点击事件
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position)
    {
        //Toast.makeText(getBaseContext(), "position:" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.button1:
                button1.setSelected(true);
                button2.setSelected(false);
                button3.setSelected(false);
                button4.setSelected(false);
                //创建适配器1
                break;
            case  R.id.button2:
                button2.setSelected(true);
                button1.setSelected(false);
                button3.setSelected(false);
                button4.setSelected(false);
                //创建适配器2
                break;
            case  R.id.button3:
                button3.setSelected(true);
                button1.setSelected(false);
                button2.setSelected(false);
                button4.setSelected(false);
                //创建适配器3
                break;
            case  R.id.button4:
                button4.setSelected(true);
                button1.setSelected(false);
                button2.setSelected(false);
                button3.setSelected(false);
                //创建适配器4

                break;
            case  R.id.back:
                finish();
                break;
        }
    }


}
