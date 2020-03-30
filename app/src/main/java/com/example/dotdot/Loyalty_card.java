package com.example.dotdot;

public class Loyalty_card {
    private String points_owned;
    Loyalty_card loyalty_card;

    public Loyalty_card(){

    }
    public Loyalty_card(String points_owned){
        this.points_owned = points_owned;
    }

    public String getPoints_owned(){return points_owned;}
}
