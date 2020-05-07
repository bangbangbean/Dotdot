package com.example.dotdot;

import android.content.ClipData;
import android.content.ContentProvider;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.dotdot.recycleritemanim.ExpandableViewHoldersUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.sql.Ref;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

public class Fav_noticeAdapter extends FirestoreRecyclerAdapter<Note_store_noit ,Fav_noticeAdapter.viewHolder> {


    private ExpandableViewHoldersUtil.KeepOneHolder<viewHolder> keepOne;
    public Fav_noticeAdapter(@NonNull FirestoreRecyclerOptions<Note_store_noit> options  ){
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull Fav_noticeAdapter.viewHolder viewHolder, int i, @NonNull Note_store_noit note_store_noit) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference FavRef = db.collection("Notice_store");
        CollectionReference MemRef = db.collection("Member");

        String x = note_store_noit.getStore();


        /*MemRef.document("iICTR1JL4eAG4B3QBi1S")
                .collection("loyalty_card")
                .whereEqualTo("store" , note_store_noit.getStore())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        FavRef.get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot documentSnapshots) {
                                        LoyaltyCard loyaltyCard = (LoyaltyCard) documentSnapshots.toObjects(LoyaltyCard.class);
                                        if(loyaltyCard.getStore() == note_store_noit.getStore()) {
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date轉string
                                            viewHolder.fav_title.setText(note_store_noit.getTitle());
                                            viewHolder.fav_time.setText(sdf.format(note_store_noit.getTime()));
                                            viewHolder.fav_contxt.setText(note_store_noit.getContxt());
                                            CollectionReference storeRef = db.collection("store");
                                            storeRef.document(note_store_noit.getStore()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    Store store = documentSnapshot.toObject(Store.class);
                                                    viewHolder.fav_storename.setText(store.getName());
                                                }
                                            });
                                        }
                                    }
                                });

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            LoyaltyCard loyaltycard = documentSnapshot.toObject(LoyaltyCard.class);
                            if (loyaltycard.getFavorite() == true) {
                                FavRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                            Note_store_noit note_store_noit1 = documentSnapshot.toObject(Note_store_noit.class);
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date轉string
                                            viewHolder.fav_title.setText(note_store_noit.getTitle());
                                            viewHolder.fav_time.setText(sdf.format(note_store_noit.getTime()));
                                            viewHolder.fav_contxt.setText(note_store_noit.getContxt());
                                            CollectionReference storeRef = db.collection("store");
                                            storeRef.document(note_store_noit.getStore()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    Store store = documentSnapshot.toObject(Store.class);
                                                    viewHolder.fav_storename.setText(store.getName());
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    }
                });*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date轉string
        viewHolder.fav_title.setText(note_store_noit.getTitle());
        viewHolder.fav_time.setText(sdf.format(note_store_noit.getTime()));
        viewHolder.fav_contxt.setText(note_store_noit.getContxt());
        CollectionReference storeRef = db.collection("store");
        storeRef.document(note_store_noit.getStore()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Store store = documentSnapshot.toObject(Store.class);
                viewHolder.fav_storename.setText(store.getName());
            }
        });

        keepOne.bind(viewHolder , i);
        viewHolder.fav_title.setOnClickListener((view -> {
            keepOne.toggle(viewHolder);
        }));



    }



    @NonNull
    @Override
    public Fav_noticeAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_notice_item,
                parent, false);

        return new Fav_noticeAdapter.viewHolder(v);
    }

    public class viewHolder extends ViewHolder implements ExpandableViewHoldersUtil.Expandable{
        private TextView fav_storename;
        private TextView fav_title;
        private TextView fav_time;
        private TextView fav_contxt;
        RelativeLayout relativeLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            fav_storename = itemView.findViewById(R.id.fav_storename);
            fav_title = itemView.findViewById(R.id.fav_title);
            fav_time = itemView.findViewById(R.id.fav_time);
            fav_contxt = itemView.findViewById(R.id.fav_contxt);
            relativeLayout = itemView.findViewById(R.id.childCard);

            keepOne = ExpandableViewHoldersUtil.getInstance().getKeepOneHolder();

            relativeLayout.setVisibility(View.GONE);
            relativeLayout.setAlpha(0);
        }

        @Override
        public View getExpandView() {
            return relativeLayout;
        }

        @Override
        public void doCustomAnim(boolean isOpen) {

        }
    }
}