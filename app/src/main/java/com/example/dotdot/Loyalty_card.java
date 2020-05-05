package com.example.dotdot;

public class Loyalty_card {
    private String points_owned;
    private String store;
    private String color;
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

}
