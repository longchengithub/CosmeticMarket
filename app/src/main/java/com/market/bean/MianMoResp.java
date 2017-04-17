package com.market.bean;

import java.util.List;

/**
 * Created by chenlong on 2017/1/8.
 */

public class MianMoResp
{
    /**
     * code : 0
     * productlist : [{"desc":"三严面膜天然成分99.1%","id":1,"image":"images/product/product_one.png","price":"价格:￥276.00"},{"desc":"PUARLR清爽保湿","id":2,"image":"images/product/product_two.png","price":"价格:￥555.00"},{"desc":"城野医生护手霜","id":3,"image":"images/product/product_three.png","price":"价格:￥76.00"},{"desc":"GHJ平衡香薰三组合","id":4,"image":"images/product/product_four.png","price":"价格:￥376.00"},{"desc":"我好气哦不会写","id":1,"image":"images/product/product_five.png","price":"价格:￥176.00"},{"desc":"年轻人不要辣么急躁","id":2,"image":"images/product/product_six.png","price":"价格:￥576.00"},{"desc":"我有句妈卖批要将","id":3,"image":"images/product/product_seven.png","price":"价格:￥272.00"},{"desc":"不要在意那些细节","id":4,"image":"images/product/product_eight.png","price":"价格:￥199.00"},{"desc":"三严面膜天然成分99.1%","id":1,"image":"images/product/product_one.png","price":"价格:￥276.00"},{"desc":"PUARLR清爽保湿","id":2,"image":"images/product/product_two.png","price":"价格:￥555.00"},{"desc":"城野医生护手霜","id":3,"image":"images/product/product_three.png","price":"价格:￥76.00"},{"desc":"GHJ平衡香薰三组合","id":4,"image":"images/product/product_four.png","price":"价格:￥376.00"},{"desc":"我好气哦不会写","id":1,"image":"images/product/product_five.png","price":"价格:￥176.00"},{"desc":"年轻人不要辣么急躁","id":2,"image":"images/product/product_six.png","price":"价格:￥576.00"},{"desc":"我有句妈卖批要将","id":3,"image":"images/product/product_seven.png","price":"价格:￥272.00"},{"desc":"不要在意那些细节","id":4,"image":"images/product/product_eight.png","price":"价格:￥199.00"}]
     */

    private int code;
    private List<ProductlistBean> productlist;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public List<ProductlistBean> getProductlist()
    {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist)
    {
        this.productlist = productlist;
    }

    public static class ProductlistBean
    {
        /**
         * desc : 三严面膜天然成分99.1%
         * id : 1
         * image : images/product/product_one.png
         * price : 价格:￥276.00
         */

        private String desc;
        private int id;
        private String image;
        private String price;

        public String getDesc()
        {
            return desc;
        }

        public void setDesc(String desc)
        {
            this.desc = desc;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getImage()
        {
            return image;
        }

        public void setImage(String image)
        {
            this.image = image;
        }

        public String getPrice()
        {
            return price;
        }

        public void setPrice(String price)
        {
            this.price = price;
        }
    }
}
