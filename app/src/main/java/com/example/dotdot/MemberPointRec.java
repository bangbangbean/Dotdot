package com.example.dotdot;
import com.google.firebase.database.Exclude;

import java.util.Date;

public class MemberPointRec {
    private String points_get;
    private String storeId;
    private Date time;

    public MemberPointRec(){}

    public MemberPointRec(String points_get, String storeId, Date time){
        this.points_get = points_get;
        this.storeId = storeId;
        this.time = time;
    }

    public String getPoint_get() {
        return points_get;
    }

    public void setPoint_get(String point_get) {
        this.points_get = point_get;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
