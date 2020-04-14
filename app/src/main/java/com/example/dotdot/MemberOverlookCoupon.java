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
    private CollectionReference couponRef = db.collection("store");
    private MemCouponAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_overlook_coupon);
        setUpRecyclerView();
    }


    private void setUpRecyclerView() {


        //store的亂碼Id
         String storeId = getSharedPreferences("save_storeId", MODE_PRIVATE)
                .getString("store_id", "沒選擇店家");

        Date dt = new Date();
        Query query = couponRef.document(storeId).collection("coupon")
                .whereGreaterThan("deadLine",dt)
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
        SharedPreferences couponpref = getSharedPreferences("save_coupon", MODE_PRIVATE);

        //coupon的Id名稱
        SharedPreferences couponIdpref = getSharedPreferences("save_couponId", MODE_PRIVATE);


        adapter.setOnItemClickListener(new MemCouponAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Coupon coupon = documentSnapshot.toObject(Coupon.class);
                couponpref.edit()
                        .putString("coupon_title", coupon.getCouponTitle())
                        .apply();
                couponIdpref.edit()
                        .putString("coupon_id",documentSnapshot.getId())
                        .apply();
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
