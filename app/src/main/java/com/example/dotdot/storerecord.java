package com.example.dotdot;

import com.google.android.gms.maps.model.LatLng;

import java.lang.ref.Reference;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Date;

public class storerecord {

    public static final LatLng loc1 = new LatLng(25.032022, 121.432804);
    public static final LatLng chicken = new LatLng(25.034434, 121.430992);
    private Date time;
    private String points_given;

//        private String member;

    public storerecord() {
        //empty constructor needed
    }

    public storerecord(Date time, String points_given, String member) {
        this.time = time;
        this.points_given = points_given;

//            this.member = member;
    }

    public Date getTime() {
        return time;
    }


    public String getPoints_given() {
        return points_given;
    }


//        public String getMember(){return member;}
}
