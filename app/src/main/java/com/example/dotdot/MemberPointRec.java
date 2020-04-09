package com.example.dotdot;
import com.google.firebase.database.Exclude;

import java.util.Date;

public class MemberPointRec {
    private String points_get;
    private String store;
    private Date time;

    public MemberPointRec(){}

    public MemberPointRec(String points_get, String store, Date time){
        this.points_get = points_get;
        this.store = store;
        this.time = time;
    }

    public String getPoint_get() {
        return points_get;
    }

    public void setPoint_get(String point_get) {
        this.points_get = point_get;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
