package com.market.view.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.market.R;

public class HelpActivity extends BaseCompatActivity {

    private TextView tv_help_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.help_heart;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tv_help_fanhui = ((TextView) findViewById(R.id.tvhelp));
        tv_help_fanhui.setOnClickListener(clickListener);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
