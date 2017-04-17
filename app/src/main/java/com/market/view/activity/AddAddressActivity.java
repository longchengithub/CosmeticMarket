package com.market.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.market.R;
import com.market.bean.AddressBean;
import com.market.http.RetrofitHelper;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.activity.base.LoginActivity;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/9.
 */
public class AddAddressActivity extends BaseCompatActivity {
   @BindView(R.id.tv_rippleview)
    RippleView mTv_rippleview;
    @BindView(R.id.tv_back)
    TextView mTv_back;
    @BindView(R.id.tv_title)
    TextView mTv_title;
    @BindView(R.id.et_consignee)
    EditText mEt_consignee;
    @BindView(R.id.et_number)
    EditText mEt_number;
    @BindView(R.id.et_province)
    EditText mEt_province;
    @BindView(R.id.et_city)
    EditText mEt_city;
    @BindView(R.id.tv_save)
    TextView mTv_save;
private AddressBean.AddressListBean newAddressListBean ;
    private AddressBean.AddressListBean mAddressListBean;
    private String test;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mAddressListBean = (AddressBean.AddressListBean) intent.getSerializableExtra("intent");
        mTv_back.setText("地址管理");

        if (mAddressListBean == null) {
            mTv_title.setText("新增地址");
        } else {
            mTv_title.setText("修改地址");

            mEt_consignee.setText(mAddressListBean.getName());
            mEt_number.setText(mAddressListBean.getPhoneNumber());
            mEt_province.setText(mAddressListBean.getProvince() + mAddressListBean.getCity());
            mEt_city.setText(mAddressListBean.getAddressArea() + mAddressListBean.getAddressDetail());
        }
        mTv_back.setOnClickListener(mOnClickListener);
        mTv_rippleview.setOnClickListener(mOnClickListener);

        mEt_consignee.setSelection(mEt_consignee.getText().length());
        mEt_number.setSelection(mEt_number.getText().length());
        mEt_province.setSelection(mEt_province.getText().length());
        mEt_city.setSelection(mEt_city.getText().length());
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = mEt_consignee.getText().toString().trim();
            String number = mEt_number.getText().toString().trim();
            String province = mEt_province.getText().toString().trim();
            String city = mEt_city.getText().toString().trim();



            switch (view.getId()) {
                case R.id.tv_back:
                    if (mAddressListBean == null){
                        startAddressManageActivity();
                        Toast.makeText(AddAddressActivity.this, "您没有任何变动哦", Toast.LENGTH_SHORT).show();
                       finish();
                    }else {
                        newAddressListBean = new AddressBean.AddressListBean("",city,"",0,mAddressListBean.getIsDefault() == 0?0:1,name,number,province,"");
                        Toast.makeText(AddAddressActivity.this, "您没有任何变动哦", Toast.LENGTH_SHORT).show();
                        RetrofitHelper.getApiService().addAddress(newAddressListBean.getId(), newAddressListBean.getName(),
                                newAddressListBean.getPhoneNumber(), newAddressListBean.getProvince(),
                                newAddressListBean.getCity(), newAddressListBean.getAddressArea(),
                                newAddressListBean.getAddressDetail(), newAddressListBean.getZipCode(),
                                newAddressListBean.getIsDefault(),Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                        .getString(LoginActivity.USERId_LOGIN,"")))
                                .compose(bindToLifecycle())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(mAddressListBean -> {
                                    startAddressManageActivity();
                                }, throwable -> {
                                    Toast.makeText(AddAddressActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                                });
                    }
                    break;
                case R.id.tv_rippleview:
                    newAddressListBean = new AddressBean.AddressListBean("",city,"",0,0,name,number,province,"");
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number) && !TextUtils.isEmpty(province) && !TextUtils.isEmpty(city)) {
                        if (mAddressListBean == null){
                            RetrofitHelper.getApiService().addAddress(newAddressListBean.getId(), newAddressListBean.getName(),
                                    newAddressListBean.getPhoneNumber(), newAddressListBean.getProvince(),
                                    newAddressListBean.getCity(), newAddressListBean.getAddressArea(),
                                    newAddressListBean.getAddressDetail(), newAddressListBean.getZipCode(),
                                    newAddressListBean.getIsDefault(),Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                                            .getString(LoginActivity.USERId_LOGIN,"")))
                                    .compose(bindToLifecycle())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(mAddressListBean -> {
                                        startAddressManageActivity();
                                    }, throwable -> {
                                        Toast.makeText(AddAddressActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                                    });
                        }else {
                            RetrofitHelper.getApiService().addAddress(newAddressListBean.getId(), newAddressListBean.getName(),
                                    newAddressListBean.getPhoneNumber(), newAddressListBean.getProvince(),
                                    newAddressListBean.getCity(), newAddressListBean.getAddressArea(),
                                    newAddressListBean.getAddressDetail(), newAddressListBean.getZipCode(),
                                    newAddressListBean.getIsDefault(),Integer.parseInt(getSharedPreferences(LoginActivity.TEXT,MODE_PRIVATE)
                                            .getString(LoginActivity.USERId_LOGIN,"")))
                                    .compose(bindToLifecycle())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(mAddressListBean -> {
                                        startAddressManageActivity();
                                    }, throwable -> {
                                        Toast.makeText(AddAddressActivity.this, "联网失败!请检查ip是否正确或是否开启服务器", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    }
                    break;
            }
        }
    };
    private void startAddressManageActivity(){
        Intent intent = new Intent(AddAddressActivity.this,AddressManageActivity.class);
        startActivity(intent);
        finish();
    }
}
