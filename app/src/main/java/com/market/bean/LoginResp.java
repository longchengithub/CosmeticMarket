package com.market.bean;

/**
 * Created by wunainei on 2017/1/9.
 */

public class LoginResp {

    /**
     * response : login
     * userInfo : {"userid":"154636"}
     */

    private String response;
    private UserInfoBean userInfo;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * userid : 154636
         */

        private String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
