package com.market.bean;

import java.util.List;

/**
 * Created by zdf on 2017/1/8.
 */

public class OrderListResp {
    /**
     * orderList : [{"flag":1,"orderId":"565919","price":450,"product":{"buyLimit":10,"id":1,"name":"韩版时尚蕾丝裙","number":"100","pic":"/images/product/detail/c3.jpg","price":350},"status":"未处理","time":"1472728565943"},{"flag":1,"orderId":"750949","price":160,"product":{"buyLimit":10,"id":30,"name":"超凡奶粉","number":"16","pic":"/images/product/detail/q26.jpg","price":160},"status":"未处理","time":"1473135750957"}]
     * response : orderList
     */

    private String response;
    private List<OrderListBean> orderList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * flag : 1
         * orderId : 565919
         * price : 450
         * product : {"buyLimit":10,"id":1,"name":"韩版时尚蕾丝裙","number":"100","pic":"/images/product/detail/c3.jpg","price":350}
         * status : 未处理
         * time : 1472728565943
         */

        private int flag;
        private String orderId;
        private int price;
        private ProductBean product;
        private String status;
        private String time;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public static class ProductBean {
            /**
             * buyLimit : 10
             * id : 1
             * name : 韩版时尚蕾丝裙
             * number : 100
             * pic : /images/product/detail/c3.jpg
             * price : 350
             */

            private int buyLimit;
            private int id;
            private String name;
            private String number;
            private String pic;
            private int price;

            public int getBuyLimit() {
                return buyLimit;
            }

            public void setBuyLimit(int buyLimit) {
                this.buyLimit = buyLimit;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }


    /**
     * response : orderList
     * orderList : [{"orderId":"260092","status":"未处理","time":"1439528260115","price":"208","flag":"1"},{"orderId":"171835","status":"未处理","time":"1439529171852","price":"\u201c208\u201d","flag":"1"},{"orderId":" 879495 ","status":"未处理","time":" 1449037879509 ","price":"\u201c450\u201d","flag":"1"}]
     */

   /* private String response;
    private List<OrderListBean> orderList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        *//**
         * orderId : 260092
         * status : 未处理
         * time : 1439528260115
         * price : 208
         * flag : 1
         *//*

        private String orderId;
        private String status;
        private String time;
        private String price;
        private String flag;

        public OrderListBean(String price, String orderId, String status, String time) {
            this.price = price;
            this.orderId = orderId;
            this.status = status;
            this.time = time;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        @Override
        public String toString() {
            return "OrderListBean{" +
                    "orderId='" + orderId + '\'' +
                    ", status='" + status + '\'' +
                    ", time='" + time + '\'' +
                    ", price='" + price + '\'' +
                    ", flag='" + flag + '\'' +
                    '}';
        }
    }*/
}
