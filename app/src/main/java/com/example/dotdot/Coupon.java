package com.example.dotdot;

import java.util.Date;

public class Coupon {
    private String dotNeed;
    private String couponContent;
    private String couponTitle;
    private Date creatTime;
    private Date deadLine;
    Coupon coupon;

    public Coupon() {

    }
    public Coupon(Date creatTime, Date deadLine,String couponTitle,String couponContent,String dotNeed){
        this.dotNeed = dotNeed;
        this.couponContent = couponContent;
        this.creatTime = creatTime;
        this.deadLine = deadLine;
        this.couponTitle = couponTitle;
    }


    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getDotNeed() {
        return dotNeed;
    }

    public void setDotNeed(String dotNeed) {
        this.dotNeed = dotNeed;
    }

    public String getCouponContent() {
        return couponContent;
    }

    public void setCouponContent(String couponContent) {
        this.couponContent = couponContent;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

}
