package com.example.dotdot;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.dotdot.QRcode.QrcodeCreate;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.widget.Toast.makeText;

public class MemberIndex extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private static final int RESQUEST_PERMISSION_LOCATION = 1;
    private FusedLocationProviderClient mfusedLocationProviderClient;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference note = db.collection("Member");

    Button home;
    Button btn_dot;
    Button btn_notificaiton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberindex);

        SharedPreferences storeref = this.getSharedPreferences("save_store", MODE_PRIVATE);


        note.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    Loyalty_card loyalty_card = queryDocumentSnapshot.toObject(Loyalty_card.class);
                    String name = loyalty_card.getStore();
                    storeref.edit().putString("save_store", name).commit();


                }

            }
        });


        //------------------------Map---------------------------------------------------------------
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //-----------------------左下功能列----------------------------------------------------------
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //------------------------action-----------------------------------------------------------
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                startActivity(i);
            }
        });


        //------------------------QRcode-----------------------------------------------------------
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), QrcodeCreate.class);
                startActivity(j);
            }
        });

        //------------------------Notification-----------------------------------------------------------
        btn_notificaiton = findViewById(R.id.notification);
        btn_notificaiton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(getApplicationContext(), notification.class);
                startActivity(n);
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addmarker();
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style));

        mMap.setInfoWindowAdapter(new MapInforWindowAdapter(this));
        mMap.setOnInfoWindowClickListener(this);
/** this code is used to get the permission/ check the permission allow or not*/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.
                    ACCESS_FINE_LOCATION}, RESQUEST_PERMISSION_LOCATION);
        } else {
            getMyLocation();
            makeText(this, "登入成功", Toast.LENGTH_LONG).show();
        }

        mMap.setMyLocationEnabled(true);

    }

    public void addmarker() {
        String memberId =getSharedPreferences("save_memberId", MODE_PRIVATE)
                .getString("user_id", "沒會員登入");

        note.document(memberId).collection("loyalty_card")
                .document("BxskPfoZCfztCUSuDUOu")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Loyalty_card poi = documentSnapshot.toObject(Loyalty_card.class);
                            MarkerOptions options = new MarkerOptions();
                            String title = poi.getStore();
                            String point = poi.getPoints_owned();
                            String color = poi.getColor();
                            options.title("椒麻雞大王");
                            options.snippet(point);
                            options.position(storerecord.chicken);
                            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shop1));
                            mMap.addMarker(options);

                        }

                    }
                });
        note.document(memberId).collection("loyalty_card")
                .document("11cbdffohVBW7MQ1lDh4")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Loyalty_card poi = documentSnapshot.toObject(Loyalty_card.class);
                            MarkerOptions options = new MarkerOptions();
                            String title = poi.getStore();
                            String point = poi.getPoints_owned();
                            options.title("椒麻雞大王");
                            options.snippet(point);
                            options.position(storerecord.chicken);
                            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shop1));
                            mMap.addMarker(options);

                        }

                    }
                });


        note.document(memberId).collection("loyalty_card")
                .document("ZuzoCsJcH5kXvCjIpKfC")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Loyalty_card poi = documentSnapshot.toObject(Loyalty_card.class);
                            MarkerOptions options1 = new MarkerOptions();
                            String title = poi.getStore();
                            String point = poi.getPoints_owned();
                            options1.title("崔舍");
                            options1.snippet(point);
                            options1.position(storerecord.loc1);
                            options1.icon(BitmapDescriptorFactory.fromResource(R.drawable.shop2));
                            mMap.addMarker(options1);

                        }

                    }
                });

        note.document(memberId).collection("loyalty_card")
                .document("IINi2hdusAGMTRUrszFr")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Loyalty_card poi = documentSnapshot.toObject(Loyalty_card.class);
                    MarkerOptions options1 = new MarkerOptions();
                    String title = poi.getStore();
                    String point = poi.getPoints_owned();
                    options1.title("崔舍");
                    options1.snippet(point);
                    options1.position(storerecord.loc1);
                    options1.icon(BitmapDescriptorFactory.fromResource(R.drawable.shop2));
                    mMap.addMarker(options1);

                }

            }
        });


    }


    @SuppressLint("MissingPermission")
    private void getMyLocation() {
        mfusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                    //記得改成mylocation
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(storerecord.chicken, 17));

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


    @Override
    public void onInfoWindowClick(Marker marker) {

        Intent intent = new Intent(MemberIndex.this, botnav.class);
        startActivity(intent);

    }


}
