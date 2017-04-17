package com.market.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.market.R;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.adapter.SimpeFragmentPagerAdapter;


public class SingleProductActivity extends BaseCompatActivity {
    private SimpeFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;


    @Override
    protected void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_product;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        pagerAdapter = new SimpeFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

}

