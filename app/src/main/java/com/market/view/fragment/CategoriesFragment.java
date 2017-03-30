package com.market.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.market.R;
import com.market.bean.CategoryResp;
import com.market.bean.ThirdLevelMenuBean;
import com.market.http.ApiService;
import com.market.http.RetrofitHelper;
import com.market.view.activity.MianMoActivity;
import com.market.view.adapter.CommonViewHolder;
import com.market.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlong on 2016/12/20.
 */

public class CategoriesFragment extends BaseFragment
{

    @BindView(R.id.scanner)
    TextView mScanner;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.recycler_category)
    RecyclerView mRecycler;

    private CateAdapter mCateAdapter;
    private List<CategoryResp.CategoryBean> topLevel;
    private List<CategoryResp.CategoryBean> secondLevel;
    private List<CategoryResp.CategoryBean> thirdLevel;
    private List<CategoryResp.CategoryBean> category;

    @Override
    protected void onLazyLoad()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        loadData();
    }

    private void loadData()
    {
        RetrofitHelper.getApiService()
                .getCategoryRespApi()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryResp -> {
                    category = categoryResp.getCategory();
                    finishTask(category);
                }, throwable -> {
                    Toast.makeText(mActivity, "联网失败或ip或访问地址有误", Toast.LENGTH_SHORT).show();
                });
    }

    private void finishTask(List<CategoryResp.CategoryBean> category)
    {
        topLevel = new ArrayList<>();
        secondLevel=new ArrayList<>();
        thirdLevel=new ArrayList<>();

        for (int i = 0; i < category.size(); i++)
        {
            if (category.get(i).getParentId() == 0)     //所有的一级菜单
            {
                topLevel.add(category.get(i));
            } else if (category.get(i).getParentId() != 0 && category.get(i).isIsLeafNode() == false)
            {
                secondLevel.add(category.get(i));
            } else                                     //所有的三级菜单
            {
                thirdLevel.add(category.get(i));
            }
        }

        mRecycler.setLayoutManager(new LinearLayoutManager(mActivity));
        mCateAdapter = new CateAdapter();
        mRecycler.setAdapter(mCateAdapter);
        mCateAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_categories;
    }

    public static CategoriesFragment newInstance()
    {
        return new CategoriesFragment();
    }

    private class CateAdapter extends RecyclerView.Adapter<CommonViewHolder>
    {
        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = View.inflate(parent.getContext(), R.layout.item_category, null);
            CommonViewHolder viewHolder = new CommonViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, int position)
        {
            CategoryResp.CategoryBean categoryBean = topLevel.get(position);
            holder.getTextView(R.id.item_title).setText(categoryBean.getName());
            Glide.with(mActivity)
                    .load(ApiService.IMAGE_URL + categoryBean.getPic())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.getImageView(R.id.iv_product));
            holder.getTextView(R.id.tv_product).setText(categoryBean.getTag());





            ThirdLevelMenuBean tempSecond = new ThirdLevelMenuBean();   //2级对象
            List<ThirdLevelMenuBean.SecondLevelMenuBean> bean = new ArrayList<>();

            for (int i = 0; i < secondLevel.size(); i++)
            {
                CategoryResp.CategoryBean data = secondLevel.get(i);
                //是2级菜单
                if (categoryBean.getId() == data.getParentId())         //单个2级菜单
                {
                    //循环第三集菜单
                    ThirdLevelMenuBean.SecondLevelMenuBean secondLevelMenuBean = new ThirdLevelMenuBean.SecondLevelMenuBean();
                    List<Integer> thirdId = new ArrayList<>();
                    for (int i1 = 0; i1 < thirdLevel.size(); i1++)      //2级循环3级
                    {
                        CategoryResp.CategoryBean categoryBean1 = thirdLevel.get(i1);
                        if (categoryBean1.getParentId() == data.getId())
                        {
                            thirdId.add(categoryBean1.getId());
                        }
                        secondLevelMenuBean.setSecondLevelName(data.getName());
                        secondLevelMenuBean.setThirdId(thirdId);
                    }
                    bean.add(secondLevelMenuBean);
                }
            }

            tempSecond.setSecondBean(bean);
            Log.d("CateAdapter", "tempSecond:" + tempSecond);


            holder.getTextView(R.id.item_title).setOnClickListener(v -> {

                if(topLevel.size()==0){
                    Toast.makeText(mActivity, "暂无商品", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(mActivity, MianMoActivity.class);
                intent.putExtra("menu", tempSecond);
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount()
        {
            return topLevel.size();
        }
    }
}
