package com.market.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class Qiangou {

    /**
     * listCount : 5
     * productList : [{"id":2,"leftTime":17000,"limitPrice":1,"name":"粉色毛衣","pic":"/images/product/detail/q1.jpg","price":100},{"id":3,"leftTime":16000,"limitPrice":90,"name":"女裙","pic":"/images/product/detail/c1.jpg","price":300},{"id":4,"leftTime":15000,"limitPrice":98,"name":"帽子","pic":"/images/product/detail/b1.jpg","price":168},{"id":5,"leftTime":14000,"limitPrice":68,"name":"时尚女裙","pic":"/images/product/detail/a1.jpg","price":108},{"id":6,"leftTime":13000,"limitPrice":36,"name":"时尚秋装","pic":"/images/product/detail/w2.jpg","price":52}]
     * response : limitbuy
     */

    private int listCount;
    private String response;
    private List<ProductListEntity> productList;

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setProductList(List<ProductListEntity> productList) {
        this.productList = productList;
    }

    public int getListCount() {
        return listCount;
    }

    public String getResponse() {
        return response;
    }

    public List<ProductListEntity> getProductList() {
        return productList;
    }

    public static class ProductListEntity {
        /**
         * id : 2
         * leftTime : 17000
         * limitPrice : 1
         * name : 粉色毛衣
         * pic : /images/product/detail/q1.jpg
         * price : 100
         */

        private int id;
        private int leftTime;
        private int limitPrice;
        private String name;
        private String pic;
        private int price;

        public void setId(int id) {
            this.id = id;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public int getLimitPrice() {
            return limitPrice;
        }

        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }

        public int getPrice() {
            return price;
        }
    }
}
