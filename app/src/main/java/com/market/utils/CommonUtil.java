package com.market.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by chenlong on 2016/12/19.
 */
public class CommonUtil
{
    /**
     * 是否开启网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager cm = getNetState(context);

        return cm.isActiveNetworkMetered();
    }

    /**
     * 拿到网络管理员对象
     *
     * @param context
     * @return
     */
    public static ConnectivityManager getNetState(Context context)
    {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
