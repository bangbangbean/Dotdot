package com.example.dotdot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CollectioncardAdapter extends FirestoreRecyclerAdapter<Loyalty_card, CollectioncardAdapter.CollectioncardHolder> {


    public CollectioncardAdapter(@NonNull FirestoreRecyclerOptions<Loyalty_card> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CollectioncardHolder collectioncardHolder, int i, @NonNull Loyalty_card loyalty_card) {

        collectioncardHolder.points.setText(loyalty_card.getPoints_owned());
        String qq = loyalty_card.getPoints_owned();
        int qqq = Integer.valueOf(qq);
        if (qqq == 4){
            collectioncardHolder.dot1.setBackgroundColor(Color.rgb(239,114,158));


        }
        collectioncardHolder.coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



    }



    @NonNull
    @Override
    public CollectioncardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collectionlist,
                parent, false);

        return new CollectioncardHolder (v);
    }

    class CollectioncardHolder extends RecyclerView.ViewHolder {

        TextView Title;
        TextView points;
        private TextView dot1;
        TextView dot2;
        TextView dot3;
        TextView dot4;
        TextView dot5;
        TextView dot6;
        TextView dot7;
        TextView dot8;
        TextView dot9;
        TextView dot10;
        private Button coupon;

        public CollectioncardHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            Title = itemView.findViewById(R.id.Title);
            points = itemView.findViewById(R.id.points);
            dot1 = itemView.findViewById(R.id.Dot1);
            dot2 = itemView.findViewById(R.id.Dot2);
            dot3 = itemView.findViewById(R.id.Dot3);
            dot4 = itemView.findViewById(R.id.Dot4);
            dot5 = itemView.findViewById(R.id.Dot5);
            dot6 = itemView.findViewById(R.id.Dot6);
            dot7 = itemView.findViewById(R.id.Dot7);
            dot8 = itemView.findViewById(R.id.Dot8);
            dot9 = itemView.findViewById(R.id.Dot9);
            dot10 = itemView.findViewById(R.id.Dot10);
            coupon = itemView.findViewById(R.id.coupon);
        }


    }

}
