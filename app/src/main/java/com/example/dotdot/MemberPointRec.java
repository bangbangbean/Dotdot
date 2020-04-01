package com.example.dotdot;
import com.google.firebase.database.Exclude;
public class MemberPointRec {
    private String points_get;
    private String store;
    private String time;

    public MemberPointRec(){}

    public MemberPointRec(String points_get, String store){
        this.points_get = points_get;
        this.store = store;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
