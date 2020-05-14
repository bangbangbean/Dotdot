package com.example.dotdot;

import java.util.Date;

public class CouponGet {
    private Date time;
    private String couponTitle;

    public CouponGet() {

    }
    public CouponGet(Date time, String couponTitle) {
        this.time = time;
        this.couponTitle = couponTitle;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }
}
