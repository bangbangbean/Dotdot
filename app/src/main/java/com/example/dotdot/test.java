package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class test extends AppCompatActivity {
    private EditText nameinput;
    private EditText passwordinput;
    private EditText phoneinput;
    private TextView getinfo;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference accountRef = db.collection("Member").document();
    private CollectionReference memRef = db.collection("Member");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        nameinput = findViewById(R.id.name);
        passwordinput = findViewById(R.id.password);
        phoneinput = findViewById(R.id.inputStore);
        getinfo = findViewById(R.id.getinfo);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        memRef.addSnapshotListener(this,new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot queryDocumentSnapshots,FirebaseFirestoreException e) {
//                if (e != null) {
//                    return;
//                }
//               String data = "";
//                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                    Member mem = documentSnapshot.toObject(Member.class);
//
//                    String name = mem.getName();
//                    String password = mem.getPassword();
//                    String phone = mem.getPhone();
//
//                    data += "name:" + name + "\npassword:" + password + "\nphone:" + phone + "\n\n" ;
//                }
//                getinfo.setText(data);
//            }
//        });
    }

    public void insertAccount(View v){
        String name = nameinput.getText().toString();
        String password = passwordinput.getText().toString();
        String phone = phoneinput.getText().toString();

        Member account = new Member(name,password,phone);

        memRef.document("BFyN264km5dWWtTPYivZ").collection("loyalty_card").add(account);
    }
    public void getAccount(View v){
        memRef.document("BFyN264km5dWWtTPYivZ").collection("loyalty_card").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String date = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            Member mem = documentSnapshot.toObject(Member.class);
                            String name = mem.getName();
                            String password = mem.getPassword();
                            String phone = mem.getPhone();
                            date += "name:" + name + "\npassword:" + password + "\nphone:" + phone + "\n\n" ;
                        }
                        getinfo.setText(date);
                    }
                });
    }

}
