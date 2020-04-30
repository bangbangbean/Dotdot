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

public class MemberOwnedCouponAdapter extends FirestoreRecyclerAdapter<Coupon, MemberOwnedCouponAdapter.MemberOwnedCouponHolder> {
    private MemberOwnedCouponAdapter.OnItemClickListener listener;

    public MemberOwnedCouponAdapter(@NonNull FirestoreRecyclerOptions<Coupon> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MemberOwnedCouponHolder memberOwnedCouponHolder, int i, @NonNull Coupon coupon) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        memberOwnedCouponHolder.couponTitle.setText(coupon.getCouponTitle());
        memberOwnedCouponHolder.couponPoint.setText(Integer.toString(coupon.getDotNeed()));
        memberOwnedCouponHolder.Time.setText(sdf.format(coupon.getTime()));
    }

    @NonNull
    @Override
    public MemberOwnedCouponHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_owned_couponlist,
                parent, false);
        return new MemberOwnedCouponAdapter.MemberOwnedCouponHolder(v);
    }

    public class MemberOwnedCouponHolder extends RecyclerView.ViewHolder {
        private TextView couponTitle;
        private TextView couponPoint;
        private TextView Time;

        public MemberOwnedCouponHolder(@NonNull View itemView) {
            super(itemView);
            couponTitle = itemView.findViewById(R.id.couponTitle);
            couponPoint = itemView.findViewById(R.id.couponPoint);
            Time = itemView.findViewById(R.id.time);

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

    public void setOnItemClickListener(MemberOwnedCouponAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

}