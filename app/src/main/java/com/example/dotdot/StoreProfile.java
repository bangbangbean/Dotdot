package com.example.dotdot;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class StoreProfile extends Fragment {
    private TextView sname;
    private TextView saddress;
    private TextView stelephone;
    private Button editbtn;
    private View view;
    private Integer a = 1;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference stoRef = db.collection("store");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store_profile_fragment, container, false);

        sname = (TextView)view.findViewById(R.id.sname);
        saddress = (TextView)view.findViewById(R.id.address);
        stelephone = (TextView)view.findViewById(R.id.telephone);
        editbtn = (Button)view.findViewById(R.id.editbtn);

        saddress.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //文本显示的位置在EditText的最上方  
        saddress.setGravity(Gravity.TOP);
        //改变默认的单行模式  
        saddress.setSingleLine(false);
        //水平滚动设置为False  
        saddress.setHorizontallyScrolling(false);

        //鎖定編輯
        sname.setFocusable(false);
        sname.setFocusableInTouchMode(false);
        saddress.setFocusable(false);
        saddress.setFocusableInTouchMode(false);
        stelephone.setFocusable(false);
        stelephone.setFocusableInTouchMode(false);

        //控制editText可否編輯
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a == 1){
                    sname.setFocusableInTouchMode(true);
                    sname.setFocusable(true);
                    sname.requestFocus();

                    saddress.setFocusableInTouchMode(true);
                    saddress.setFocusable(true);
                    saddress.requestFocus();

                    stelephone.setFocusableInTouchMode(true);
                    stelephone.setFocusable(true);
                    stelephone.requestFocus();
                    a = 0;
                }
                else {
                    sname.setFocusable(false);
                    sname.setFocusableInTouchMode(false);
                    saddress.setFocusable(false);
                    saddress.setFocusableInTouchMode(false);
                    stelephone.setFocusable(false);
                    stelephone.setFocusableInTouchMode(false);
                    a = 1;
                }
                String name = sname.getText().toString();
                String address = saddress.getText().toString();
                String tele = stelephone.getText().toString();

                Map<Object, Object> upData = new HashMap<>();
                upData.put("name", name);
                upData.put("address", address);
                upData.put("phone", tele);
                String storeID =getActivity().getSharedPreferences("save_storeId", MODE_PRIVATE)
                        .getString("user_id", "沒會員登入");

                stoRef.document(storeID).set(upData, SetOptions.merge());
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stoRef.whereEqualTo("name", "椒麻雞大王")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            Store store = documentSnapshot.toObject(Store.class);
                            String name = store.getName();
                            String address = store.getAddress();
                            String telephone = store.getPhone();

                            sname.setText(name);
                            saddress.setText(address);
                            stelephone.setText(telephone);
                        }
                    }
                });
    }
}
