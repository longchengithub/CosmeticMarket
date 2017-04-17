package com.market.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */

public class Topic {

    /**
     * response : topic
     * topic : [{"id":2,"name":"儿童玩具聚划算","pic":"/images/topic/2.jpg"},{"id":3,"name":"尿不湿大酬宾","pic":"/images/topic/3.png"}]
     */

    private String response;
    private List<TopicEntity> topic;

    public void setResponse(String response) {

        this.response = response;
    }

    public void setTopic(List<TopicEntity> topic) {

        this.topic = topic;
    }

    public String getResponse() {
        return response;
    }

    public List<TopicEntity> getTopic() {

        return topic;
    }

    public static class TopicEntity {
        /**
         * id : 2
         * name : 儿童玩具聚划算
         * pic : /images/topic/2.jpg
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
