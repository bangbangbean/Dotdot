package com.example.dotdot;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class data {
    public static  final LatLng fju = new LatLng(25.03319,121.433572);
    public static  final LatLng chicken = new LatLng(25.034434, 121.430992);

    public static ArrayList<LatLng> getPositions() {
        ArrayList<LatLng> list = new ArrayList<>();
        list.add(fju);
        list.add(chicken);

        return list;
    }

    }
