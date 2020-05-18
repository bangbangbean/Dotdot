package com.example.dotdot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dotdot.BotNav_mem.botnav;
import com.example.dotdot.BotNav_mem.botnav2;
import com.example.dotdot.BotNav_mem.botnav3;
import com.example.dotdot.BotNav_mem.botnav4;

public class PopActivity extends Activity {


    Button btn_profile;
    Button btn_dot;
    Button btn_card;
    Button btn_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8) , (int)(height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = 25;

        getWindow().setAttributes(params);

        Button card = findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(PopActivity.this, botnav.class);
                startActivity(p);
            }
        });

        btn_profile = findViewById(R.id.profilee);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(PopActivity.this,botnav.class);
                startActivity(p);
            }
        });

        btn_dot = findViewById(R.id.dot);
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PopActivity.this , botnav2.class);
                startActivity(i);
            }
        });

        btn_card = findViewById(R.id.card);
        btn_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(PopActivity.this , botnav3.class);
                startActivity(n);
            }
        });

        btn_record = findViewById(R.id.record);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v = new Intent(PopActivity.this , botnav4.class);
                startActivity(v);
            }
        });

    }
}
