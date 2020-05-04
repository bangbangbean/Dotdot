package com.example.dotdot;

import com.google.firebase.database.Exclude;

import java.util.Date;

public class CouponBeenExchange {
    private String store_couponiId;

    CouponBeenExchange couponBeenExchange;

    public CouponBeenExchange() {

    }
    public CouponBeenExchange(String store_couponiId){
        this.store_couponiId = store_couponiId;

    }
    @Exclude
    public String getStore_couponiId(){return store_couponiId;}

}
