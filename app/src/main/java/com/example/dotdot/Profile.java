package com.example.dotdot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dotdot.BeforeLogin.Login;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Profile extends Fragment {

    private TextView showusername;
    private TextView showpassword;
    private TextView showphone;
    private Integer a = 1;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment, container, false);
        showusername = (TextView)view.findViewById(R.id.inputName);
        showpassword = (TextView) view.findViewById(R.id.password);
        showphone = (TextView) view.findViewById(R.id.newPassword);
        Button editBtn = (Button)view.findViewById(R.id.editBtn);
        Button logoutBtn = (Button)view.findViewById(R.id.logoutBtn);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences memberId =getActivity().getSharedPreferences("save_memberId", MODE_PRIVATE);
                memberId.edit()
                        .clear()
                        .apply();
                Intent i = new Intent(getActivity(), Login.class);
                startActivity(i);
            }
        });


        //鎖定編輯
        showusername.setFocusable(false);
        showusername.setFocusableInTouchMode(false);
        showpassword.setFocusable(false);
        showpassword.setFocusableInTouchMode(false);
        showphone.setFocusable(false);
        showphone.setFocusableInTouchMode(false);

        //控制editText可否編輯
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a == 1){
                    showusername.setFocusableInTouchMode(true);
                    showusername.setFocusable(true);
                    showusername.requestFocus();

                    showpassword.setFocusableInTouchMode(true);
                    showpassword.setFocusable(true);
                    showpassword.requestFocus();
                    a = 0;
                }
                else {
                    showusername.setFocusable(false);
                    showusername.setFocusableInTouchMode(false);
                    showpassword.setFocusable(false);
                    showpassword.setFocusableInTouchMode(false);
                    a = 1;
                }
                String name = showusername.getText().toString();
                String password = showpassword.getText().toString();

                Map<Object, Object> upData = new HashMap<>();
                upData.put("name", name);
                upData.put("password", password);
                //member的亂碼Id
                String memberId =getActivity().getSharedPreferences("save_memberId", MODE_PRIVATE)
                        .getString("user_id", "沒會員登入");
                memRef.document(memberId).set(upData, SetOptions.merge());
            }
        });
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //member的亂碼Id
        String memberId =getActivity().getSharedPreferences("save_memberId", MODE_PRIVATE)
                .getString("user_id", "沒會員登入");

        db.collection("Member")
                .document(memberId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String name = documentSnapshot.getString("name");
                            String password = documentSnapshot.getString("password");
                            String phone = documentSnapshot.getString("phone");

                            showusername.setText(name);
                            showpassword.setText(password);
                            showphone.setText(phone);
                        }
                    }
                });
    }
}
