package com.example.dotdot.StoreCouponManager;

import android.graphics.Color;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CouponAdapter extends FirestoreRecyclerAdapter<Coupon, CouponAdapter.CouponHolder> {

    private OnItemClickListener listener;

    public CouponAdapter(@NonNull FirestoreRecyclerOptions<Coupon> options) {
            super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CouponHolder couponHolder, int i, @NonNull Coupon coupon) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        couponHolder.couponTitle.setText(coupon.getCouponTitle());
        couponHolder.couponPoint.setText(Integer.toString(coupon.getDotNeed()));
        couponHolder.startTime.setText(sdf.format(coupon.getCreatTime()));
        couponHolder.endTime.setText(sdf.format(coupon.getDeadLine()));
        String deadline = sdf.format(coupon.getDeadLine());
        Date dt = new Date();
        String nowdt = sdf.format(dt);
        if (compareDate(nowdt, deadline) == false) {
            couponHolder.listBackground.setBackgroundResource(R.drawable.bgg_cou2_used);
            //couponHolder.listBackground.setBackgroundColor(Color.rgb(211, 210, 210));
            couponHolder.couponPoint.setTextColor(Color.rgb(147,147,147));
        }
    }

    public boolean compareDate(String nowDate, String compareDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date now = df.parse(nowDate);
            Date compare = df.parse(compareDate);
            if (now.before(compare)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
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
            listBackground = itemView.findViewById(R.id.listBg);

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
