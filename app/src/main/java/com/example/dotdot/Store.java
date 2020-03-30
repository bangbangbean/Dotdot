package com.example.dotdot;

public class Store {
    private String name;
    private String phone;
    private String address;
    private String money;
    Store store;

    public Store() {
    }

    public Store(String name,String phone,String money){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.money = money;
    }

    public String getName() {
        return name;
    }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getMoney(){return money;}
}
