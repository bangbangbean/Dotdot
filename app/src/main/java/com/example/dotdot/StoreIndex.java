package com.example.dotdot;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.opencensus.tags.Tag;

public class StoreIndex extends FragmentActivity implements OnMapReadyCallback {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("store");
    private CollectionReference note = db.collection("store").document("nQnT8AAt4NYIRYZFZfAR")
            .collection("record");
    private GoogleMap mMap;
    private static final int RESQUEST_PERMISSION_LOCATION = 1;
    // this variable get for the location in mobile
    private FusedLocationProviderClient mfusedLocationProviderClient;

    Button btn_dot;
    private TextView Storetitle;
    private TextView Mon;
    private TextView Pointsgives;
    Button btn_notificaiton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_index);

        //地圖--------------------------------------------------------------------------------------
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Storetitle = findViewById(R.id.storetitle);

        memRef.document("nQnT8AAt4NYIRYZFZfAR").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Store mem = documentSnapshot.toObject(Store.class);
                    String name = mem.getName();
                    Storetitle.setText(name);

                }
            }
        });
        //--------------------------------------------------------------------------------------
        //月份--------------------------------------------------------------------------------------
        Mon = findViewById(R.id.mom);
        note.document("YrwbzDW809Z1ANn3z9zz").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM");
                    storerecord rec = documentSnapshot.toObject(storerecord.class);
                    Date record = rec.getTime();
                    String mon = sdf.format(record);
                    Mon.setText(mon+"月");



                }
            }
        });

        Date dt = new Date();
        dt.equals("2020-05-01 00:00:00");

        note.whereGreaterThan("time",dt).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots ) {

                    storerecord rec = queryDocumentSnapshot.toObject(storerecord.class);
                    String point = rec.getPoints_given();
                    int point1 = Integer.valueOf(point);

                    Pointsgives = findViewById(R.id.pointsgive);
                    Pointsgives.setText(point);




                }


            }
        });
        //--------------------------------------------------------------------------------------


        //QRcode Scanner----------------------------------------------------------------------------
        btn_dot = (Button)

                findViewById(R.id.btn_dot);

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), QrcodeScanner.class);
                startActivity(j);
            }
        });
        btn_notificaiton = findViewById(R.id.storeNotification);
        btn_notificaiton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(getApplicationContext() , StoreNotification.class);
                startActivity(n);
            }
        });
    }
    //------------------------------------------------------------------------------------------




    //GMAP地圖--------------------------------------------------------------------------------------
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

/** this code is used to get the permission/ check the permission allow or not*/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.
                    ACCESS_FINE_LOCATION}, RESQUEST_PERMISSION_LOCATION);
        } else {
            getMyLocation();
            Toast.makeText(this, "Permission is allowed", Toast.LENGTH_SHORT).show();
        }

        mMap.setMyLocationEnabled(true);
    }

    @SuppressLint("MissingPermission")
    private void getMyLocation() {
        mfusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 18));
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RESQUEST_PERMISSION_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getMyLocation();
            } else {
                Toast.makeText(this, "this is permission is mandatory", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RESQUEST_PERMISSION_LOCATION);
            }
        }
    }
    //----------------------------------------------------------------------------------------------

}

