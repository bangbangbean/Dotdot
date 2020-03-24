package com.example.dotdot;

public class StoreLoyaltyCard{
    private String color;
    private String money;
    StoreLoyaltyCard storeloyaltycard;

    public StoreLoyaltyCard() {
    }

    public StoreLoyaltyCard(String color, String money) {
        this.color = color;
        this.money = money;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}
