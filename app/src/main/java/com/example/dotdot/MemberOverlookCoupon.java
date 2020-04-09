package com.example.dotdot;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;

public class MemberOverlookCoupon extends Activity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference couponRef = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("coupon");
    private MemCouponAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_overlook_coupon);
        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        Date dt = new Date();
        Query query = couponRef.whereGreaterThan("deadLine",dt)
                .orderBy("deadLine", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Coupon> options = new FirestoreRecyclerOptions.Builder<Coupon>()
                .setQuery(query, Coupon.class)
                .build();
        adapter = new MemCouponAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //coupon的title名稱
        SharedPreferences coupon = getSharedPreferences("save_coupon", MODE_PRIVATE);
        final SharedPreferences.Editor editor = coupon.edit();

        //coupon的亂碼Id
        SharedPreferences couponId = getSharedPreferences("save_coupon", MODE_PRIVATE);
        final SharedPreferences.Editor editorId = couponId.edit();

        //store的亂碼Id
        SharedPreferences store = getSharedPreferences("save_coupon", MODE_PRIVATE);
        final SharedPreferences.Editor storeEditor = store.edit();

        adapter.setOnItemClickListener(new MemCouponAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Coupon coupon = documentSnapshot.toObject(Coupon.class);
                editor.putString("user_id", coupon.getCouponTitle());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MemCouponContent.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
