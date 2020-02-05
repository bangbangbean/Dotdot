package com.example.dotdot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;


import io.opencensus.tags.Tag;


public class Regist extends AppCompatActivity {

    private EditText phone;
    private EditText password;
    private EditText passagain;
    private EditText username;
    private EditText birthday;
    private Button registbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        registbt = (Button) findViewById(R.id.registbt);
        registbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Creatmember();
                Intent intent= new Intent(Regist.this, Forgetpassword.class);
                startActivity(intent);
            }
        });

        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        passagain=(EditText)findViewById(R.id.passagain);
        username=(EditText)findViewById(R.id.username);
        birthday=(EditText)findViewById(R.id.birthday);



    }
    public void Creatmember() {

//        boolean pho = false;//手機號碼驗證
//        boolean pass = false;//密碼驗證
//
//        int reg=0;
//        int nu=0;
//        int pas=0;
//        int pasch=0;
//        for (int i =0;i<password.getTextSize();i=i+1){
//            if (account.equals(accr.get(i))){
//                pho=true;
//                break;
//            }
//        }
//
////            test.setText("帳號是: "+account+"\n"+"信箱是: "+email+"\n"+"密碼是: "+pass+"\n"+"確認密碼是: "+chpass);
//
//        if(reg==1){
//            test2.setText("Warning : 註冊帳號已有人使用");
//        }
//        else if ( phone.length()==0 || account.length()==0 || pass.length()==0 || chpass.length()==0){
//            nu=1;
//            test2.setText(" Warning : 註冊欄位不得為空");
//
//        }
//        else if (pass.length()<6){
//            pas=1;
//            test2.setText("Warning : 密碼至少為6碼");
//        }
//        else if (pass.equals(chpass)==false){
//            pasch=1;
//            test2.setText("Warning : 密碼與確認密碼不符");
//        }

        String phonee=phone.getText().toString();
        String birthdayy=birthday.getText().toString();
        String passwordd=password.getText().toString();
        String usernamee=username.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> Member = new HashMap<>();
        Member.put("birth",birthdayy);
        Member.put("password",passwordd);
        Member.put("name",usernamee);
        Member.put("phone",phonee);
        db.collection("Member")
                .add(Member)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });
    }
}
