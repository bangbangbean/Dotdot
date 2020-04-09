package com.example.dotdot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FavoriteNotification_fragment extends Fragment {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference noticeRef = db.collection("Notice");
    private NoticeAdapter adapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favoritenotification , container , false);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){

        Query query = noticeRef.orderBy("priority", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<notice_note> options = new FirestoreRecyclerOptions.Builder<notice_note>()
                .setQuery(query , notice_note.class)
                .build();
        adapter = new NoticeAdapter(options);

        RecyclerView recyclerview = getView().findViewById(R.id.fav_recycler);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(FavoriteNotification_fragment.this));
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
