package com.example.dotdot.BeforeLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dotdot.Member;
import com.example.dotdot.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

//ok
public class Regist extends AppCompatActivity {

    private EditText inputphone;
    private EditText inputpassword;
    private EditText inputpassagain;
    private EditText inputname;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    int onlyacc = 0;//判斷帳號有沒有重複
    int allinput = 0;//所有欄位都已填寫
    int passconfirm = 0;//密碼跟確認密碼輸入一致
    int passlen = 0;//密碼長度至少8個
    int phonelen = 0;//電話號碼須為10


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Button nextbtn = (Button) findViewById(R.id.nextbtn);

        //確認輸入格式並跳轉到下一頁-----------------------------------------------------------------
        nextbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputphone = (EditText) findViewById(R.id.newPassword);
                inputpassword = (EditText) findViewById(R.id.password);
                inputpassagain = (EditText) findViewById(R.id.passagain);
                inputname = (EditText) findViewById(R.id.inputName);

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
                    Toast.makeText(Regist.this, "此手機號碼已有人使用 !", Toast.LENGTH_LONG).show();
                } else if (inputphone.length() == 0 || inputpassword.length() == 0 || inputpassagain.length() == 0
                        || inputname.length() == 0) {
                    allinput = 1;
                    Toast.makeText(Regist.this, "註冊不得有欄位為空 !", Toast.LENGTH_LONG).show();
                } else if (inputpassword.length() < 8) {
                    passlen = 1;
                    Toast.makeText(Regist.this, "密碼長度不得小於8個字元 !", Toast.LENGTH_LONG).show();
                } else if (inputphone.length() != 10) {
                    phonelen = 1;
                    Toast.makeText(Regist.this, "電話號碼格式錯誤 !", Toast.LENGTH_LONG).show();
                } else if (!inputpass.equals(inputpassag)) {
                    passconfirm = 1;
                    Toast.makeText(Regist.this, "密碼與確認密碼輸入不一致 !", Toast.LENGTH_LONG).show();
                }

                if (allinput == 0 && onlyacc == 0 && passlen == 0 && passconfirm == 0 && phonelen == 0) {
                    //將資料儲存並傳到下一頁-----------------------------------------------------------
                    String phone = inputphone.getText().toString();
                    String password = inputpassword.getText().toString();
                    String name = inputname.getText().toString();
                    SharedPreferences registData = getSharedPreferences("save_registData", MODE_PRIVATE);
                    registData.edit()
                            .putString("name", name)
                            .putString("phone", phone)
                            .putString("password", password)
                            .apply();

                    Intent intent = new Intent(Regist.this, RegistPart2.class);
                    startActivity(intent);
                }
            }
        });
    }

}
