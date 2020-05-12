package com.example.dotdot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Profile extends Fragment {

    private TextView showusername;
    private TextView showpassword;
    private TextView showphone;
    private Button button2;
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
        showphone = (TextView) view.findViewById(R.id.inputPhone);
        button2 = (Button)view.findViewById(R.id.button2);

        //鎖定編輯
        showusername.setFocusable(false);
        showusername.setFocusableInTouchMode(false);
        showpassword.setFocusable(false);
        showpassword.setFocusableInTouchMode(false);
        showphone.setFocusable(false);
        showphone.setFocusableInTouchMode(false);

        //控制editText可否編輯
        button2.setOnClickListener(new View.OnClickListener() {
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



        memRef.whereEqualTo("name", "邱冠儒")
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
//    @Override
//    public void onStart() {
//        super.onStart();
//        memRef.whereEqualTo("name", "王小名")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            Member mem = documentSnapshot.toObject(Member.class);
//                            String name = mem.getName();
//                            String password = mem.getPassword();
//                            String phone = mem.getPhone();
//
//                            showusername.setText(name);
//                            showpassword.setText(password);
//                            showphone.setText(phone);
//                        }
//                    }
//                });
//    }
}
