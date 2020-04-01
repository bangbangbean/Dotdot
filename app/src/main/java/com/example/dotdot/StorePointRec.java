package com.example.dotdot;

import com.google.firebase.database.Exclude;

public class StorePointRec {
    private String member;
    private String points_given;
    private String time;

    public StorePointRec(){ }

    public StorePointRec(String member, String point_given){
        this.member = member;
        this.points_given = point_given;
    }

    public String getMember() {
        return member;
    }

    public String getPoint_given() {
        return points_given;
    }

    public String getTime() {
        return time;
    }

    public void setMember(String member) { this.member = member; }

    public void setPoint_given(String point_given) { this.points_given = point_given; }

    public void setTime(String time) { this.time = time; }
}

