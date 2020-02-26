package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Login extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    private EditText inputPassword;
    private EditText inputPhone;
    private Button loginbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputPhone = (EditText)findViewById(R.id.inputPhone);
        loginbt = (Button)findViewById(R.id.loginbt);

        String password = inputPassword.getText().toString();
        String phone = inputPhone.getText().toString();

        loginbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                memRef.whereEqualTo("phone",phone)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    Member mem = documentSnapshot.toObject(Member.class);
                                    String pass = mem.getPassword();
                                    if (pass.equals(password)){
                                        
                                    }
                                }
                            }
                        });
            }
        });

    }
}
