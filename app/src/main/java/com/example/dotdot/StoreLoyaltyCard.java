package com.example.dotdot;

public class StoreLoyaltyCard{
    private String color;
    private String Threshold;
    StoreLoyaltyCard storeloyaltycard;

    public StoreLoyaltyCard() {
    }

    public StoreLoyaltyCard(String color, String Threshold) {
        this.color = color;
        this.Threshold = Threshold;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getThreshold() {
        return Threshold;
    }

    public void setThreshold(String Threshold) {
        this.Threshold = Threshold;
    }

}
