package com.example.dotdot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.SimpleDateFormat;

public class CouponGetAdapter extends FirestoreRecyclerAdapter<CouponGet, CouponGetAdapter.coupongetHolder> {

    public CouponGetAdapter(@NonNull FirestoreRecyclerOptions<CouponGet> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull coupongetHolder coupongetHolder, int i, @NonNull CouponGet couponGet) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        coupongetHolder.store_couponId.setText(couponGet.getCouponTitle());
        coupongetHolder.yearmonth.setText(sdf.format(couponGet.getTime()));
        coupongetHolder.day.setText(sdf1.format(couponGet.getTime()));
        coupongetHolder.time.setText(sdf2.format(couponGet.getTime()));
    }

    @NonNull
    @Override
    public coupongetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_listmode_coupon,
                parent, false);
        return new coupongetHolder(v);
    }

    class coupongetHolder extends RecyclerView.ViewHolder {
        TextView yearmonth;
        TextView day;
        TextView time;
        TextView store_couponId;

        public coupongetHolder(@NonNull View itemView) {
            super(itemView);
            yearmonth = itemView.findViewById(R.id.yearmonth);
            day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            store_couponId = itemView.findViewById(R.id.couponName);
        }
    }
}
