package com.market.view.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.market.R;

/**
 * 设置的activity
 */
public class PersonalActivity extends BaseCompatActivity {

    private RelativeLayout setting_personal;
    private RelativeLayout relative_store;
    private RelativeLayout help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setting_personal = ((RelativeLayout) findViewById(R.id.setting_personal));
        setting_personal.setOnClickListener(clickListener);
        relative_store = ((RelativeLayout) findViewById(R.id.store));
        relative_store.setOnClickListener(storeListener);
        help = ((RelativeLayout) findViewById(R.id.help));
        help.setOnClickListener(helpListener);
    }
    private View.OnClickListener helpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PersonalActivity.this,HelpActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener storeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PersonalActivity.this,StoreActivity.class);
            startActivity(intent);
        }
    };
    public void click_quit(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("250","");
        startActivity(intent);
    }
    public void click_personal_fanhui(View view){
        finish();
    }
    public void click_feedback(View view){
        Intent intent = new Intent(this,FeedbackActivity.class);
        startActivity(intent);
    }
    public void click_guanyu(View view){
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PersonalActivity.this,PersonalDataActivity.class);
            startActivity(intent);
        }
    };
}
