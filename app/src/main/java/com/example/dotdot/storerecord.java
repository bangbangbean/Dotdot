package com.example.dotdot;

import com.google.android.gms.maps.model.LatLng;

import java.lang.ref.Reference;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Date;

public class storerecord {

    public static final LatLng loc1 = new LatLng(25.034318, 121.431252);
    public static final LatLng chicken = new LatLng(25.034434, 121.430992);
    public static final LatLng loc2 = new LatLng(25.033628, 121.432123);

    private Date time;
    private String point_given;



    public storerecord() {
        //empty constructor needed
    }

    public storerecord(Date time, String point_given ){
        this.time = time;
        this.point_given = point_given;


    }

    public Date getTime() {
        return time;
    }


    public String getPoint_given() {
        return point_given;
    }



}
