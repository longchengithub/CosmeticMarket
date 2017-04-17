package com.market.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */

public class Hotproduct {

    /**
     * recommendpics : [{"img_url":"images/myrecommend/a1.png","jumpto_url":"test_url"},{"img_url":"images/myrecommend/a2.png","jumpto_url":"test_url"},{"img_url":"images/myrecommend/a3.png","jumpto_url":"test_url"},{"img_url":"images/myrecommend/a4.png","jumpto_url":"test_url"}]
     * response : recommendpics
     */

    private String response;
    private List<RecommendpicsEntity> recommendpics;
    public void setResponse(String response) {

        this.response = response;
    }

    public void setRecommendpics(List<RecommendpicsEntity> recommendpics) {
        this.recommendpics = recommendpics;
    }

    public String getResponse() {
        return response;
    }

    public List<RecommendpicsEntity> getRecommendpics() {

        return recommendpics;
    }



    public static class RecommendpicsEntity {
        /**
         * img_url : images/myrecommend/a1.png
         * jumpto_url : test_url
         */

        private String img_url;
        private String jumpto_url;

        public void setImg_url(String img_url) {

            this.img_url = img_url;
        }

        public void setJumpto_url(String jumpto_url) {

            this.jumpto_url = jumpto_url;
        }

        public String getImg_url() {

            return img_url;
        }

        public String getJumpto_url() {
            return jumpto_url;
        }
    }
}
