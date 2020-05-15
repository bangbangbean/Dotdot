package com.example.dotdot.MemberCouponManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotdot.Coupon;
import com.example.dotdot.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;

//OK
public class MemCouponAdapter extends FirestoreRecyclerAdapter<Coupon, MemCouponAdapter.MemCouponHolder> {
    private MemCouponAdapter.OnItemClickListener listener;

    public MemCouponAdapter(@NonNull FirestoreRecyclerOptions<Coupon> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MemCouponHolder memCouponHolder, int i, @NonNull Coupon coupon) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        memCouponHolder.couponTitle.setText(coupon.getCouponTitle());
        memCouponHolder.couponPoint.setText(Integer.toString(coupon.getDotNeed()));
        memCouponHolder.endTime.setText(sdf.format(coupon.getDeadLine()));
    }

    @NonNull
    @Override
    public MemCouponHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_couponlist,
                parent, false);
        return new MemCouponAdapter.MemCouponHolder(v);
    }

    public class MemCouponHolder extends RecyclerView.ViewHolder {
        private TextView couponTitle;
        private TextView couponPoint;
        private TextView endTime;

        public MemCouponHolder(@NonNull View itemView) {
            super(itemView);
            couponTitle = itemView.findViewById(R.id.couponTitle);
            couponPoint = itemView.findViewById(R.id.couponPoint);
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

    public void setOnItemClickListener(MemCouponAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}