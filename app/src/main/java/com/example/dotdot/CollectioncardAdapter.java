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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class CollectioncardAdapter extends FirestoreRecyclerAdapter<Loyalty_card, CollectioncardAdapter.CollectioncardHolder> {

    private OnItemClickListener listener;

    public CollectioncardAdapter(@NonNull FirestoreRecyclerOptions<Loyalty_card> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CollectioncardHolder collectioncardHolder, int i, @NonNull Loyalty_card loyalty_card) {
        collectioncardHolder.points.setText(loyalty_card.getPoints_owned());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference storeRef = db.collection("store");
        storeRef.document(loyalty_card.getStore()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Store store = documentSnapshot.toObject(Store.class);
                collectioncardHolder.Title.setText(store.getName());
                System.out.println(store.getName());

            }
        });

        int qqq = Integer.parseInt(loyalty_card.getPoints_owned());
        String color = loyalty_card.getColor();
        if (color.equals("red")) {

            if (qqq == 1) {

                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);

            } else if (qqq == 2) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
            } else if (qqq == 3) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.red_dot);

            } else if (qqq == 4) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.red_dot);

            } else if (qqq == 5) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.red_dot);

            } else if (qqq == 6) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.red_dot);

            } else if (qqq == 7) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.red_dot);
            } else if (qqq == 8) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.red_dot);
            } else if (qqq == 9) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot9.setBackgroundResource(R.drawable.red_dot);
            } else if (qqq >= 10) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot9.setBackgroundResource(R.drawable.red_dot);
                collectioncardHolder.dot10.setBackgroundResource(R.drawable.red_dot);
            }
        }
        if (color.equals("green")) {

            if (qqq == 1) {

                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);

            } else if (qqq == 2) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
            } else if (qqq == 3) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.green_dot);

            } else if (qqq == 4) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.green_dot);

            } else if (qqq == 5) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.green_dot);

            } else if (qqq == 6) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.green_dot);

            } else if (qqq == 7) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.green_dot);
            } else if (qqq == 8) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.green_dot);
            } else if (qqq == 9) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot9.setBackgroundResource(R.drawable.green_dot);
            } else if (qqq >= 10) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot9.setBackgroundResource(R.drawable.green_dot);
                collectioncardHolder.dot10.setBackgroundResource(R.drawable.green_dot);
            }
        }
        if (color.equals("yellow")) {

            if (qqq == 1) {

                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);

            } else if (qqq == 2) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
            } else if (qqq == 3) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.yellow_dot);

            } else if (qqq == 4) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.yellow_dot);

            } else if (qqq == 5) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.yellow_dot);

            } else if (qqq == 6) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.yellow_dot);

            } else if (qqq == 7) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.yellow_dot);
            } else if (qqq == 8) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.yellow_dot);
            } else if (qqq == 9) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot9.setBackgroundResource(R.drawable.yellow_dot);
            } else if (qqq >= 10) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot9.setBackgroundResource(R.drawable.yellow_dot);
                collectioncardHolder.dot10.setBackgroundResource(R.drawable.yellow_dot);
            }
        }
        if (color.equals("blue")) {

            if (qqq == 1) {

                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);

            } else if (qqq == 2) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
            } else if (qqq == 3) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.blue_dot);

            } else if (qqq == 4) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.blue_dot);

            } else if (qqq == 5) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.blue_dot);

            } else if (qqq == 6) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.blue_dot);

            } else if (qqq == 7) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.blue_dot);
            } else if (qqq == 8) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.blue_dot);
            } else if (qqq == 9) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot9.setBackgroundResource(R.drawable.blue_dot);
            } else if (qqq >= 10) {
                collectioncardHolder.dot1.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot2.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot3.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot4.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot5.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot6.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot7.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot8.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot9.setBackgroundResource(R.drawable.blue_dot);
                collectioncardHolder.dot10.setBackgroundResource(R.drawable.blue_dot);
            }
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

        return new CollectioncardHolder(v);
    }

    public void myLoveItem(int position) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference memRef = db.collection("Member");

        String id = getSnapshots().getSnapshot(position).getId();
        memRef.document("iICTR1JL4eAG4B3QBi1S")//須改活
                .collection("loyalty_card")
                .document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        LoyaltyCard loyaltyCard = documentSnapshot.toObject(LoyaltyCard.class);
                        Boolean f = loyaltyCard.getFavorite();
                        if (f.equals(false)) {
                            //False修改為True
                            Map<Object, Object> upData = new HashMap<>();
                            upData.put("favorite", true);
                            memRef.document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card")
                                    .document(id).set(upData, SetOptions.merge());
                        } else {
                            //True修改為False
                            Map<Object, Object> Data = new HashMap<>();
                            Data.put("favorite", false);
                            memRef.document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card")
                                    .document(id).set(Data, SetOptions.merge());
                        }
                    }
                });
    }

    class CollectioncardHolder extends RecyclerView.ViewHolder {
        private TextView Title;
        private TextView points;
        private TextView dot1;
        private TextView dot2;
        private TextView dot3;
        private TextView dot4;
        private TextView dot5;
        private TextView dot6;
        private TextView dot7;
        private TextView dot8;
        private TextView dot9;
        private TextView dot10;
        private Button coupon;


        public CollectioncardHolder(@NonNull View itemView) {
            super(itemView);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
