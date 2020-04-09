package com.example.dotdot;

import com.google.firebase.database.Exclude;

import java.util.Date;

public class StorePointRec {
    private String member;
    private String points_given;
    private Date time;

    public StorePointRec(){ }

    public StorePointRec(String member, String point_given, Date time){
        this.member = member;
        this.points_given = point_given;
        this.time = time;
    }

    public String getMember() {
        return member;
    }

    public String getPoint_given() {
        return points_given;
    }

    public Date getTime() {
        return time;
    }

    public void setMember(String member) { this.member = member; }

    public void setPoint_given(String point_given) { this.points_given = point_given; }

    public void setTime(Date time) { this.time = time; }
}

