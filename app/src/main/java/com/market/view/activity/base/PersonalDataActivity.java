package com.market.view.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.market.R;

public class PersonalDataActivity extends BaseCompatActivity {

    private RoundedImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        iv = ((RoundedImageView) findViewById(R.id.meinv));
        iv.setOval(true);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
    }
    public void click_personal_data_fanhui(View view){
        finish();
    }
}
