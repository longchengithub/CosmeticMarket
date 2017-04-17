package com.market.view.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.market.R;

public class FeedbackActivity extends BaseCompatActivity {

    private TextView tv_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.set_up_user_feedback;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tv_fanhui = ((TextView) findViewById(R.id.tv_fanhui));
        tv_fanhui.setOnClickListener(clickListener);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    public void click_up_feedback(View view){
        Toast.makeText(this, "您的意见已经保存,请保持电话畅通哦,亲~", Toast.LENGTH_SHORT).show();
    }
}
