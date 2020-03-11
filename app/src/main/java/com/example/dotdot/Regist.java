package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Regist extends AppCompatActivity {

    private EditText inputphone;
    private EditText inputpassword;
    private EditText inputpassagain;
    private EditText inputname;
    private EditText inputbirthday;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    int onlyacc = 0;//判斷帳號有沒有重複
    int allinput = 0;//所有欄位都已填寫
    int passconfirm = 0;//密碼跟確認密碼輸入一致
    int passlen = 0;//密碼長度至少8個

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Button registbtn = (Button) findViewById(R.id.registbtn);
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        //隱藏註冊btn
        registbtn.setVisibility(View.GONE);
        //CheckBox狀態改變觸發動作
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判斷CheckBox是否有勾選，同CheckBox.isChecked()
                if(isChecked) {
                    //CheckBox狀態 : 已勾選，顯示註冊btn
                    registbtn.setVisibility(View.VISIBLE);
                } else {
                    //CheckBox狀態 : 未勾選，隱藏註冊btn
                    registbtn.setVisibility(View.GONE);
                }
            }
        });


        registbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputphone = (EditText) findViewById(R.id.inputStore);
                inputpassword = (EditText) findViewById(R.id.password);
                inputpassagain = (EditText) findViewById(R.id.passagain);
                inputname = (EditText) findViewById(R.id.inputPhone);
                inputbirthday = (EditText) findViewById(R.id.birthday);

                String inputph = inputphone.getText().toString();
                String inputpass = inputpassword.getText().toString();
                String inputpassag = inputpassagain.getText().toString();

                memRef.get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Member mem = documentSnapshot.toObject(Member.class);
                                    String phone = mem.getPhone();
                                    if (phone.equals(inputph)) {
                                        onlyacc += 1;
                                    }
                                }
                            }
                        });

                if (onlyacc != 0) {
                    onlyacc = 1;
                    Toast.makeText(Regist.this, "此手機號碼已有人使用", Toast.LENGTH_LONG).show();
                } else if (inputphone.length() == 0 || inputpassword.length() == 0 || inputpassagain.length() == 0
                        || inputname.length() == 0) {
                    allinput = 1;
                    Toast.makeText(Regist.this, "註冊欄位不得為空", Toast.LENGTH_LONG).show();
                } else if (inputpassword.length() < 8) {
                    passlen = 1;
                    Toast.makeText(Regist.this, "密碼長度小於8個字元", Toast.LENGTH_LONG).show();
                } else if (!inputpass.equals(inputpassag)) {
                    passconfirm = 1;
                    Toast.makeText(Regist.this, "密碼與確認密碼輸入不一致", Toast.LENGTH_LONG).show();
                }

                if (allinput == 0 && onlyacc == 0 && passlen == 0 && passconfirm == 0) {
                    //新增資料到資料庫
                    Creatmember();

                    Intent intent = new Intent(Regist.this, Login.class);
                    startActivity(intent);
                }
                onlyacc = 0;
                allinput = 0;
                passlen = 0;
                passconfirm = 0;//重製變數
            }
        });
    }


    public void Creatmember() {
        String phone = inputphone.getText().toString();
        String password = inputpassword.getText().toString();
        String name = inputname.getText().toString();

        Member account = new Member(name, password, phone);
        memRef.add(account);
    }
}
