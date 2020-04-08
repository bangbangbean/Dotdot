package com.example.dotdot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Loyalty_cardAdapter extends FirestoreRecyclerAdapter<Loyalty_card, Loyalty_cardAdapter.Loyalty_cardHolder> {

    public Loyalty_cardAdapter(@NonNull FirestoreRecyclerOptions<Loyalty_card> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Loyalty_cardHolder loyalty_cardholder, int position, @NonNull Loyalty_card loyalty_card) {
        loyalty_cardholder.textViewstore.setText(loyalty_card.getStore());
        loyalty_cardholder.textViewpoints_owned.setText(loyalty_card.getPoints_owned());
        loyalty_cardholder.textViewpoints_use.setText(loyalty_card.getPoints_use());
        loyalty_cardholder.textViewcoupon.setText(loyalty_card.getCoupon());
    }

    @NonNull
    @Override
    public Loyalty_cardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_storemode_list,
                parent, false);
        return new Loyalty_cardHolder(v);
    }

    class Loyalty_cardHolder extends RecyclerView.ViewHolder {
        TextView textViewpoints_owned;
        TextView textViewpoints_use;
        TextView textViewstore;
        TextView textViewcoupon;


        public Loyalty_cardHolder(@NonNull View itemView) {
            super(itemView);
            textViewpoints_owned = itemView.findViewById(R.id.getDot);
            textViewpoints_use = itemView.findViewById(R.id.useDot);
            textViewstore = itemView.findViewById(R.id.memberRecStoreName);
            textViewcoupon = itemView.findViewById(R.id.getCoupon);

        }
    }
}
