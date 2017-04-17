package com.market.bean;

import java.util.List;

/**
 * Created by zdf on 2017/1/11.
 */

public class OrdercheckoutResp {

    @Override
    public String toString() {
        return "OrdercheckoutResp{" +
                "addressInfo=" + addressInfo +
                ", checkoutAddup=" + checkoutAddup +
                ", response='" + response + '\'' +
                ", checkoutProm=" + checkoutProm +
                ", deliveryList=" + deliveryList +
                ", paymentList=" + paymentList +
                ", productList=" + productList +
                '}';
    }

    /**
     * addressInfo : {"addressArea":"赤水市","addressDetail":"文华路文华学院","city":"遵义市","id":134,"isDefault":1,"name":"张瑞丽","phoneNumber":"189","province":"贵州省","zipCode":"563000"}
     * checkoutAddup : {"freight":10,"totalCount":5,"totalPoint":30,"totalPrice":1250}
     * checkoutProm : ["促销信息一","促销信息二"]
     * deliveryList : [{"des":"周一至周五送货","type":1},{"des":"双休日及公众假期送货","type":2},{"des":"时间不限，工作日双休日及公众假期均可送货","type":3}]
     * paymentList : [{"des":"到付-现金","type":1},{"des":"到付-POS机","type":2},{"des":"支付宝","type":1}]
     * productList : [{"prodNum":3,"product":{"id":1,"name":"韩版时尚蕾丝裙","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":3,"k":"尺码","v":"M"}]}},{"prodNum":2,"product":{"id":2,"name":"粉色毛衣","pic":"/images/product/detail/q1.jpg","price":100,"productProperty":[{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"}]}}]
     * response : checkOut
     */

    private AddressInfoBean addressInfo;
    private CheckoutAddupBean checkoutAddup;
    private String response;
    private List<String> checkoutProm;
    private List<DeliveryListBean> deliveryList;
    private List<PaymentListBean> paymentList;
    private List<ProductListBean> productList;

    public AddressInfoBean getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfoBean addressInfo) {
        this.addressInfo = addressInfo;
    }

    public CheckoutAddupBean getCheckoutAddup() {
        return checkoutAddup;
    }

    public void setCheckoutAddup(CheckoutAddupBean checkoutAddup) {
        this.checkoutAddup = checkoutAddup;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getCheckoutProm() {
        return checkoutProm;
    }

    public void setCheckoutProm(List<String> checkoutProm) {
        this.checkoutProm = checkoutProm;
    }

    public List<DeliveryListBean> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<DeliveryListBean> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public List<PaymentListBean> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<PaymentListBean> paymentList) {
        this.paymentList = paymentList;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class AddressInfoBean {
        @Override
        public String toString() {
            return "AddressInfoBean{" +
                    "addressArea='" + addressArea + '\'' +
                    ", addressDetail='" + addressDetail + '\'' +
                    ", city='" + city + '\'' +
                    ", id=" + id +
                    ", isDefault=" + isDefault +
                    ", name='" + name + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", province='" + province + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    '}';
        }

        /**
         * addressArea : 赤水市
         * addressDetail : 文华路文华学院
         * city : 遵义市
         * id : 134
         * isDefault : 1
         * name : 张瑞丽
         * phoneNumber : 189
         * province : 贵州省
         * zipCode : 563000
         */

        private String addressArea;
        private String addressDetail;
        private String city;
        private int id;
        private int isDefault;
        private String name;
        private String phoneNumber;
        private String province;
        private String zipCode;

        public String getAddressArea() {
            return addressArea;
        }

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

    public static class CheckoutAddupBean {
        @Override
        public String toString() {
            return "CheckoutAddupBean{" +
                    "freight=" + freight +
                    ", totalCount=" + totalCount +
                    ", totalPoint=" + totalPoint +
                    ", totalPrice=" + totalPrice +
                    '}';
        }

        /**
         * freight : 10
         * totalCount : 5
         * totalPoint : 30
         * totalPrice : 1250
         */

        private int freight;
        private int totalCount;
        private int totalPoint;
        private int totalPrice;

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPoint() {
            return totalPoint;
        }

        public void setTotalPoint(int totalPoint) {
            this.totalPoint = totalPoint;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public static class DeliveryListBean {
        @Override
        public String toString() {
            return "DeliveryListBean{" +
                    "des='" + des + '\'' +
                    ", type=" + type +
                    '}';
        }

        /**
         * des : 周一至周五送货
         * type : 1
         */

        private String des;
        private int type;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class PaymentListBean {
        /**
         * des : 到付-现金
         * type : 1
         */

        private String des;
        private int type;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class ProductListBean {
        @Override
        public String toString() {
            return "ProductListBean{" +
                    "prodNum=" + prodNum +
                    ", product=" + product +
                    '}';
        }

        /**
         * prodNum : 3
         * product : {"id":1,"name":"韩版时尚蕾丝裙","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":3,"k":"尺码","v":"M"}]}
         */

        private int prodNum;
        private ProductBean product;

        public int getProdNum() {
            return prodNum;
        }

        public void setProdNum(int prodNum) {
            this.prodNum = prodNum;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            @Override
            public String toString() {
                return "ProductBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", pic='" + pic + '\'' +
                        ", price=" + price +
                        ", productProperty=" + productProperty +
                        '}';
            }

            /**
             * id : 1
             * name : 韩版时尚蕾丝裙
             * pic : /images/product/detail/c3.jpg
             * price : 350
             * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":3,"k":"尺码","v":"M"}]
             */

            private int id;
            private String name;
            private String pic;
            private int price;
            private List<ProductPropertyBean> productProperty;

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

            public List<ProductPropertyBean> getProductProperty() {
                return productProperty;
            }

            public void setProductProperty(List<ProductPropertyBean> productProperty) {
                this.productProperty = productProperty;
            }

            public static class ProductPropertyBean {
                /**
                 * id : 1
                 * k : 颜色
                 * v : 红色
                 */

                private int id;
                private String k;
                private String v;

                @Override
                public String toString() {
                    return "ProductPropertyBean{" +
                            "id=" + id +
                            ", k='" + k + '\'' +
                            ", v='" + v + '\'' +
                            '}';
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getK() {
                    return k;
                }

                public void setK(String k) {
                    this.k = k;
                }

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }
            }
        }
    }
}
