package com.market.view.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenlong on 2017/1/4.
 */

public abstract class BaseCompatActivity extends RxAppCompatActivity
{
    private Unbinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //统一进行布局的填充
        setContentView(getLayoutId());
        //统一进行黄油刀的绑定
        mBinder = ButterKnife.bind(this);
        //统一进行初始化view
        initView(savedInstanceState);
        //统一初始化toolBar
        initToolBar();
    }

    protected abstract void initToolBar();

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //解绑黄油刀
        mBinder.unbind();
    }
}
