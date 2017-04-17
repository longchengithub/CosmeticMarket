package com.market.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.market.R;
import com.market.bean.SearchResult;
import com.market.http.ApiService;
import com.market.http.RetrofitHelper;
import com.market.view.adapter.CommonViewHolder;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener
{

    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_cancel)
    TextView searchCancel;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;
    @BindView(R.id.result_rv)
    RecyclerView resultRv;
    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        searchIv.setOnClickListener(mOnClickListener);
        searchCancel.setOnClickListener(this);
        tabLayout.addOnTabSelectedListener(this);
        initTab();
        initPre();

        resultRv.addItemDecoration(new DividerItemDecoration(SearchActivity.this, DividerItemDecoration.VERTICAL));
    }

    private void initPre()
    {
        String keyword = getIntent().getStringExtra("keyword");
        searchEt.setText(keyword);
        search = keyword;
        initItem(keyword, "saleDown");
    }

    public void initTab()
    {
        String[] titles = "  销  量,  价  格,  评  价,  上  架".split(",");
        for (int i = 0; i < titles.length; i++)
        {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
    }

    /**
     * 条目监听事件
     *
     * @param tab
     */
    private boolean isDown = false;

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        switch (tab.getPosition())
        {
            case 0:
                initItem(search, "saleDown");
                break;
            case 1:
                initItem(search, "priceDown");
                break;
            case 2:
                initItem(search, "commentDown");
                break;
            case 3:
                initItem(search, "shelvesDown");
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab)
    {
        if (tab.getPosition() == 1)
        {
            if (isDown)
            {
                isDown = false;
                initItem(search, "priceDown");
            } else
            {
                isDown = true;
                initItem(search, "priceUp");
            }
        }
    }

    /**
     * 联网获取商品搜索结果的javabean
     */
    int page = 1;
    int pageNum = 10;

    private void initItem(String keyword, String orderby)
    {
        RetrofitHelper.getApiService().getSearchResultApi(keyword, page, pageNum, orderby)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResult -> {
                    List<SearchResult.ProductListBean> productList = searchResult.getProductList();
                    if (productList.size() == 0)
                    {
                        Toast.makeText(this, "没有" + keyword + "这个商品", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                    initRecycleView(productList);
                }, throwable -> {
                    Toast.makeText(SearchActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * 设置条目内容
     */
    private void initRecycleView(List<SearchResult.ProductListBean> productList)
    {
        for (SearchResult.ProductListBean productListBean : productList)
        {
            Log.d("SearchActivity", "productListBean.getPrice():" + productListBean.getPrice());
        }
        resultRv.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
        resultRv.setAdapter(new CommonAdapter(productList));
        resultRv.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 通用adapter
     */
    private class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> implements View.OnClickListener
    {
        private List<SearchResult.ProductListBean> productListBeans;

        public CommonAdapter(List<SearchResult.ProductListBean> productListBeans)
        {
            this.productListBeans = productListBeans;
        }

        private CommonViewHolder mCommonViewHolder;

        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View mConvertView = View.inflate(parent.getContext(), R.layout.search_result_item, null);
            mCommonViewHolder = new CommonViewHolder(mConvertView);
            return mCommonViewHolder;
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, int position)
        {
            SearchResult.ProductListBean productListBean = productListBeans.get(position);
            holder.getTextView(R.id.tv_1).setText(productListBean.getName());
            Glide.with(SearchActivity.this).load(ApiService.BASE_URL + productListBean.getPic()).fitCenter().into(holder.getImageView(R.id.result_iv));
            holder.getTextView(R.id.tv_3).setText(productListBean.getPrice() + "");
            holder.getLinearLayout(R.id.result_ll).setOnClickListener(this);
        }

        @Override
        public int getItemCount()
        {
            return productListBeans == null ? 0 : productListBeans.size();
        }

        /**
         * 条目点击事件
         *
         * @param view
         */
        @Override
        public void onClick(View view)
        {
            int random = new Random().nextInt(4) + 1;
            Intent intent = new Intent(SearchActivity.this, ProductDetailActivity.class);
            intent.putExtra("pid", random);
            startActivity(intent);
        }
    }

    /**
     * 搜索事件
     */
    private View.OnClickListener mOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            //隐藏输入法
            InputMethodManager imm = (InputMethodManager) searchEt.getContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchEt.getWindowToken(), 0);

            search = searchEt.getText().toString().trim();
            if (!TextUtils.isEmpty(search))
            {
                //输入非空就进行连网
                initItem(search, "saleDown");
            } else
            {
                Toast.makeText(SearchActivity.this, "输入为空，请重新输入", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * 返回键监听
     *
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        finish();
    }
}
