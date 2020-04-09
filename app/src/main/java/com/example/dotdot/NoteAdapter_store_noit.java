package com.example.dotdot;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.SimpleDateFormat;

public class NoteAdapter_store_noit extends FirestoreRecyclerAdapter<Note_store_noit, NoteAdapter_store_noit.NoteHolder> {

    public NoteAdapter_store_noit(@NonNull FirestoreRecyclerOptions<Note_store_noit> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder noteHolder, int i, @NonNull Note_store_noit note_store_noit) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date轉string

        noteHolder.notiTitle.setText(note_store_noit.getTitle());
        noteHolder.notiTime.setText(sdf.format(note_store_noit.getTime()));
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_notification_item,
                parent, false);
        return new NoteHolder(v);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        TextView notiTitle;
        TextView notiTime;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            notiTitle = itemView.findViewById(R.id.notiTitle);
            notiTime = itemView.findViewById(R.id.notiTime);
        }
    }
}

