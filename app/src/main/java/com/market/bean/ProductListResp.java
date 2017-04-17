package com.market.bean;

import java.util.List;

/**
 * Created by chenlong on 2017/1/12.
 */

public class ProductListResp
{

    /**
     * listCount : 6
     * listFilter : [{"key":"品牌","valueList":[{"id":"s9","name":"爱家"},{"id":"s10","name":"咪咪"},{"id":"s11","name":"无印良品"},{"id":"s12","name":"枕工坊"},{"id":"s13","name":"Nutrilon"},{"id":"s14","name":"Hero"},{"id":"s15","name":"Goo.N"},{"id":"s8","name":"好奇"},{"id":"s1","name":"快乐宝贝"},{"id":"s2","name":"环球娃娃"},{"id":"s3","name":"Kiddy"},{"id":"s4","name":"hogar"},{"id":"s5","name":"奇妮孕妇装"},{"id":"s6","name":"Bio-oil"},{"id":"s7","name":"莫施"},{"id":"s16","name":"IMG"},{"id":"s17","name":"雅培"},{"id":"s18","name":"贝因美"},{"id":"s19","name":"周生生"},{"id":"s20","name":"婴姿坊"},{"id":"s21","name":"飞利浦"},{"id":"s22","name":"良品铺子"}]},{"key":"价格","valueList":[{"id":"p1","name":"100.0"},{"id":"p2","name":"200.0"},{"id":"p3","name":"300.0"},{"id":"p4","name":"400.0"},{"id":"p5","name":"500.0"},{"id":"p6","name":"600.0"},{"id":"p7","name":"700.0"},{"id":"p8","name":"800.0"},{"id":"p9","name":"900.0"}]},{"key":"颜色","valueList":[{"id":"t1","name":"红色"},{"id":"t2","name":"粉色"},{"id":"t3","name":"黑色"},{"id":"t4","name":"深色"},{"id":"t5","name":"浅色"},{"id":"t6","name":"白色"}]}]
     * productList : [{"commentCount":10,"id":8,"marketPrice":200,"name":"情女装","pic":"/images/product/detail/q6.jpg","price":160},{"commentCount":10,"id":9,"marketPrice":300,"name":"女鞋","pic":"/images/product/detail/q7.jpg","price":200},{"commentCount":10,"id":5,"marketPrice":98,"name":"时尚女裙","pic":"/images/product/detail/a1.jpg","price":108},{"commentCount":10,"id":6,"marketPrice":100,"name":"时尚秋装","pic":"/images/product/detail/w2.jpg","price":52},{"commentCount":10,"id":3,"marketPrice":500,"name":"女裙","pic":"/images/product/detail/c1.jpg","price":300},{"commentCount":10,"id":4,"marketPrice":198,"name":"帽子","pic":"/images/product/detail/b1.jpg","price":168}]
     * response : categoryProductlist
     */

    private int listCount;
    private String response;
    private List<ListFilterBean> listFilter;
    private List<ProductListBean> productList;

    public int getListCount()
    {
        return listCount;
    }

    public void setListCount(int listCount)
    {
        this.listCount = listCount;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public List<ListFilterBean> getListFilter()
    {
        return listFilter;
    }

    public void setListFilter(List<ListFilterBean> listFilter)
    {
        this.listFilter = listFilter;
    }

    public List<ProductListBean> getProductList()
    {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList)
    {
        this.productList = productList;
    }

    public static class ListFilterBean
    {
        /**
         * key : 品牌
         * valueList : [{"id":"s9","name":"爱家"},{"id":"s10","name":"咪咪"},{"id":"s11","name":"无印良品"},{"id":"s12","name":"枕工坊"},{"id":"s13","name":"Nutrilon"},{"id":"s14","name":"Hero"},{"id":"s15","name":"Goo.N"},{"id":"s8","name":"好奇"},{"id":"s1","name":"快乐宝贝"},{"id":"s2","name":"环球娃娃"},{"id":"s3","name":"Kiddy"},{"id":"s4","name":"hogar"},{"id":"s5","name":"奇妮孕妇装"},{"id":"s6","name":"Bio-oil"},{"id":"s7","name":"莫施"},{"id":"s16","name":"IMG"},{"id":"s17","name":"雅培"},{"id":"s18","name":"贝因美"},{"id":"s19","name":"周生生"},{"id":"s20","name":"婴姿坊"},{"id":"s21","name":"飞利浦"},{"id":"s22","name":"良品铺子"}]
         */

        private String key;
        private List<ValueListBean> valueList;

        public String getKey()
        {
            return key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        public List<ValueListBean> getValueList()
        {
            return valueList;
        }

        public void setValueList(List<ValueListBean> valueList)
        {
            this.valueList = valueList;
        }

        public static class ValueListBean
        {
            /**
             * id : s9
             * name : 爱家
             */

            private String id;
            private String name;

            public String getId()
            {
                return id;
            }

            public void setId(String id)
            {
                this.id = id;
            }

            public String getName()
            {
                return name;
            }

            public void setName(String name)
            {
                this.name = name;
            }
        }
    }

    public static class ProductListBean
    {
        /**
         * commentCount : 10
         * id : 8
         * marketPrice : 200
         * name : 情女装
         * pic : /images/product/detail/q6.jpg
         * price : 160
         */

        private int commentCount;
        private int id;
        private int marketPrice;
        private String name;
        private String pic;
        private int price;

        public int getCommentCount()
        {
            return commentCount;
        }

        public void setCommentCount(int commentCount)
        {
            this.commentCount = commentCount;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public int getMarketPrice()
        {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice)
        {
            this.marketPrice = marketPrice;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getPic()
        {
            return pic;
        }

        public void setPic(String pic)
        {
            this.pic = pic;
        }

        public int getPrice()
        {
            return price;
        }

        public void setPrice(int price)
        {
            this.price = price;
        }
    }
}
