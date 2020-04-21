package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.Context.MODE_PRIVATE;


public class MapInforWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference note = db.collection("Member")
            .document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card");

    private Context context;
    public MapInforWindowAdapter(Context context) {
        this.context = context;

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }



    @Override
    public View getInfoContents(Marker marker) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_infor_map, null);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView Message1 = view.findViewById(R.id.tvMessage1);
        TextView dot1 = view.findViewById(R.id.Dot1);
        TextView dot2 = view.findViewById(R.id.Dot2);
        TextView dot3 = view.findViewById(R.id.Dot3);
        TextView dot4 = view.findViewById(R.id.Dot4);
        TextView dot5 = view.findViewById(R.id.Dot5);
        TextView dot6 = view.findViewById(R.id.Dot6);
        TextView dot7 = view.findViewById(R.id.Dot7);
        TextView dot8 = view.findViewById(R.id.Dot8);
        TextView dot9 = view.findViewById(R.id.Dot9);
        TextView dot0 = view.findViewById(R.id.Dot10);

        tvTitle.setText(marker.getTitle());
        Message1.setText(marker.getSnippet());
        String point = marker.getSnippet();
        System.out.println(point);
        int points = Integer.valueOf(point);


        if(points == 1){

            dot1.setBackgroundResource(R.drawable.pink_dot);
        }

        else if(points == 2){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
        }

        else if(points == 3){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
            dot3.setBackgroundResource(R.drawable.pink_dot);
        }
        else if(points == 4){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
            dot3.setBackgroundResource(R.drawable.pink_dot);
            dot4.setBackgroundResource(R.drawable.pink_dot);

        }
        else if(points == 5){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
            dot3.setBackgroundResource(R.drawable.pink_dot);
            dot4.setBackgroundResource(R.drawable.pink_dot);
            dot5.setBackgroundResource(R.drawable.pink_dot);

        }
        else if(points == 6){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
            dot3.setBackgroundResource(R.drawable.pink_dot);
            dot4.setBackgroundResource(R.drawable.pink_dot);
            dot5.setBackgroundResource(R.drawable.pink_dot);
            dot6.setBackgroundResource(R.drawable.pink_dot);
        }
        else if(points == 7){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
            dot3.setBackgroundResource(R.drawable.pink_dot);
            dot4.setBackgroundResource(R.drawable.pink_dot);
            dot5.setBackgroundResource(R.drawable.pink_dot);
            dot6.setBackgroundResource(R.drawable.pink_dot);
            dot7.setBackgroundResource(R.drawable.pink_dot);
        }
        else if(points == 8){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
            dot3.setBackgroundResource(R.drawable.pink_dot);
            dot4.setBackgroundResource(R.drawable.pink_dot);
            dot5.setBackgroundResource(R.drawable.pink_dot);
            dot6.setBackgroundResource(R.drawable.pink_dot);
            dot7.setBackgroundResource(R.drawable.pink_dot);
            dot8.setBackgroundResource(R.drawable.pink_dot);

        }

        else if(points == 9){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
            dot3.setBackgroundResource(R.drawable.pink_dot);
            dot4.setBackgroundResource(R.drawable.pink_dot);
            dot5.setBackgroundResource(R.drawable.pink_dot);
            dot6.setBackgroundResource(R.drawable.pink_dot);
            dot7.setBackgroundResource(R.drawable.pink_dot);
            dot8.setBackgroundResource(R.drawable.pink_dot);
            dot9.setBackgroundResource(R.drawable.pink_dot);


        }
        else if(points >= 10){
            dot1.setBackgroundResource(R.drawable.pink_dot);
            dot2.setBackgroundResource(R.drawable.pink_dot);
            dot3.setBackgroundResource(R.drawable.pink_dot);
            dot4.setBackgroundResource(R.drawable.pink_dot);
            dot5.setBackgroundResource(R.drawable.pink_dot);
            dot6.setBackgroundResource(R.drawable.pink_dot);
            dot7.setBackgroundResource(R.drawable.pink_dot);
            dot8.setBackgroundResource(R.drawable.pink_dot);
            dot9.setBackgroundResource(R.drawable.pink_dot);
            dot0.setBackgroundResource(R.drawable.pink_dot);

        }

        return view;
    }
}
