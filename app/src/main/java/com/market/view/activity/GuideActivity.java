package com.market.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.market.R;
import com.market.utils.DisplayUtil;
import com.market.view.activity.base.BaseCompatActivity;

import butterknife.BindView;

public class GuideActivity extends BaseCompatActivity
{
    @BindView(R.id.guide_viewPager)
    ViewPager mGuideViewPager;
    @BindView(R.id.guide_points)
    LinearLayout mGuidePoints;

    private GuideAdapter mGuideAdapter;
    private int preIndex;

    @Override
    protected void initToolBar()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        //初始化指示器的小圆点
        initPoints();

        //初始化viewPager
        initViewPager();

        mGuidePoints.getChildAt(0).setEnabled(false);
    }

    private void initViewPager()
    {
        mGuideAdapter = new GuideAdapter();
        mGuideViewPager.setAdapter(mGuideAdapter);

        mGuideViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                mGuidePoints.getChildAt(preIndex).setEnabled(true);
                mGuidePoints.getChildAt(position).setEnabled(false);
                preIndex = position;
            }
        });
    }

    private void initPoints()
    {
        mGuidePoints.removeAllViews();
        for (int i = 0; i < mGuidePics.length + 1; i++)
        {
            ImageView dot = new ImageView(getBaseContext());
            dot.setBackgroundResource(R.drawable.selector_point);
            dot.setScaleType(ImageView.ScaleType.CENTER);
            dot.setEnabled(true);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DisplayUtil.dp2px(getBaseContext(), 6),
                    DisplayUtil.dp2px(getBaseContext(), 6));
            params.leftMargin = 30;
            params.rightMargin = 30;
            dot.setLayoutParams(params);
            mGuidePoints.addView(dot);
        }
    }

    private int[] mGuidePics = {R.drawable.guide1, R.drawable.guide2};

    private class GuideAdapter extends PagerAdapter
    {
        @Override
        public int getCount()
        {
            return mGuidePics.length + 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            if (position == getCount() - 1)
            {
                View guideLogin = View.inflate(GuideActivity.this, R.layout.last_guide_pager, null);
                View tv_login = guideLogin.findViewById(R.id.guide_login);
                tv_login.setOnClickListener(v -> {
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    finish();
                });
                container.addView(guideLogin);
                return guideLogin;
            } else
            {
                ImageView imageView = new ImageView(getBaseContext());
                imageView.setImageResource(mGuidePics[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(imageView);
                return imageView;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }
    }
}
