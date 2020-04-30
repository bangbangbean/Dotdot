package com.example.dotdot;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class StoreCreatLoyaltyCard extends Fragment {
    private String color = "blue";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference SloyaltyCardRef = db.document("store/nQnT8AAt4NYIRYZFZfAR");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_store_creat_loyalty_card, container, false);
        Button setRed = view.findViewById(R.id.redDot);
        Button setBlue = view.findViewById(R.id.blueDot);
        Button setGreen = view.findViewById(R.id.greenDot);
        Button setYellow = view.findViewById(R.id.yellowDot);
        Button saveBtn = view.findViewById(R.id.saveBtn);

        TextView dot1 = view.findViewById(R.id.dot1);
        TextView dot2 = view.findViewById(R.id.dot2);
        TextView dot3 = view.findViewById(R.id.dot3);
        TextView dot4 = view.findViewById(R.id.dot4);
        TextView dot5 = view.findViewById(R.id.dot5);
        TextView dot6 = view.findViewById(R.id.dot6);
        TextView dot7 = view.findViewById(R.id.dot7);
        EditText inputDollar = view.findViewById(R.id.inputDollar);


        //選擇顏色---------------------------------------------------------------------------
        setGreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Drawable dot_green = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.green_dot);
                dot1.setBackgroundDrawable(dot_green);
                dot2.setBackgroundDrawable(dot_green);
                dot3.setBackgroundDrawable(dot_green);
                dot4.setBackgroundDrawable(dot_green);
                dot5.setBackgroundDrawable(dot_green);
                dot6.setBackgroundDrawable(dot_green);
                dot7.setBackgroundDrawable(dot_green);
                color = "green";
            }
        });setBlue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable dot_blue = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.blue_dot);
                dot1.setBackgroundDrawable(dot_blue);
                dot2.setBackgroundDrawable(dot_blue);
                dot3.setBackgroundDrawable(dot_blue);
                dot4.setBackgroundDrawable(dot_blue);
                dot5.setBackgroundDrawable(dot_blue);
                dot6.setBackgroundDrawable(dot_blue);
                dot7.setBackgroundDrawable(dot_blue);
                color = "blue";
            }
        });setRed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable dot_red = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.red_dot);
                dot1.setBackgroundDrawable(dot_red);
                dot2.setBackgroundDrawable(dot_red);
                dot3.setBackgroundDrawable(dot_red);
                dot4.setBackgroundDrawable(dot_red);
                dot5.setBackgroundDrawable(dot_red);
                dot6.setBackgroundDrawable(dot_red);
                dot7.setBackgroundDrawable(dot_red);
                color = "red";
            }
        });setYellow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable dot_yellow = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.yellow_dot);
                dot1.setBackgroundDrawable(dot_yellow);
                dot2.setBackgroundDrawable(dot_yellow);
                dot3.setBackgroundDrawable(dot_yellow);
                dot4.setBackgroundDrawable(dot_yellow);
                dot5.setBackgroundDrawable(dot_yellow);
                dot6.setBackgroundDrawable(dot_yellow);
                dot7.setBackgroundDrawable(dot_yellow);
                color = "yellow";
            }
        });
        //---------------------------------------------------------


        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String money = inputDollar.getText().toString();
                Map<Object, Object> StoreCreatLoyaltyCard = new HashMap<>();
                StoreCreatLoyaltyCard.put("color", color);
                StoreCreatLoyaltyCard.put("Threshold", money);
                SloyaltyCardRef.set(StoreCreatLoyaltyCard, SetOptions.merge());
            }
        });

        return view;
    }
}
