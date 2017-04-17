package com.market.view.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.market.R;

public class AboutActivity extends BaseCompatActivity {

    private TextView tv_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.set_up_about;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tv_about = ((TextView) findViewById(R.id.tv_about));
        tv_about.setOnClickListener(clickListener);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
