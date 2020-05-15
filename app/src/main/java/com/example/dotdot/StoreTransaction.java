package com.example.dotdot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class StoreTransaction extends Fragment {

    private RecyclerView recyclerView;
    private Switch modeSwitch = null;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference storeref = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("giveDotRecord");
    private CollectionReference storeref2 = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("couponBeenExchange");

    private StorePointRecAdapter adapter;
    private StoreCouponRecordAdapter adapter2;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_store_transaction, container, false);

        setUpRecyclerView();
        setUpRecyclerView2();

        modeSwitch = view.findViewById(R.id.modeSwitch);
        recyclerView =view.findViewById(R.id.recycler_view);


        //利用Switch切換模式
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    //顯示清單模式紀錄
                    recyclerView.setAdapter(adapter);
                }
                else {
                    //顯示店家模式紀錄
                    recyclerView.setAdapter(adapter2);
                }
            }
        });

        return view;

    }


    private void setUpRecyclerView() {
        Query query = storeref;
        FirestoreRecyclerOptions<StorePointRec> options = new FirestoreRecyclerOptions.Builder<StorePointRec>()
                .setQuery(query, StorePointRec.class)
                .build();

        adapter = new StorePointRecAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUpRecyclerView2() {
        Query query = storeref2;
        FirestoreRecyclerOptions<StoreCouponRecord> options = new FirestoreRecyclerOptions.Builder<StoreCouponRecord>()
                .setQuery(query, StoreCouponRecord.class)
                .build();

        adapter2 = new StoreCouponRecordAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter2);


    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        adapter2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter2.stopListening();
    }


}

