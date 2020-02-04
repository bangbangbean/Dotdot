package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Regist extends AppCompatActivity {

    private EditText phone;
    private EditText password;
    private EditText passagain;
    private EditText username;
    private EditText erqwer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
    }
}
