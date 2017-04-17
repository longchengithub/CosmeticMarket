package com.market.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.market.R;
import com.market.bean.ProductListResp;
import com.market.bean.ThirdLevelMenuBean;
import com.market.http.ApiService;
import com.market.http.RetrofitHelper;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.adapter.CommonViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlong on 2017/1/7.
 */
public class MianMoActivity extends BaseCompatActivity implements TabLayout.OnTabSelectedListener
{
    @BindView(R.id.back)
    TextView mBack;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.recycler_mianmo)
    RecyclerView mRecycler;
    @BindView(R.id.swipe_mianmo)
    SwipeRefreshLayout mSwipe;

    private ProductListAdapter mAdapter;
    private List<ThirdLevelMenuBean.SecondLevelMenuBean> secondBean;
    private int cid;

    @Override
    protected void initToolBar()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_mianmo;
    }


    private List<String> secondName = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        Serializable menu = getIntent().getSerializableExtra("menu");

        if (menu == null)
        {
            Toast.makeText(this, "中转数据失败,我草", Toast.LENGTH_SHORT).show();
            return;
        }
        ThirdLevelMenuBean menuBean = (ThirdLevelMenuBean) menu;
        secondBean = menuBean.getSecondBean();
        for (int i = 0; i < secondBean.size(); i++)
        {
            secondName.add(secondBean.get(i).getSecondLevelName());
        }

        //数据为空或 没数据
        if (secondBean.size() == 0 || secondBean.get(0).getThirdId().size() == 0)
        {
            Toast.makeText(this, "暂无商品", Toast.LENGTH_SHORT).show();
            return;
        }

        initTab();

        initSwipe();
    }

    private void initSwipe()
    {
        mSwipe.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        mSwipe.setOnRefreshListener(() -> {
            Observable.timer(5, TimeUnit.SECONDS)
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        mSwipe.setRefreshing(false);
                    });

        });
    }

    private int page = 0;

    private void loadData()
    {
        RetrofitHelper.getApiService()
                .getProductList(page, 10, cid, "saleDown")
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productListResp -> {
                    finishTask(productListResp.getProductList());
                }, throwable -> {
                    Toast.makeText(this, "联网失败!ip地址,url错误或无服务器", Toast.LENGTH_SHORT).show();
                });
    }

    private void finishTask(List<ProductListResp.ProductListBean> productlist)
    {
        mAdapter = new ProductListAdapter(productlist);
        mRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        mRecycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void initTab()
    {
        for (int i = 0; i < secondName.size(); i++)
        {
            TabLayout.Tab tab = mTab.newTab();
            tab.setText(secondName.get(i));
            mTab.addTab(tab);
        }
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        mTab.addOnTabSelectedListener(this);
        cid = secondBean.get(0).getThirdId().get(0);
        loadData();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        int position = tab.getPosition();
        cid = secondBean.get(0).getThirdId().get(position);
        loadData();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab)
    {

    }

    private class ProductListAdapter extends RecyclerView.Adapter<CommonViewHolder>
    {
        private List<ProductListResp.ProductListBean> productlist;

        public ProductListAdapter(List<ProductListResp.ProductListBean> productlist)
        {
            this.productlist = productlist;
        }

        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = View.inflate(parent.getContext(), R.layout.item_mianmo, null);
            CommonViewHolder viewHolder = new CommonViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, int position)
        {
            holder.getCardView(R.id.cardView).setOnClickListener(v -> {
                mSwipe.setRefreshing(false);
                //todo 跳转到商品详情页面的 intent
                Intent intent = new Intent(MianMoActivity.this, ProductDetailActivity.class);
                int i = new Random().nextInt(4) + 1;
                intent.putExtra("pid", i);
                startActivity(intent);
            });
            Glide.with(MianMoActivity.this)
                    .load(ApiService.BASE_URL + productlist.get(position).getPic())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.getImageView(R.id.iv_mianmo));
            holder.getTextView(R.id.desc).setText(productlist.get(position).getName());
            holder.getTextView(R.id.price).setText("￥" + productlist.get(position).getPrice());
        }

        @Override
        public int getItemCount()
        {
            return productlist == null ? 0 : productlist.size();
        }
    }
}
