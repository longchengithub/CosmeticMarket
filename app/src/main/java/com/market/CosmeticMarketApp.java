package com.market;

import android.app.Application;
import android.content.Context;

import com.market.dao.DaoMaster;
import com.market.dao.DaoSession;

/**
 * Created by chenlong on 2017/1/5.
 */

public class CosmeticMarketApp extends Application
{
    private static CosmeticMarketApp mInstance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

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

    /**
     * 取得DaoMaster
     *
     * @return daoMaster
     */
    private static DaoMaster getDaoMaster(Context context)
    {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "CarStore.db", null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @return daoSession
     */
    public static DaoSession getDaoSession(Context context)
    {
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}