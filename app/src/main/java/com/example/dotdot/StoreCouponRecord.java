package com.example.dotdot;

import java.util.Date;

public class StoreCouponRecord {
    private String store_couponId;
    private Date time;

    public StoreCouponRecord(){

    }

    public StoreCouponRecord(String store_couponId, Date time) {
        this.store_couponId = store_couponId;
        this.time = time;
    }

    public String getStore_couponId() {
        return store_couponId;
    }

    public void setStore_couponId(String store_couponId) {
        this.store_couponId = store_couponId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
