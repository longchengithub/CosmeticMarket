package com.market.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chenlong on 2017/1/10.
 */

@Entity
public class CarStore
{
    @Id(autoincrement = true)
    private Long id;
    private String product_detail;
    @Generated(hash = 385860368)
    public CarStore(Long id, String product_detail) {
        this.id = id;
        this.product_detail = product_detail;
    }
    @Generated(hash = 884564556)
    public CarStore() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProduct_detail() {
        return this.product_detail;
    }
    public void setProduct_detail(String product_detail) {
        this.product_detail = product_detail;
    }
}
