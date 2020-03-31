package com.example.dotdot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;

public class CouponAdapter extends FirestoreRecyclerAdapter<Coupon, CouponAdapter.CouponHolder> {

    private OnItemClickListener listener;

    public CouponAdapter(@NonNull FirestoreRecyclerOptions<Coupon> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CouponHolder couponHolder, int i, @NonNull Coupon coupon) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        couponHolder.couponTitle.setText(coupon.getCouponTitle());
        couponHolder.couponPoint.setText(coupon.getDotNeed());
        couponHolder.startTime.setText(sdf.format(coupon.getCreatTime()));
        couponHolder.endTime.setText(sdf.format(coupon.getDeadLine()));
    }

    @NonNull
    @Override
    public CouponHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_couponlist,
                parent, false);
        return new CouponHolder(v);
    }

    class CouponHolder extends RecyclerView.ViewHolder {
        private TextView couponTitle;
        private TextView couponPoint;
        private TextView startTime;
        private TextView endTime;
        private TextView listBackground;

        public CouponHolder(@NonNull View itemView) {
            super(itemView);
            couponTitle = itemView.findViewById(R.id.couponTitle);
            couponPoint = itemView.findViewById(R.id.couponPoint);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
