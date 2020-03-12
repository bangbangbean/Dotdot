package com.example.dotdot;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;


import java.util.ArrayList;

public class MemberIndex extends FragmentActivity implements OnMapReadyCallback, PopupMenu.OnMenuItemClickListener {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference accountRef = db.collection("Member").document();
    private CollectionReference memRef = db.collection("Member");
    private GoogleMap mMap;
    private static final int RESQUEST_PERMISSION_LOCATION = 1;
    private FusedLocationProviderClient mfusedLocationProviderClient;

    Button btn_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberindex);

        //------------------------Map---------------------------------------------------------------
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //------------------------QRcode-----------------------------------------------------------
        btn_dot = (Button) findViewById(R.id.btn_dot);

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), QrcodeCreate.class);
                startActivity(j);
            }
        });

    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addmarker();
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style));
        mMap.setInfoWindowAdapter(new MapInforWindowAdapter(this));


/** this code is used to get the permission/ check the permission allow or not*/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.
                    ACCESS_FINE_LOCATION}, RESQUEST_PERMISSION_LOCATION);
        } else {
            getMyLocation();
            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
        }

        mMap.setMyLocationEnabled(true);

    }


    public void addmarker() {
        ArrayList<LatLng> list = data.getPositions();
        for (int i = 0; i < list.size(); i++) {
            LatLng latLng = list.get(i);
            MarkerOptions options = new MarkerOptions();
            options.position(latLng);
            options.title("position -" + i);
            options.snippet("hello -" + i);
            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shop));
            mMap.addMarker(options);
        }

    }


    @SuppressLint("MissingPermission")
    private void getMyLocation() {
        mfusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(data.fju, 15));

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

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_test);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

}

