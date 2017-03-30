package com.market.view.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.market.CosmeticMarketApp;
import com.market.R;
import com.market.bean.ProductResp;
import com.market.dao.CarStore;
import com.market.dao.CarStoreDao;
import com.market.http.ApiService;
import com.market.http.RetrofitHelper;
import com.market.utils.DisplayUtil;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.adapter.CommonViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenlong on 2017/1/8.
 */
public class ProductDetailActivity extends BaseCompatActivity
{
    @BindView(R.id.iv_product)
    ImageView mProductImage;
    @BindView(R.id.recycler_recommend)
    RecyclerView mRecommend;
    @BindView(R.id.recycler_details)
    RecyclerView mDetails;
    @BindView(R.id.back)
    ImageView mIvBack;
    @BindView(R.id.share)
    ImageView mIvShare;
    @BindView(R.id.tv_product_detail)
    TextView mTvProductDetail;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_market_price)
    TextView mTvMarketPrice;
    @BindView(R.id.tv_address_send)
    TextView mTvAddressSend;
    @BindView(R.id.tv_num_less)
    TextView mTvNumLess;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.tv_num_more)
    TextView mTvNumMore;
    @BindView(R.id.rg_color_red)
    RadioButton mRbColorRed;
    @BindView(R.id.rg_color_green)
    RadioButton mRbColorGreen;
    @BindView(R.id.rg_color)
    RadioGroup mRgColor;
    @BindView(R.id.rg_size_m)
    RadioButton mRbSizeM;
    @BindView(R.id.rg_size_xxl)
    RadioButton mRbSizeXxl;
    @BindView(R.id.rg_size_xxxl)
    RadioButton mRbSizeXxxl;
    @BindView(R.id.rg_size)
    RadioGroup mRgSize;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private RecommendAdapter mRecommendAdapter;
    private DetailAdapter mDetailAdapter;
    private int pid;

    @Override
    protected void initToolBar()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        pid = getIntent().getIntExtra("pid", 0);
        if (pid == 0)
        {
            return;
        } else
        {
            loadData(pid);
        }

    }

    private void loadData(int pid)
    {
        RetrofitHelper.getApiService()
                .getProductDetailsApi(pid)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productResp -> {
                    ProductResp.ProductBean product = productResp.getProduct();
                    finishTask(product);
                }, throwable -> {
                    Toast.makeText(this, "联网失败", Toast.LENGTH_SHORT).show();
                });
    }

    private void finishTask(ProductResp.ProductBean productDetail)
    {
        initTitleImage(productDetail.getBigPic());

        initRecyclerRecommend(productDetail.getPics());

        initRecyclerDetail(productDetail.getBigPic());

        mTvProductDetail.setText(productDetail.getName());
        mTvPrice.setText("￥" + productDetail.getPrice());
        mTvMarketPrice.setText("国内参考价:￥" + productDetail.getMarketPrice());
        mTvAddressSend.setText(productDetail.getInventoryArea());
    }

    private void initRecyclerDetail(List<String> productdetails)
    {
        mDetailAdapter = new DetailAdapter(productdetails);
        mDetails.setLayoutManager(new LinearLayoutManager(this));
        mDetails.setAdapter(mDetailAdapter);
        mDetailAdapter.notifyDataSetChanged();
    }

    private void initRecyclerRecommend(List<String> productrecommend)
    {
        mRecommendAdapter = new RecommendAdapter(productrecommend);
        mRecommend.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecommend.addItemDecoration(new SpaceItemDecoration(10));
        mRecommend.setAdapter(mRecommendAdapter);
        mRecommendAdapter.notifyDataSetChanged();
    }

    private void initTitleImage(List<String> bigPic)
    {
        Glide.with(this)
                .load(ApiService.IMAGE_URL + bigPic.get(0))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(mProductImage);
    }

    private int prevColorId = 0;
    private int prevSizeId = 0;
    private int prevCount = 1;
    private boolean isStore2Car;        //是否加入了购物车

    @OnClick({R.id.tv_num_less, R.id.tv_num_more, R.id.rg_color_red, R.id.rg_color_green, R.id.rg_size_m, R.id.rg_size_xxl, R.id.rg_size_xxxl, R.id.fab})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_num_less:
                if (prevCount == 1)
                {
                    return;
                } else
                {
                    prevCount--;
                    updateCount();
                }
                break;
            case R.id.tv_num_more:
                if (prevCount == 10)
                {
                    Toast.makeText(this, "最多只有10件", Toast.LENGTH_SHORT).show();

                    return;
                } else
                {
                    prevCount++;
                    updateCount();
                }
                break;
            case R.id.rg_color_red:
                prevColorId = 1;
                break;
            case R.id.rg_color_green:
                prevColorId = 2;
                break;
            case R.id.rg_size_m:
                prevSizeId = 3;
                break;
            case R.id.rg_size_xxl:
                prevSizeId = 4;
                break;
            case R.id.rg_size_xxxl:
                prevSizeId = 5;
                break;
            case R.id.fab:

                if (prevColorId == 0 || prevSizeId == 0)
                {
                    Toast.makeText(this, "请选择商品的尺码和颜色啊!亲!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isStore2Car)
                {
                    Toast.makeText(this, "宝贝已加入购物车", Toast.LENGTH_SHORT).show();
                    return;
                }

                mFab.setImageResource(R.drawable.ic_favorite_red_24dp);
                Toast.makeText(this, "宝贝已收藏" +
                        "\n GreenDao数据已添加:" + pid + ":" + prevCount
                        + ":" + prevColorId + "," + prevSizeId, Toast.LENGTH_LONG).show();

//                DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "CarStore.db", null);
//                SQLiteDatabase writableDatabase = helper.getWritableDatabase();
//                DaoMaster master = new DaoMaster(writableDatabase);
//                CarStoreDao carStoreDao = master.newSession().getCarStoreDao();
                CarStoreDao carStoreDao = CosmeticMarketApp.getDaoSession(this).getCarStoreDao();

                CarStore store = new CarStore();
                store.setProduct_detail(pid + ":" + prevCount + ":" + prevColorId + "," + prevSizeId);
                carStoreDao.insert(store);
                isStore2Car = true;
                break;
        }
    }

    private void updateCount()
    {
        mTvNum.setText("" + prevCount);
    }

    private class RecommendAdapter extends RecyclerView.Adapter<CommonViewHolder>
    {
        private List<String> productrecommend;

        public RecommendAdapter(List<String> productrecommend)
        {
            this.productrecommend = productrecommend;
        }

        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = View.inflate(parent.getContext(), R.layout.item_image, null);
            CommonViewHolder viewHolder = new CommonViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, int position)
        {
            Glide.with(ProductDetailActivity.this)
                    .load(ApiService.IMAGE_URL + productrecommend.get(position))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.getImageView(R.id.iv_recommend));
        }

        @Override
        public int getItemCount()
        {
            return productrecommend.size();
        }
    }

    private class DetailAdapter extends RecyclerView.Adapter<CommonViewHolder>
    {
        private List<String> productdetails;

        public DetailAdapter(List<String> productdetails)
        {
            this.productdetails = productdetails;
        }

        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = View.inflate(parent.getContext(), R.layout.item_image_full, null);
            CommonViewHolder viewHolder = new CommonViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, int position)
        {
            Glide.with(ProductDetailActivity.this)
                    .load(ApiService.IMAGE_URL + productdetails.get(position))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.getImageView(R.id.iv_details));
        }

        @Override
        public int getItemCount()
        {
            return productdetails.size();
        }
    }

    /**
     * recycler设置间距
     */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration
    {
        int mSpace;

        /**
         * @param space 传入的值，其单位视为dp
         */
        public SpaceItemDecoration(int space)
        {
            this.mSpace = DisplayUtil.dp2px(ProductDetailActivity.this, space);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            int pos = parent.getChildAdapterPosition(view);

            outRect.left = mSpace;
            outRect.top = mSpace;
            outRect.bottom = mSpace;
            outRect.right = mSpace;
        }
    }
}
