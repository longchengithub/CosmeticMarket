package com.market.bean;

/**
 * Created by zdf on 2017/1/12.
 */

public class OrderSumbitResp {

    /**
     * orderInfo : {"orderId":"695300","paymentType":1,"price":450}
     * response : orderSubmit
     */

    private OrderInfoBean orderInfo;
    private String response;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class OrderInfoBean {
        /**
         * orderId : 695300
         * paymentType : 1
         * price : 450
         */

        private String orderId;
        private int paymentType;
        private int price;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "OrderInfoBean{" +
                    "orderId='" + orderId + '\'' +
                    ", paymentType=" + paymentType +
                    ", price=" + price +
                    '}';
        }
    }
}
