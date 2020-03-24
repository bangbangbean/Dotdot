package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class StoreCreatLoyaltyCard extends AppCompatActivity {
    private String color = "blue";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference SloyaltyCardRef = db.document("store/N0yDSwbrTTVP2nZSKw1h");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_creat_loyalty_card);

        Button setBlue = (Button)findViewById(R.id.blueDot);
        Button setGreen = (Button)findViewById(R.id.greenDot);
        Button setYellow = (Button)findViewById(R.id.yellowDot);
        Button setRed = (Button)findViewById(R.id.redDot);
        Button saveBtn = (Button)findViewById(R.id.saveBtn);

        TextView dot1 = (TextView)findViewById(R.id.dot1);
        TextView dot2 = (TextView)findViewById(R.id.dot2);
        TextView dot3 = (TextView)findViewById(R.id.dot3);
        TextView dot4 = (TextView)findViewById(R.id.dot4);
        TextView dot5 = (TextView)findViewById(R.id.dot5);
        TextView dot6 = (TextView)findViewById(R.id.dot6);
        TextView dot7 = (TextView)findViewById(R.id.dot7);
        EditText inputDollar = (EditText) findViewById(R.id.inputDollar);


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
                StoreLoyaltyCard loytyCard = new StoreLoyaltyCard(color,money);
                SloyaltyCardRef.set(loytyCard, SetOptions.merge());
            }
        });
    }
}
