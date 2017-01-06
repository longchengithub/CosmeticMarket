package com.market.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.market.R;
import com.market.view.activity.base.BaseCompatActivity;
import com.market.view.fragment.CategoriesFragment;
import com.market.view.fragment.HomeFragment;
import com.market.view.fragment.SearchFragment;
import com.market.view.fragment.SettingFragment;
import com.market.view.fragment.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by chenlong on 2017/1/5.
 */
public class MainActivity extends BaseCompatActivity
{
    @BindView(R.id.main_frameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView mBottomNavigation;

    @Override
    protected void initToolBar()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        initFragments();    //初始化fragments

        initNavigation();   //初始化底部导航栏

    }

    /**
     * 初始化底部导航栏
     */
    private void initNavigation()
    {
        BottomNavigationItem home = new BottomNavigationItem("首页", Color.RED, R.drawable.ic_home_black_24dp);
        BottomNavigationItem search = new BottomNavigationItem("搜索", Color.YELLOW, R.drawable.ic_search_black_24dp);
        BottomNavigationItem categories = new BottomNavigationItem("分类", Color.BLUE, R.drawable.ic_categories_black_24dp);
        BottomNavigationItem shopping = new BottomNavigationItem("购物", Color.GREEN, R.drawable.ic_shopping_black_24dp);
        BottomNavigationItem setting = new BottomNavigationItem("我的", Color.BLACK, R.drawable.ic_account_black_24dp);

        mBottomNavigation.addTab(home);
        mBottomNavigation.addTab(search);
        mBottomNavigation.addTab(categories);
        mBottomNavigation.addTab(shopping);
        mBottomNavigation.addTab(setting);

        mBottomNavigation.isColoredBackground(false);   //不显示整个的背景色
        mBottomNavigation.isWithText(true);             //文本一直显示
        //设置当前选中的图标的背景色 without(不包括)整个底部导航栏的颜色
        mBottomNavigation.setItemActiveColorWithoutColoredBackground(getResources().getColor(R.color.gray));

        //点击事件
        mBottomNavigation.setOnBottomNavigationItemClickListener(index -> {
            showFragment(index);
        });
    }

    private List<Fragment> mFragments = new ArrayList<>();
    private int preIndex;

    /**
     * 初始化fragment
     */
    private void initFragments()
    {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(SearchFragment.newInstance());
        mFragments.add(CategoriesFragment.newInstance());
        mFragments.add(ShoppingFragment.newInstance());
        mFragments.add(SettingFragment.newInstance());

        //默认选中第一个fragment
        showFragment(0);
    }

    private void showFragment(int index)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!mFragments.get(index).isAdded())
        {
            transaction.add(R.id.main_frameLayout, mFragments.get(index));
        }

        transaction.hide(mFragments.get(preIndex)).show(mFragments.get(index)).commit();

        preIndex = index;
    }

}
