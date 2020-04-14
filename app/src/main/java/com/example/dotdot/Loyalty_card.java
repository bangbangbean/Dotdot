package com.example.dotdot;

public class Loyalty_card {
    private String points_owned;
    private String store;
    private String points_use;
    private String coupon;
    private String documentId;
    public Loyalty_card(){

    }
    public Loyalty_card(String points_owned, String store){
        this.points_owned = points_owned;
        this.store = store;
        this.points_use = points_use;
        this.coupon = coupon;
        this.documentId = documentId;
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

    public void setStore(String store) {
        this.store = store;
    }

    public String getPoints_use() {
        return points_use;
    }

    public void setPoints_use(String points_use) {
        this.points_use = points_use;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
