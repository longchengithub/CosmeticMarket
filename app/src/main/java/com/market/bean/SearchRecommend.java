package com.market.bean;

import java.util.List;

/**
 * Created by 你这坏孩纸 on 2017/1/10.
 */

public class SearchRecommend {
    /**
     * response : searchrecommend
     * searchKeywords : ["韩版时尚蕾丝裙","粉色毛衣","女裙","帽子","时尚女裙","时尚秋装"]
     */

    private String response;
    private List<String> searchKeywords;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }
}
