package com.example.dotdottest;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Regist extends AppCompatActivity {

    private EditText phone;
    private EditText password;
    private EditText passagain;
    private EditText username;
    private EditText birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        passagain=(EditText)findViewById(R.id.passagain);
        username=(EditText)findViewById(R.id.username);
        birthday=(EditText)findViewById(R.id.birthday);

    }
}
