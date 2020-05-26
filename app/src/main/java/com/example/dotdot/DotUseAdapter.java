package com.example.dotdot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class DotUseAdapter extends FirestoreRecyclerAdapter<DotUse, DotUseAdapter.DotUseHolder> {


    public DotUseAdapter(@NonNull FirestoreRecyclerOptions<DotUse> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DotUseHolder dotUseHolder, int i, @NonNull DotUse dotUse) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd");
        //SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        dotUseHolder.point_use.setText(dotUse.getPoint_use());
        dotUseHolder.store_couponId.setText(dotUse.getCouponTitle());
        dotUseHolder.yearmonth.setText(sdf.format(dotUse.getTime()));
        //dotUseHolder.day.setText(sdf1.format(dotUse.getTime()));
        dotUseHolder.time.setText(sdf2.format(dotUse.getTime()));
        dotUseHolder.storeId.setText("獲得優惠券");
    }

    @NonNull
    @Override
    public DotUseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_listmode_list,
                parent, false);
        return new DotUseHolder (v);
    }

    class DotUseHolder extends RecyclerView.ViewHolder {
        TextView point_use;
        TextView storeId;
        TextView store_couponId;
        TextView yearmonth;
        //TextView day;
        TextView time;

        public DotUseHolder(@NonNull View itemView) {
            super(itemView);
            point_use = itemView.findViewById(R.id.situation);
            storeId = itemView.findViewById(R.id.storeName);
            store_couponId = itemView.findViewById(R.id.obj);
            yearmonth = itemView.findViewById(R.id.yearmonth);
            //day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
        }
    }
}
