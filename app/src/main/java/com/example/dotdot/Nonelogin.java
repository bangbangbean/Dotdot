package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dotdot.BeforeLogin.Login;

public class Nonelogin extends Activity {

    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonelogin);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = 25;

        getWindow().setAttributes(params);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(Nonelogin.this, Login.class);
                startActivity(p);
            }
        });


    }
}
