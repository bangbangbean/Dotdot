package com.example.dotdot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class StoreProfile extends Fragment {
    private TextView sname;
    private TextView saddress;
    private TextView stelephone;
    private View view;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference stoRef = db.collection("store");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store_profile_fragment, container, false);

        sname = (TextView)view.findViewById(R.id.sname);
        saddress = (TextView)view.findViewById(R.id.address);
        stelephone = (TextView)view.findViewById(R.id.telephone);

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
