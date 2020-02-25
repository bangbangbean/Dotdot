package com.example.dotdot;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class marker extends MapActivity implements GoogleMap.OnMapClickListener {

    @Override
    protected void onMapReady() {
        setMap();
        addMarker();
        getMap().setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style));

    }


        private void setMap(){
        getMap().setOnMapClickListener(this);
        final LatLng FJU = new LatLng(25.0368, 121.4322);
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(FJU,16));

    }
    private void  addMarker(){
        ArrayList<LatLng> list = data.getPositions();
        for (int i=0 ; i<list.size();i++){
            LatLng latLng = list.get(i);
            MarkerOptions options = new MarkerOptions();
            options.position(latLng);
            options.title("position -" + i );
            options.snippet("hello -" + i);
            getMap().addMarker(options);
        }
    }
    public void moveCamera(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        //
        ArrayList<LatLng> list = data.getPositions();
        for(LatLng latLng : list){
            builder.include(latLng);
        }
        //
        LatLngBounds bounds = builder.build();
        //
        getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,50),3000,null);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this,"this is permission is mandatory",Toast.LENGTH_SHORT).show();
    }



}



