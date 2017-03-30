package com.market.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.market.R;
import com.market.bean.SearchResult;
import com.market.http.RetrofitHelper;
import com.market.utils.SPUtils;
import com.market.utils.XDialog;
import com.market.view.activity.MainActivity;
import com.market.view.activity.SearchActivity;
import com.market.view.adapter.CommonViewHolder;
import com.market.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by chenlong on 2016/12/20.
 */

public class SearchFragment extends BaseFragment
{

    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_cancel)
    TextView searchCancel;
    @BindView(R.id.search_rv_hot)
    RecyclerView searchRvHot;
    @BindView(R.id.search_tv_clean)
    TextView searchTvClean;
    @BindView(R.id.search_rv_history)
    RecyclerView searchRvHistory;
    @BindView(R.id.search_ll_normal)
    LinearLayout searchLlNormal;
    @BindView(R.id.search_empty)
    TextView searchEmpty;
    @BindView(R.id.search_rv_try)
    RecyclerView searchRvTry;
    @BindView(R.id.search_ll_empty)
    LinearLayout searchLlEmpty;

    private String[] names = {"陆谦", "家猪", "1", "2", "3", "原木平底杉木碟简约创意果盘GP-13", "川岛屋日式酸枣木大肚杯茶J_10", "简约创意插花瓶", "这是个啥", "这又是个啥", "真不知道是啥", "哈哈哈哈，要疯了"};
    boolean isON = true;
    public List<String> mSearchHistory;
    private CommonAdapter mMHotAdapter;
    private String search;
    private String itemText;

    @Override
    protected void onLazyLoad()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_search;
    }

    public static SearchFragment newInstance()
    {
        return new SearchFragment();
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        initHot();
        initHistory();
        initTry();

        searchIv.setOnClickListener(mOnClickListener);
        searchCancel.setOnClickListener(mOnClickListener2);
        searchTvClean.setOnClickListener(mOnClickListenerCancel);
    }

    /**
     * 初始化试试商品
     */
    private String[] trys = "粉色毛衣,护肤,洗面奶,奶粉,爽肤水,紧肤水,柔肤水,护肤霜,眼影,陆谦,唇膏,遮瑕膏,家猪,粉饼,BB霜,指甲油,粉底,UrZHU,卸妆乳".split(",");
    private List<String> mTry = new ArrayList<>();

    private void initTry()
    {
        for (String aTry : trys)
        {
            mTry.add(aTry);
        }
        searchRvTry.setLayoutManager(new GridLayoutManager(mActivity, 4));
        searchRvTry.setAdapter(new CommonAdapter(mTry));
        searchRvTry.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化搜索历史
     */
    private void initHistory()
    {
        mSearchHistory = SPUtils.getStrListValue(getContext(), "search");

        //list集合去重
        for  ( int  i  =   0 ; i  <  mSearchHistory.size()  -   1 ; i ++ )   {
            for  ( int  j  =  mSearchHistory.size()  -   1 ; j  >  i; j -- )   {
                if  (mSearchHistory.get(j).equals(mSearchHistory.get(i)))   {
                    mSearchHistory.remove(j);
                }
            }
        }


        searchRvHistory.setLayoutManager(new GridLayoutManager(mActivity, 3));
        searchRvHistory.setAdapter(new CommonAdapter(mSearchHistory));
        searchRvHistory.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化热搜
     */
    private List<String> mHotSearch = new ArrayList();

    private void initHot()
    {
        initHotData();
        searchRvHot.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mMHotAdapter = new CommonAdapter(mHotSearch);
        searchRvHot.setAdapter(mMHotAdapter);
        searchRvHot.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 连网获取热搜数据
     */
    protected void initHotData()
    {
        RetrofitHelper.getApiService().getSearchRecommendApi()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchRecommend -> {
                    mHotSearch.addAll(searchRecommend.getSearchKeywords());
                    mMHotAdapter.notifyDataSetChanged();
                }, throwable -> {
                    Toast.makeText(getActivity(), "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * 联网获取商品搜索结果的javabean,根据是否匹配到判断做相应操作
     */
    private void getData(String keyword)
    {
        RetrofitHelper.getApiService().getSearchResultApi(keyword, 1, 10, "saleDown")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResult -> {
                    List<SearchResult.ProductListBean> productList = searchResult.getProductList();
                    if (productList.size() == 0)
                    {
                        //切换到无商品页面
                        ToEmptyPage(keyword);
                    } else
                    {
                        //有数据就跳转页面
                        ToProductListPage(keyword);
                    }
                }, throwable -> {
                    Toast.makeText(getActivity(), "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * 切换到无商品页面
     */
    private void ToEmptyPage(String text)
    {

        searchLlEmpty.setVisibility(View.VISIBLE);
        searchLlNormal.setVisibility(View.INVISIBLE);
        searchEmpty.setText("未找到与  " + text + "  有关的商品");
        isON = false;
        Toast.makeText(getActivity(), "没有" + text + "这个商品", Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳到商品列表界面
     */
    private void ToProductListPage(String keyword)
    {
        Intent mIntent = new Intent(mActivity, SearchActivity.class);
        mIntent.putExtra("keyword", keyword);
        startActivity(mIntent);
    }

    /**
     * 搜索按钮点击事件
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
                //将数据添加到集合，并将集合存入SP中
                mSearchHistory.add(search);
                searchEmpty.setText(search);
                SPUtils.putStrListValue(getContext(), "search", mSearchHistory);
                //将结果显示在历史中
                initHistory();
                searchEt.setText(search);
                searchEt.setSelection(search.length());
                getData(search);

            } else
            {
                Toast.makeText(getContext(), "输入为空", Toast.LENGTH_SHORT).show();
            }
        }
    };
    /**
     * 返回按钮监听事件
     */
    private View.OnClickListener mOnClickListener2 = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if (isON == true)
            {
                ((MainActivity) getActivity()).showFragment(0);
            } else
            {
                searchLlNormal.setVisibility(View.VISIBLE);
                searchLlEmpty.setVisibility(View.INVISIBLE);
                isON = true;
            }
        }
    };

    /**
     * 清除按钮监听
     */
    private View.OnClickListener mOnClickListenerCancel = view -> {
        if (!mSearchHistory.isEmpty())
        {
            XDialog.Builder builder = new XDialog.Builder(getActivity());
            builder.setMessage("确定清空吗");
            builder.setTitle("提示");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                    //设置你的操作事项
                    SPUtils.removeStrList(getContext(), "search");
                    initHistory();
                }
            });

            builder.setNegativeButton("取消",
                    (dialog, which) -> dialog.dismiss());
            builder.create().show();
        } else
        {
            Toast.makeText(mActivity, "多次点击可以召唤神龙", Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 通用adapter
     */
    private class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> implements View.OnClickListener
    {
        private List<String> list = null;

        public CommonAdapter(List<String> list)
        {
            this.list = list;
        }

        private CommonViewHolder mCommonViewHolder;

        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View convertView = View.inflate(parent.getContext(), R.layout.search_item, null);
            mCommonViewHolder = new CommonViewHolder(convertView);
            return mCommonViewHolder;
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, int position)
        {
            holder.getTextView(R.id.search_tv_item).setText(list.get(position));
            holder.getTextView(R.id.search_tv_item).setOnClickListener(this);
        }

        @Override
        public int getItemCount()
        {
            return list == null ? 0 : list.size();
        }

        /**
         * 条目点击事件
         *
         * @param view
         */
        @Override
        public void onClick(View view)
        {
            itemText = ((TextView) view).getText().toString().trim();
            mSearchHistory.add(itemText);
            SPUtils.putStrListValue(getContext(), "search", mSearchHistory);
            initHistory();
            searchEt.setText(itemText);
            searchEt.setSelection(itemText.length());
            getData(itemText);
        }
    }
}


