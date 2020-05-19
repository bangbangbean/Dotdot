package com.example.dotdot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class StoreCouponRecordAdapter extends FirestoreRecyclerAdapter<StoreCouponRecord, StoreCouponRecordAdapter.storecouponrecordHolder> {

    public StoreCouponRecordAdapter(@NonNull FirestoreRecyclerOptions<StoreCouponRecord> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull storecouponrecordHolder storecouponrecordHolder, int i, @NonNull StoreCouponRecord storeCouponRecord) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        storecouponrecordHolder.yearmonth.setText(sdf.format(storeCouponRecord.getTime()));
        storecouponrecordHolder.day.setText(sdf1.format(storeCouponRecord.getTime()));
        storecouponrecordHolder.time.setText(sdf2.format(storeCouponRecord.getTime()));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference couponTitle = db.collection("store");
        couponTitle.document("itNDANFxcx8moOGEg8JF").collection("coupon")
                .document(storeCouponRecord.getStore_couponId())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Coupon coupon = documentSnapshot.toObject(Coupon.class);
                storecouponrecordHolder.store_couponId.setText(coupon.getCouponTitle());
            }
        });
    }

    @NonNull
    @Override
    public storecouponrecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_listmode_coupon,
                parent, false);
        return new storecouponrecordHolder(v);
    }

    class storecouponrecordHolder extends RecyclerView.ViewHolder{
        TextView yearmonth;
        TextView day;
        TextView time;
        TextView store_couponId;

        public storecouponrecordHolder(@NonNull View itemView) {
            super(itemView);
            yearmonth = itemView.findViewById(R.id.yearmonth);
            day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            store_couponId = itemView.findViewById(R.id.couponName);
        }
    }
}
