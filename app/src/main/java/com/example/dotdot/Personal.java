package com.example.dotdot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.dotdot.Member.*;

public class Personal extends AppCompatActivity {
    private TextView showusername;
    private TextView showbirthday;
    private TextView showphone;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        showusername = (TextView) findViewById(R.id.username);
        showbirthday = (TextView) findViewById(R.id.birthday);
        showphone = (TextView) findViewById(R.id.phone);

//        memRef.whereEqualTo("name","王小名")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        String username = Member.getName();
//                        String birthday = Member.getBirthday();
//                        String phone = Member.getPhone();
//
//                        showusername.setText(username);
//                        showbirthday.setText(birthday);
//                        showphone.setText(phone);
//                    }
//                });
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Member mem = (Member) queryDocumentSnapshots.toObjects(Member.class);
//
//                        String username = mem.getName();
//                        String birthday = mem.getBirthday();
//                        String phone = mem.getPhone();
//
//                        showusername.setText(username);
//                        showbirthday.setText(birthday);
//                        showphone.setText(phone);
//                    }
//                });
    }

}
