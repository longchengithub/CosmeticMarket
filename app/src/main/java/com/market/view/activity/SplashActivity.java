package com.market.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.market.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity
{

    @BindView(R.id.splash)
    ImageView mSplashImage;

    private Subscription mSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    /**
     * 在resume生命周期开启动画 和跳转的订阅任务
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        initSplash();       //初始化splash
        overSplash = false;
    }

    /**
     * 在stop生命周期 处于后台时 停止动画的跳转 解除订阅任务
     */
    @Override
    protected void onStop()
    {
        super.onStop();
        overSplash = true;

        if (mSubscribe != null && !mSubscribe.isUnsubscribed())
        {
            mSubscribe.unsubscribe();
        }
    }

    /**
     * 计时器 2秒后执行动画
     */
    protected void initSplash()
    {
        mSubscribe = Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    initAnimator();
                });
    }

    //标记位 是否允许执行动画后跳转
    private boolean overSplash;

    /**
     * 执行动画
     */
    protected void initAnimator()
    {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mSplashImage, "scaleX",
                1f, 1.15f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mSplashImage, "scaleY",
                1f, 1.15f);

        AnimatorSet set = new AnimatorSet();
        //组合2个动画效果
        set.setDuration(2000).play(animatorX).with(animatorY);
        set.start();

        set.removeAllListeners();
        //监听动画完成
        set.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                if (!overSplash)
                {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                    //系统自带的淡入淡出动画
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            }
        });
    }
}
