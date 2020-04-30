package com.example.dotdot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class store_pop extends Activity {


    Button btn_profile;
    Button btn_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_pop);

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
                Intent p = new Intent(store_pop.this,Store_botnav.class);
                startActivity(p);
            }
        });

        btn_profile = findViewById(R.id.profilee);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(store_pop.this,Store_botnav.class);
                Bundle bundle = new Bundle();
                bundle.putString("key","profile");
                p.putExtras(bundle);
                startActivity(p);
            }
        });

        btn_dot = findViewById(R.id.dot);
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(store_pop.this , Store_botnav.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "dot");
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }
}
