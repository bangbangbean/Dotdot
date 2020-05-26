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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class DotGetAdapter extends FirestoreRecyclerAdapter<DotGet, DotGetAdapter.DotGetHolder> {

    public DotGetAdapter(@NonNull FirestoreRecyclerOptions<DotGet> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DotGetHolder dotGetHolder, int i, @NonNull DotGet dotGet) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd");
        //SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        dotGetHolder.point_get.setText(dotGet.getPoint_get());
        dotGetHolder.yearmonth.setText(sdf.format(dotGet.getTime()));
        //dotGetHolder.day.setText(sdf1.format(dotGet.getTime()));
        dotGetHolder.time.setText(sdf2.format(dotGet.getTime()));
        dotGetHolder.getpoint.setVisibility(View.VISIBLE);




        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference recordRef = db.collection("Record");
        recordRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

            }
        });
        CollectionReference storeRef = db.collection("store");
        storeRef.document(dotGet.getStoreId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Store store = documentSnapshot.toObject(Store.class);
                dotGetHolder.storeId.setText(store.getName());
            }
        });


    }

    @NonNull
    @Override
    public DotGetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_listmode_list,
                parent, false);
        return new DotGetHolder(v);
    }

    class DotGetHolder extends RecyclerView.ViewHolder {
        TextView point_get;
        TextView storeId;
        TextView yearmonth;
        //TextView day;
        TextView time;
        TextView getpoint;

        public DotGetHolder(@NonNull View itemView) {
            super(itemView);
            point_get = itemView.findViewById(R.id.situation2);
            storeId = itemView.findViewById(R.id.storeName);
            yearmonth = itemView.findViewById(R.id.yearmonth);
            //day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            getpoint = itemView.findViewById(R.id.obj2);
        }
    }
}
