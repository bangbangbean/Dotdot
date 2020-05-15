package com.example.dotdot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.SimpleDateFormat;

public class StorePointRecAdapter extends FirestoreRecyclerAdapter<StorePointRec, StorePointRecAdapter.storepointrecHolder> {


    public StorePointRecAdapter(@NonNull FirestoreRecyclerOptions<StorePointRec> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull storepointrecHolder storepointrecHolder, int i, @NonNull StorePointRec storePointRec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        storepointrecHolder.point_given.setText(storePointRec.getPoint_given());
        storepointrecHolder.yearmonth.setText(sdf.format(storePointRec.getTime()));
        storepointrecHolder.day.setText(sdf1.format(storePointRec.getTime()));
        storepointrecHolder.time.setText(sdf2.format(storePointRec.getTime()));
    }

    @NonNull
    @Override
    public storepointrecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_listmode_dotrecord,
                parent, false);
        return new storepointrecHolder(v);
    }

    class storepointrecHolder extends RecyclerView.ViewHolder {
        TextView yearmonth;
        TextView day;
        TextView time;
        TextView point_given;

        public storepointrecHolder(@NonNull View itemView) {
            super(itemView);
            yearmonth = itemView.findViewById(R.id.yearmonth);
            day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            point_given = itemView.findViewById(R.id.point_given);
        }
    }
}
