package com.market.http;

import android.content.Context;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/1/8.
 */

public class GlideUtlis {
    //http://192.168.31.22:8080/market/images/dazhe/2.png
    private static  String HttpUtl="http://192.168.31.22:8080/market/";
    public  static DrawableTypeRequest<String> getpicture(Context context,String string){
        DrawableTypeRequest<String> load = Glide.with(context).load(HttpUtl+string);
        return load;

    }

}
