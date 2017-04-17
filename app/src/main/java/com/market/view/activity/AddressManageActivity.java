package com.market.view.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.market.R;
import com.market.bean.AddressBean;
import com.market.http.RetrofitHelper;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.activity.base.LoginActivity;
import com.market.view.adapter.AddressAdapter;

import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/9.
 */

public class AddressManageActivity extends BaseCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.rippleview)
    RippleView mRippleView;
    @BindView(R.id.tv_addAddress)
    TextView mTv_addAddress;
    @BindView(R.id.tv_back)
    TextView mTv_back;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.btn_addAddress)
    Button mBtn_addAddress;
    private AddressAdapter mAddressAdapter;
    private List<AddressBean.AddressListBean> mAddressBean;
    private AddressBean.AddressListBean  chooseAddressListBean;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_manage1;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RetrofitHelper.getApiService().getAddressListApi(Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                .getString(LoginActivity.USERId_LOGIN,"")))
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addressBean -> {
                    mAddressBean = addressBean.getAddressList();
                    intiData();
                }, throwable -> {
                    Toast.makeText(this, "亲,还没有联网哦", Toast.LENGTH_SHORT).show();
                });
        mTv_back.setOnClickListener(mOnClickListener);
}

    private void intiData() {
        if (mAddressBean == null || mAddressBean.size() == 0){
            rl.setVisibility(View.VISIBLE);
            mBtn_addAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AddressManageActivity.this, AddAddressActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else {
            mRippleView.setVisibility(View.VISIBLE);
            mTv_addAddress.setText("增加地址");
            mRippleView.setOnClickListener(mOnClickListener);
            //mTv_addAddress.setOnClickListener(mOnClickListener);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerview.setLayoutManager(layoutManager);
            //设置条目间距
            mAddressAdapter = new AddressAdapter(AddressManageActivity.this, mAddressBean);
            recyclerview.addItemDecoration(new SpaceItemDecoration(10));
            recyclerview.setAdapter(mAddressAdapter);

            mAddressAdapter.setOnclikedListener(mOnclikedListener);
        }
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_back:
                    finish();
                    break;
                case R.id.rippleview:
                    Intent intent = new Intent(AddressManageActivity.this, AddAddressActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };

    private AddressAdapter.OnclikedListener mOnclikedListener = new AddressAdapter.OnclikedListener() {
        @Override
        public void edit(AddressBean.AddressListBean addressListBean) {

            chooseAddressListBean = addressListBean;
            RetrofitHelper.getApiService().getAddressDeleteApi(addressListBean.getId(),Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                    .getString(LoginActivity.USERId_LOGIN,"")))
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(addressBean -> {
                        Intent intent = new Intent(AddressManageActivity.this,AddAddressActivity.class);
                        intent.putExtra("intent",chooseAddressListBean);
                        startActivity(intent);
                        mAddressBean.remove(addressListBean);
                        mAddressAdapter.notifyDataSetChanged();
                        finish();
                    }, throwable -> {
                        Toast.makeText(AddressManageActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                    });
        }

        @Override
        public void detele(AddressBean.AddressListBean addressListBean,View view) {


            RetrofitHelper.getApiService().getAddressDeleteApi(addressListBean.getId(),Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                    .getString(LoginActivity.USERId_LOGIN,"")))
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(addressBean -> {
                        mAddressBean.remove(addressListBean);
                        /*AlphaAnimation aa = new AlphaAnimation(1.0f,0.0f);
                        aa.setDuration(1000);
                        ScaleAnimation sa = new ScaleAnimation(1.0f,0.0f,1.0f,0.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                        sa.setDuration(1000);
                        AnimationSet set = new AnimationSet(false);
                        set.addAnimation(aa);
                        set.addAnimation(sa);
                        view.setAnimation(set);*/
                        mAddressAdapter.notifyDataSetChanged();
                    }, throwable -> {
                        Toast.makeText(AddressManageActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                    });
        }

        @Override
        public void itemData(AddressBean.AddressListBean addressListBean) {
            Intent intent= new Intent();
            intent.putExtra("data",addressListBean);
            setResult(101,intent);
            finish();
        }

        @Override
        public void checked(AddressBean.AddressListBean addressListBean) {
            RetrofitHelper.getApiService().getAddressDefaultApi(addressListBean.getId(),Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                    .getString(LoginActivity.USERId_LOGIN,"")))
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(addressBean -> {
                        RetrofitHelper.getApiService().addAddress(addressListBean.getId(), addressListBean.getName(),
                                addressListBean.getPhoneNumber(), addressListBean.getProvince(),
                                addressListBean.getCity(), addressListBean.getAddressArea(),
                                addressListBean.getAddressDetail(), addressListBean.getZipCode(),1,Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                                        .getString(LoginActivity.USERId_LOGIN,"")))
                                .compose(bindToLifecycle())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(mAddressListBean -> {
                                    if (addressListBean.getIsDefault()==1){
                                        addressListBean.setIsDefault(0);
                                    }else{
                                        for (int i = 0; i < mAddressBean.size(); i++) {
                                            mAddressBean.get(i).setIsDefault(0);
                                        }
                                        addressListBean.setIsDefault(1);
                                    }
                                    mAddressAdapter.notifyDataSetChanged();
                                }, throwable -> {
                                    Toast.makeText(AddressManageActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                                });
                    }, throwable -> {
                        Toast.makeText(AddressManageActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                    });
        }
    };

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace ;

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int itemCount = mAddressAdapter.getItemCount();
            int pos = parent.getChildAdapterPosition(view);

            outRect.left = 10;
            outRect.right = 10;
            outRect.bottom = 10;

            if (pos !=0) {
                outRect.top = mSpace;
            } else {
                outRect.top = 0;
            }
        }
    }

}