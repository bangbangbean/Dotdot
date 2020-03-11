package com.example.dotdot;

public class Store {
    private String name;
    private String phone;
    private String address;
    Store store;

    public Store() {
    }
    public Store(String name,String address,String phone){
        this.name = name;
        this.address = address;
        this.phone = phone;

    }

    public String getName() {
        return name;
    }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}
