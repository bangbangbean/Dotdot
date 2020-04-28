package com.example.dotdot;

import com.google.firebase.database.Exclude;

import java.util.Date;

public class Coupon {
    private Integer dotNeed;
    private String couponContent;
    private String couponTitle;
    private Date creatTime;
    private Date deadLine;
    private Date time;
    private String documentId;
    Coupon coupon;

    public Coupon() {

    }
    public Coupon(Date creatTime, Date deadLine,String couponTitle,String couponContent,Integer dotNeed){
        this.dotNeed = dotNeed;
        this.couponContent = couponContent;
        this.creatTime = creatTime;
        this.deadLine = deadLine;
        this.couponTitle = couponTitle;
        this.time = time;
    }
    @Exclude
    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Integer getDotNeed() {
        return dotNeed;
    }

    public Date getTime() {
        return time;
    }

    public void setDotNeed(Integer dotNeed) {
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
}
