package com.example.dotdot.BeforeLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Forgetpassword_1 extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    String hintAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword_1);

        String phone = getSharedPreferences("save_forgetpasswordData", MODE_PRIVATE)
                .getString("phone", "phoneerror");

        memRef.whereEqualTo("phone",phone)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Member mem = documentSnapshot.toObject(Member.class);
                            String Q = mem.getHintQuestion();
                            TextView hintQuestion = (TextView)findViewById(R.id.inputhintQuestion);
                            hintQuestion.setText(Q);
                            hintAnswer = mem.getHintAnswer();
                        }
                    }
                });

        Button nextBtn = (Button)findViewById(R.id.nextbtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputhintAnswer = (EditText)findViewById(R.id.newPassword);
                String inputhintA = inputhintAnswer.getText().toString();

                if(inputhintA.equals(hintAnswer)){
                    Intent intent = new Intent(Forgetpassword_1.this, Forgetpassword_2.class);
                    startActivity(intent);
                    Toast.makeText(Forgetpassword_1.this, "回答正確!", Toast.LENGTH_SHORT).show();
                }else if(!inputhintA.equals(hintAnswer)){
                    Toast.makeText(Forgetpassword_1.this, "回答錯誤，請重新輸入!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
