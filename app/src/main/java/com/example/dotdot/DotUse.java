package com.example.dotdot;

import java.util.Date;

public class DotUse {
    private String point_use;
    private String couponTitle;
    private Date time;


    public DotUse() {

    }

    public DotUse(String point_use, String couponTitle, Date time) {
        this.point_use = point_use;
        this.couponTitle = couponTitle;
        this.time = time;
    }

    public String getPoint_use() {
        return point_use;
    }

    public void setPoint_use(String point_use) {
        this.point_use = point_use;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
