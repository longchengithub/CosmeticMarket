package com.market;

import android.app.Application;

/**
 * Created by chenlong on 2017/1/5.
 */

public class CosmeticMarketApp extends Application
{
    private static CosmeticMarketApp mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
    }

    public static CosmeticMarketApp newInstance()
    {
        return mInstance;
    }
}
