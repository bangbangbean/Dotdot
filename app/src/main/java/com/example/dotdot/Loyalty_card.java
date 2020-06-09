package com.example.dotdot;

public class Loyalty_card {
    private String points_owned;
    private String store;
    private String color;
    private String couponCount;
    private String dotUse;
    private String dotGet;
    Loyalty_card loyalty_card;
    public Loyalty_card(){
    }
    public Loyalty_card(String points_owned, String store,String color){
        this.points_owned = points_owned;
        this.store = store;
        this.color = color;

    }

    public String getPoints_owned() {
        return points_owned;
    }

    public void setPoints_owned(String points_owned) {
        this.points_owned = points_owned;
    }

    public String getStore() {
        return store;
    }
    public String getColor() {
        return color;
    }

    public String getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
    }

    public String getDotUse() {
        return dotUse;
    }

    public void setDotUse(String dotUse) {
        this.dotUse = dotUse;
    }

    public String getDotGet() {
        return dotGet;
    }

    public void setDotGet(String dotGet) {
        this.dotGet = dotGet;
    }
}
