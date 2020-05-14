package com.example.dotdot;

import java.util.Date;

public class Record {
    private String point_use;
    private String storeId;
    private String couponTitle;
    private Date time;
    private String point_get;

    public Record() {

    }

    public Record(String point_use, String storeId, Date time, String point_get, String couponTitle) {
        this.point_use = point_use;
        this.couponTitle = couponTitle;
        this.storeId = storeId;
        this.time = time;
        this.point_get = point_get;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public String getPoint_use() {
        return point_use;
    }

    public void setPoint_use(String point_use) {
        this.point_use = point_use;
    }

    public String getPoint_get() {
        return point_get;
    }

    public void setPoint_get(String point_get) {
        this.point_get = point_get;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
