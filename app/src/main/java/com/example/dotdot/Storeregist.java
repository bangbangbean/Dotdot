package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Storeregist extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference storeRef = db.collection("Store");
    EditText inputPhone = (EditText) findViewById(R.id.inputName);
    EditText inputStore = (EditText) findViewById(R.id.newPassword);
    EditText inputAddress = (EditText) findViewById(R.id.inputAddress);
    int allinput = 0;//所有欄位都已填寫
    int phonelen = 0;//密碼長度至少8個
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeregist);

        Button registbtn = (Button) findViewById(R.id.nextbtn);
        registbtn.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                if (inputPhone.length() == 0 || inputAddress.length() == 0 || inputStore.length() == 0) {
                    allinput = 1;
                    Toast.makeText(Storeregist.this, "註冊欄位不得為空", Toast.LENGTH_LONG).show();
                } else if (inputPhone.length() != 10) {
                    phonelen = 1;
                    Toast.makeText(Storeregist.this, "電話號碼格式不符", Toast.LENGTH_LONG).show();
                }

                if (allinput == 0  && phonelen == 0) {
                    //新增資料到資料庫
                    creatStore();

                    Intent intent = new Intent(Storeregist.this, StoreIndex.class);
                    startActivity(intent);
                }
                allinput = 0;
                phonelen = 0;//重製變數
            }
        });
    }
    public void creatStore(){
        String store = inputStore.getText().toString();
        String address = inputAddress.getText().toString();
        String phone = inputPhone.getText().toString();
        Store store1 = new Store(store, address, phone);
        storeRef.add(store1);
    }
}

