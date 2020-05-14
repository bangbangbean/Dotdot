package com.example.dotdot.BeforeLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dotdot.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Forgetpassword_2 extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    String memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword_2);
        Button nextbtn = (Button) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView newPassword = (TextView) findViewById(R.id.newPassword);
                TextView inputAgain = (TextView) findViewById(R.id.inputAgain);
                int length = 0;
                int same = 0;
                String newpassword = newPassword.getText().toString();
                String inputagain = inputAgain.getText().toString();

                if (newPassword.length() < 8) {
                    length=1;
                    Toast.makeText(Forgetpassword_2.this, "新密碼長度不得小於8!", Toast.LENGTH_SHORT).show();
                } else if (!newpassword.equals(inputagain)) {
                    same=1;
                    Toast.makeText(Forgetpassword_2.this, "輸入不一致，請確認輸入是否有誤!", Toast.LENGTH_SHORT).show();
                }

                if (length == 0 && same == 0) {

                    memRef.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                        memberId = documentSnapshot.getId();
                                    }
                                }
                            });

                    Map<Object, Object> upDataPassword = new HashMap<>();
                    upDataPassword.put("password", newpassword);

                    memRef.document(memberId).set(upDataPassword, SetOptions.merge());
                    Toast.makeText(Forgetpassword_2.this, "密碼已更新，請重新登入", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Forgetpassword_2.this, Login.class);
                    startActivity(intent);
                    //member的亂碼Id
                    SharedPreferences memberId = getSharedPreferences("save_memberId", MODE_PRIVATE);
                    memberId.edit()
                            .clear()
                            .apply();
                }
            }
        });
    }
}
