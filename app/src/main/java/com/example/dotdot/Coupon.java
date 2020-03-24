package com.example.dotdot;

import java.util.Date;

public class Coupon {
//    private String dotNeed;
//    private String content;
    private Date creatTime;
    private Date deadLine;
    Coupon coupon;

    public Coupon() {

    }
    public Coupon(Date creatTime, Date deadLine){
//        this.dotNeed = dotNeed;
//        this.content = content;
        this.creatTime = creatTime;
        this.deadLine = deadLine;
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
