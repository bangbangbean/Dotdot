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

public class StoreNotiSend_Adapter extends FirestoreRecyclerAdapter<Note_store_noit, StoreNotiSend_Adapter.viewHolder> {
    public StoreNotiSend_Adapter(@NonNull FirestoreRecyclerOptions<Note_store_noit> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StoreNotiSend_Adapter.viewHolder viewHolder, int i, @NonNull Note_store_noit note_store_noit) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date轉string
        viewHolder.notiTitle.setText(note_store_noit.getTitle());
        viewHolder.notiTime.setText(sdf.format(note_store_noit.getTime()));
    }

    @NonNull
    @Override
    public StoreNotiSend_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_notification_item,
                                parent, false);
                        return new viewHolder(v);
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView notiTitle;
        private TextView notiTime;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            notiTitle = itemView.findViewById(R.id.notiTitle);
            notiTime = itemView.findViewById(R.id.notiTime);
        }
    }
}
