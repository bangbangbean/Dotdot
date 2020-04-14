package com.example.dotdot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Login extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences memberSession = getSharedPreferences("save_useraccount", MODE_PRIVATE);
        final SharedPreferences.Editor editor = memberSession.edit();


        EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
        EditText inputPhone = (EditText) findViewById(R.id.inputStore);
        Button loginbtn = (Button) findViewById(R.id.loginbt);
        TextView registbtn = (TextView) findViewById(R.id.registbtn);
        TextView forgetPassbtn = (TextView) findViewById(R.id.forgetPassbtn);
        TextView storebtn = (TextView) findViewById(R.id.storebtn);

        registbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Regist.class);
                startActivity(intent);
            }
        });

        storebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Storeregist.class);
                startActivity(intent);
            }
        });

        forgetPassbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Forgetpassword.class);
                startActivity(intent);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phone = inputPhone.getText().toString();
                String password = inputPassword.getText().toString();
                memRef.whereEqualTo("phone", phone)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override

                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Member mem = documentSnapshot.toObject(Member.class);
                                    String documentId = documentSnapshot.getId();
                                    String pass = mem.getPassword();
                                    if (pass.equals(password)) {
                                        editor.putString("user_id", "iICTR1JL4eAG4B3QBi1S");
                                        editor.commit();
                                        Intent intent = new Intent(Login.this, test.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Login.this, "登入失敗", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
            }
        });


    }
}
