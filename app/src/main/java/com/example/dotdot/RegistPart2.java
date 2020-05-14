package com.example.dotdot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistPart2 extends AppCompatActivity {


    EditText inputhintQuestion;
    EditText inputhintAnswer;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_part2);

        Button registbtn = (Button) findViewById(R.id.nextbtn);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        //隱藏註冊btn
        registbtn.setVisibility(View.GONE);
        //CheckBox狀態改變觸發動作
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判斷CheckBox是否有勾選，同CheckBox.isChecked()
                if (isChecked) {
                    //CheckBox狀態 : 已勾選，顯示註冊btn
                    registbtn.setVisibility(View.VISIBLE);
                } else {
                    //CheckBox狀態 : 未勾選，隱藏註冊btn
                    registbtn.setVisibility(View.GONE);
                }
            }
        });

        registbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputhintQuestion = (EditText) findViewById(R.id.inputHintQuestion);
                inputhintAnswer = (EditText) findViewById(R.id.inputHintAnswer);

                String hintQuestion = inputhintQuestion.getText().toString();
                String hintAnswer = inputhintAnswer.getText().toString();
                int hintQuestionConfirm = 0;//確認有沒有輸入提示答案
                int hintAnswerConfirm = 0;//確認有沒有輸入提示答案

                if (hintQuestion.length() == 0) {
                    hintQuestionConfirm = 1;
                    Toast.makeText(RegistPart2.this, "問題題目不得為空 !", Toast.LENGTH_LONG).show();
                } else if (hintAnswer.length() == 0) {
                    hintAnswerConfirm = 1;
                    Toast.makeText(RegistPart2.this, "題目答案不得為空 !", Toast.LENGTH_LONG).show();
                }

                if (hintAnswerConfirm == 0 && hintQuestionConfirm == 0) {
                    Creatmember();
                    Intent intent = new Intent(RegistPart2.this, Login.class);
                    startActivity(intent);
                    Toast.makeText(RegistPart2.this, "註冊成功!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    //上傳-------------------------------------------------------------------------------------------
    public void Creatmember() {

        String phone = getSharedPreferences("save_registData", MODE_PRIVATE)
                .getString("phone", "phoneerror");
        String password = getSharedPreferences("save_registData", MODE_PRIVATE)
                .getString("password", "passworderror");
        String name = getSharedPreferences("save_registData", MODE_PRIVATE)
                .getString("name", "nameerror");
        String hintQuestion = inputhintQuestion.getText().toString();
        String hintAnswer = inputhintAnswer.getText().toString();

        Member account = new Member(name, password, phone, hintQuestion, hintAnswer);
        memRef.add(account);
    }
}
