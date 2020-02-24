package com.example.dotdot;

public class Member {
    private String name;
    private String phone;
    private String password;

    public Member(String name,String password,String phone){
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getPassword() {
        return password;
    }
}
