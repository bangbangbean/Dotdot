package com.example.dotdot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Profile extends Fragment {


    private TextView showusername;
    private TextView showpassword;
    private TextView showphone;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    private View view;





    public Profile() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView( inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.profile_fragment, container, false);

        showusername = (TextView)view.findViewById(R.id.inputPhone);
        showpassword = (TextView) view.findViewById(R.id.password);
        showphone = (TextView) view.findViewById(R.id.inputStore);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        memRef.whereEqualTo("name", "王小名")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
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


