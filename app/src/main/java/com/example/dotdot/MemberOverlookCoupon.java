package com.example.dotdot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class MemberOverlookCoupon extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference storeRef = db.collection("store");
    private MemCouponAdapter adapter;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_member_overlook_coupon, container, false);
        setUpRecyclerView();

        //跳轉頁到MemberCanExchangeCoupon
        Button canExchangeBtn = (Button)view.findViewById(R.id.canExchangeBtn);
        canExchangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MemberCanExchangeCoupon llf = new MemberCanExchangeCoupon();
                ft.replace(R.id.fragment_container, llf);
                ft.commit();
            }
        });
        //跳轉頁到MemberOwnedCoupon
        Button ownedCoupon = (Button)view.findViewById(R.id.ownedCoupon);
        ownedCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MemberOwnedCoupon llf = new MemberOwnedCoupon();
                ft.replace(R.id.fragment_container, llf);
                ft.commit();
            }
        });
        return view;
    }

    private void setUpRecyclerView() {
        //store的亂碼Id
        String storeId = this.getActivity().getSharedPreferences("save_storeId", MODE_PRIVATE)
                .getString("store_id", "沒選擇店家");

        Date dt = new Date();
        Query query = storeRef.document(storeId).collection("coupon")
                .whereGreaterThan("deadLine",dt)
                .orderBy("deadLine", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Coupon> options = new FirestoreRecyclerOptions.Builder<Coupon>()
                .setQuery(query, Coupon.class)
                .build();
        adapter = new MemCouponAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);

        //coupon的title名稱
        SharedPreferences couponpref = this.getActivity().getSharedPreferences("save_coupon", MODE_PRIVATE);

        //coupon的Id名稱
        SharedPreferences couponIdpref = this.getActivity().getSharedPreferences("save_couponId", MODE_PRIVATE);


        adapter.setOnItemClickListener(new MemCouponAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Coupon coupon = documentSnapshot.toObject(Coupon.class);
                couponpref.edit()
                        .putString("coupon_title", coupon.getCouponTitle())
                        .commit();
                couponIdpref.edit()
                        .putString("coupon_id",documentSnapshot.getId())
                        .commit();
                Intent intent = new Intent(getActivity(), MemCouponContent.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}