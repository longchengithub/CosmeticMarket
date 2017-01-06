package com.market.view.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenlong on 2017/1/4.
 */

public abstract class BaseFragment extends RxFragment
{
    private View mParentView;

    private Unbinder mBinder;

    protected Activity mActivity;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (mParentView == null)
        {
            mParentView = inflater.inflate(getLayoutId(), container, false);
        }
        return mParentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mBinder = ButterKnife.bind(this, view);
        initView(savedInstanceState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mBinder.unbind();
    }

    protected boolean isVisible;        //标记  是否可见

    protected boolean isPrepared;       //标记  是否初始化完毕

    /**
     * 用于需要懒加载时
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);

        if (mParentView == null)
        {
            return;
        }

        if (getUserVisibleHint())
        {
            isVisible = true;
            onVisible();
        } else
        {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible()
    {
        onLazyLoad();
    }

    protected abstract void onLazyLoad();

    protected void onInvisible()
    {
    }

    protected abstract void initView(Bundle savedInstanceState);

    @LayoutRes
    protected abstract int getLayoutId();

}
