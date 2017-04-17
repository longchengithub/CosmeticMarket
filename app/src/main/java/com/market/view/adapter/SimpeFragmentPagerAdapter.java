package com.market.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.market.view.fragment.PageFragment;

/**
 * Created by wuwei on 2017/1/8.
 */

public  class SimpeFragmentPagerAdapter extends FragmentPagerAdapter {

    //final int PAGE_COUNT = 6;
    private String tabTitles[] = new String[]{"全部商品","面部护理","生活用品","时尚潮品","母婴用品","保健养生","人气零食"};

    private Context context;

    public SimpeFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}