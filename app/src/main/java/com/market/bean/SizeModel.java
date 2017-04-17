package com.market.bean;

import com.bumptech.glide.DrawableTypeRequest;

/**
 * Created by Administrator on 2017/1/9.
 */

public class SizeModel {
    private DrawableTypeRequest<String> urlmode;
    private int height;
    private int width;

    public DrawableTypeRequest<String> getDrawableTypeRequest() {
        return urlmode;
    }

    public void setDrawableTypeRequest(DrawableTypeRequest<String> urlmode) {
        this.urlmode = urlmode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isNull() {
        return height == 0 || width == 0;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
