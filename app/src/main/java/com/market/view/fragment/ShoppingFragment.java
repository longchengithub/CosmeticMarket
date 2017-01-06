package com.market.view.fragment;

import android.os.Bundle;

import com.market.R;
import com.market.view.fragment.base.BaseFragment;

/**
 * Created by chenlong on 2016/12/20.
 */

public class ShoppingFragment extends BaseFragment
{
    @Override
    protected void onLazyLoad()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_shopping;
    }

    public static ShoppingFragment newInstance()
    {
        return new ShoppingFragment();
    }
}
