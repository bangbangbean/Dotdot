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
    private TextView showpassword;
    private TextView showphone;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        showusername = (TextView) findViewById(R.id.username);
        showpassword = (TextView) findViewById(R.id.password);
        showphone = (TextView) findViewById(R.id.phone);

    }

    protected void onStart(){
        super.onStart();
        memRef.whereEqualTo("name","王小名")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            Member mem = documentSnapshot.toObject(Member.class);
                            String name = mem.getName();
                            String password = mem.getPassword();
                            String phone = mem.getPhone();

                            showusername.setText(name);
                            showpassword.setText(password);
                            showphone.setText(phone);
                        }

                    }
                });
    }

}
