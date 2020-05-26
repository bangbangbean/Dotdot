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
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class RecordAdapter extends FirestoreRecyclerAdapter<Record, RecordAdapter.RecordHolder> {

    public RecordAdapter(@NonNull FirestoreRecyclerOptions<Record> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecordHolder recordHolder, int i, @NonNull Record record) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH：mm");
        recordHolder.point_use.setText(record.getPoint_use());
        recordHolder.point_get.setText(record.getPoint_get());
        recordHolder.store_couponId.setText(record.getCouponTitle());
        recordHolder.yearmonth.setText(sdf.format(record.getTime()));
        //recordHolder.day.setText(sdf1.format(record.getTime()));
        recordHolder.time.setText(sdf2.format(record.getTime()));
        if(record.getCouponTitle() ==null){
            recordHolder.getpoint.setVisibility(View.VISIBLE);
        }else{
            recordHolder.getpoint.setVisibility(View.INVISIBLE);
        }



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference storeRef = db.collection("store");
        storeRef.document(record.getStoreId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Store store = documentSnapshot.toObject(Store.class);
                recordHolder.storeId.setText(store.getName());
            }
        });


    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_listmode_list,
                parent, false);
        return new RecordHolder(v);
    }

    class RecordHolder extends RecyclerView.ViewHolder {
        private TextView point_use;
        private TextView point_get;
        private TextView storeId;
        private TextView store_couponId;
        private TextView yearmonth;
        //private TextView day;
        private TextView time;
        private TextView getpoint;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            point_use = itemView.findViewById(R.id.situation);
            point_get = itemView.findViewById(R.id.situation2);
            storeId = itemView.findViewById(R.id.storeName);
            store_couponId = itemView.findViewById(R.id.obj);
            yearmonth = itemView.findViewById(R.id.yearmonth);
            //day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            getpoint = itemView.findViewById(R.id.obj2);
        }
    }
}
