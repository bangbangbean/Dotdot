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

public class RecordAdapter extends FirestoreRecyclerAdapter<Record, RecordAdapter.RecordHolder> {

    public RecordAdapter(@NonNull FirestoreRecyclerOptions<Record> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecordHolder recordHolder, int i, @NonNull Record record) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
        recordHolder.textViewyearmonth.setText(sdf1.format(record.getTime()));
        recordHolder.textViewday.setText(sdf2.format(record.getTime()));
        recordHolder.textViewtime.setText(sdf3.format(record.getTime()));
        recordHolder.textViewStoreName.setText(record.getStore());
        recordHolder.textViewObject.setText(record.getObject());
        recordHolder.textViewSituation.setText(record.getPoint_get());
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_listmode_list,
                parent, false);
        return new RecordHolder(v);
    }

    class RecordHolder extends RecyclerView.ViewHolder {
        TextView textViewyearmonth;
        TextView textViewday;
        TextView textViewtime;
        TextView textViewStoreName;
        TextView textViewObject;
        TextView textViewSituation;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            textViewyearmonth = itemView.findViewById(R.id.yearmonth);
            textViewday = itemView.findViewById(R.id.day);
            textViewtime = itemView.findViewById(R.id.time);
            textViewStoreName = itemView.findViewById(R.id.storeName);
            textViewObject = itemView.findViewById(R.id.obj);
            textViewSituation = itemView.findViewById(R.id.situation);

        }
    }
}
