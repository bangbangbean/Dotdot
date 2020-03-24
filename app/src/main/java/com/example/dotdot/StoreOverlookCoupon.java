package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoreOverlookCoupon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_overlook_coupon);

        Button creatCoupon = (Button)findViewById(R.id.creatBtn);
        creatCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , StoreCreatCoupon.class);
                startActivity(i);
            }
        });
    }
}
