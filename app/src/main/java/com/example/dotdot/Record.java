package com.example.dotdot;

import java.util.Date;

public class Record {

    private Date time;
    private String store;
    private String object;
    private String point_get;



    public Record() {
        //empty constructor needed
    }

    public Record(Date time, String StoreName, String Object, String Situation ,String point_get ) {
        this.time = time;
        this.store = store;
        this.object = object;
        this.point_get = point_get;

    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStore() {
        return store;
    }

    public String getObject() {
        return object;
    }

    public String getPoint_get() {return point_get;}





}
