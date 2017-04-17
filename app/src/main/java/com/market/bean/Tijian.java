package com.market.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */

public class Tijian {
    /**
     * brand : [{"id":1,"key":"孕妈专区","value":[{"id":1218,"name":"雅培","pic":"/images/brand/c.png"},{"id":1219,"name":"贝因美","pic":"/images/brand/d.png"},{"id":1220,"name":"周生生","pic":"/images/brand/a.png"},{"id":1221,"name":"婴姿坊","pic":"/images/brand/e.png"}]},{"id":2,"key":"营养食品","value":[{"id":1202,"name":"咪咪","pic":"/images/brand/b.png"}]},{"id":3,"key":"宝宝用品","value":[{"id":1209,"name":"好奇","pic":"/images/brand/d.png"},{"id":1210,"name":"快乐宝贝","pic":"/images/brand/e.png"},{"id":1211,"name":"环球娃娃","pic":"/images/brand/f.png"},{"id":1212,"name":"Kiddy","pic":"/images/brand/a.png"}]},{"id":4,"key":"零食专区","value":[{"id":1223,"name":"良品铺子","pic":"/images/brand/lppz.jpg"}]}]
     * response : brand
     */

    private String response;
    private List<BrandEntity> brand;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setBrand(List<BrandEntity> brand) {
        this.brand = brand;
    }

    public String getResponse() {
        return response;
    }

    public List<BrandEntity> getBrand() {
        return brand;
    }

    public static class BrandEntity {
        /**
         * id : 1
         * key : 孕妈专区
         * value : [{"id":1218,"name":"雅培","pic":"/images/brand/c.png"},{"id":1219,"name":"贝因美","pic":"/images/brand/d.png"},{"id":1220,"name":"周生生","pic":"/images/brand/a.png"},{"id":1221,"name":"婴姿坊","pic":"/images/brand/e.png"}]
         */

        private int id;
        private String key;
        private List<ValueEntity> value;

        public void setId(int id) {
            this.id = id;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(List<ValueEntity> value) {
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public String getKey() {
            return key;
        }

        public List<ValueEntity> getValue() {
            return value;
        }

        public static class ValueEntity {
            /**
             * id : 1218
             * name : 雅培
             * pic : /images/brand/c.png
             */

            private int id;
            private String name;
            private String pic;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getPic() {
                return pic;
            }
        }
    }
}
