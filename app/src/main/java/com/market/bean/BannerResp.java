package com.market.bean;

import java.util.List;

/**
 * Created by chenlong on 2017/1/5.
 */

public class BannerResp
{


    /**
     * data : [{"id":"1","pic":"images/banner.png","title":"专题活动名称"},{"id":"2","pic":"images/banner1.png","title":"专题活动名称"},{"id":"3","pic":"images/banner2.png","title":"专题活动名称"},{"id":"4","pic":"images/banner3.png","title":"专题活动名称"},{"id":"5","pic":"images/banner4.png","title":"专题活动名称"}]
     * response : home
     */

    private String response;
    private List<DataBean> data;

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public List<DataBean> getData()
    {
        return data;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        /**
         * id : 1
         * pic : images/banner.png
         * title : 专题活动名称
         */

        private String id;
        private String pic;
        private String title;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getPic()
        {
            return pic;
        }

        public void setPic(String pic)
        {
            this.pic = pic;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }
    }
}
