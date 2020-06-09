package com.example.dotdot;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.share.internal.DeviceShareDialogFragment.TAG;

public class Loyalty_cardAdapter extends FirestoreRecyclerAdapter<Loyalty_card, Loyalty_cardAdapter.Loyalty_cardHolder> {
    private OnItemClickListener listener;

    public Loyalty_cardAdapter(@NonNull FirestoreRecyclerOptions<Loyalty_card> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Loyalty_cardHolder loyalty_cardholder, int position, @NonNull Loyalty_card loyalty_card) {
        loyalty_cardholder.textViewpoints_owned.setText(loyalty_card.getDotGet());
        loyalty_cardholder.textViewcoupon.setText(loyalty_card.getCouponCount());
        loyalty_cardholder.textViewpoints_use.setText(loyalty_card.getDotUse());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference storeRef = db.collection("store");
        storeRef.document(loyalty_card.getStore()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Store store = documentSnapshot.toObject(Store.class);
                loyalty_cardholder.textViewstore.setText(store.getName());
            }
        });


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

    public interface  OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(Loyalty_cardAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
