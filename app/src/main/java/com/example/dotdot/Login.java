package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;


public class Login extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
        EditText inputPhone = (EditText) findViewById(R.id.inputPhone);
        Button loginbt = (Button) findViewById(R.id.loginbt);



        loginbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phone = inputPhone.getText().toString();
                String password = inputPassword.getText().toString();
                memRef.whereEqualTo("phone",phone)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Member mem = documentSnapshot.toObject(Member.class);
                                    String pass = mem.getPassword();
                                    if (pass.equals(password)){
                                        Toast.makeText(Login.this,"登入成功",Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(Login.this, MemberIndex.class);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(Login.this,"登入失敗",Toast.LENGTH_LONG).show();
                                    }
                                    Toast.makeText(Login.this,"登入失敗",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


    }
}
