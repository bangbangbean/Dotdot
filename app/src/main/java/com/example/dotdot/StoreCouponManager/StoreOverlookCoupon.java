package com.example.dotdot.StoreCouponManager;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotdot.Coupon;
import com.example.dotdot.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static android.content.Context.MODE_PRIVATE;

public class StoreOverlookCoupon extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference storeRef = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("coupon");
    private CouponAdapter adapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_store_overlook_coupon, container, false);
        setUpRecyclerView();

        Button creatCoupon = view.findViewById(R.id.creatBtn);
        creatCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StoreCreatCoupon.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void setUpRecyclerView() {

        Query query = storeRef.orderBy("deadLine", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Coupon> options = new FirestoreRecyclerOptions.Builder<Coupon>()
                .setQuery(query, Coupon.class)
                .build();
        adapter = new CouponAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);


        SharedPreferences couponpref = this.getActivity().getSharedPreferences("save_coupon", MODE_PRIVATE);




        adapter.setOnItemClickListener(new CouponAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Coupon coupon = documentSnapshot.toObject(Coupon.class);
                String title = coupon.getCouponTitle();
                couponpref.edit()
                    .putString("coupon_title", title)
                    .commit();
                Intent intent = new Intent(getActivity(), CouponContent.class);
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
