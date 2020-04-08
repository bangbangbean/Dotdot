package com.example.dotdot;

public class Loyalty_card {
    private String points_owned;
    private String store;
    private String points_use;
    private String coupon;

    public Loyalty_card(){

    }
    public Loyalty_card(String points_owned, String store, String points_use, String coupon){
        this.points_owned = points_owned;
        this.store = store;
        this.points_use = points_use;
        this.coupon = coupon;
    }

    public String getPoints_owned(){return points_owned;}
    public String getPoints_use(){return points_use;}
    public String getStore(){return store;}
    public String getCoupon(){return coupon;}
}
