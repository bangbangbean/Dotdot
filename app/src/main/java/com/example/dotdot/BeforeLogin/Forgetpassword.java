package com.example.dotdot.BeforeLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dotdot.Member;
import com.example.dotdot.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Forgetpassword extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    int haveAcc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        TextView inputphone = (TextView) findViewById(R.id.newPassword);
        Button nextbtn = (Button) findViewById(R.id.nextbtn);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memRef.get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Member mem = documentSnapshot.toObject(Member.class);
                                    String phone = mem.getPhone();
                                    String inputph = inputphone.getText().toString().trim();
                                    if (phone.equals(inputph)) {
                                        SharedPreferences forgetpasswordData = getSharedPreferences("save_forgetpasswordData", MODE_PRIVATE);
                                        forgetpasswordData.edit()
                                                .putString("phone",phone)
                                                .apply();
                                        Intent intent = new Intent(Forgetpassword.this, Forgetpassword_1.class);
                                        startActivity(intent);
                                        Toast.makeText(Forgetpassword.this, "登入成功 !", Toast.LENGTH_SHORT).show();
                                    } else if(!phone.equals(inputph)){
                                        Toast.makeText(Forgetpassword.this, "此手機號碼並未註冊過 !\n請確認輸入是否有誤", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }
}
