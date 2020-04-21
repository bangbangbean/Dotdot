package com.example.dotdot;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class data {


    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final CollectionReference memRef = db.collection("store");




    public static final LatLng loc1 = new LatLng(25.032022, 121.432804);
    public static final LatLng chicken = new LatLng(25.034434, 121.430992);


    public static ArrayList<LatLng> getPositions() {
        ArrayList<LatLng> list = new ArrayList<>();
        list.add(loc1);
        list.add(chicken);

        return list;
    }

}
