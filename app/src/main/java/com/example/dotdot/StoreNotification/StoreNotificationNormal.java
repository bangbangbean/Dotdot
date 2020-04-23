package com.example.dotdot.StoreNotification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotdot.Note_store_noit;
import com.example.dotdot.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class StoreNotificationNormal extends Fragment {

    private View PrivateView;
    private RecyclerView notiList;

    private StoreNotiSend_Adapter adapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notiRef = db.collection("Notice");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PrivateView = inflater.inflate(R.layout.store_notification_normal , container , false);
        notiList = (RecyclerView) PrivateView.findViewById(R.id.StoreNotiView);
        setUpRecyclerView();

        return PrivateView;
    }

    public void setUpRecyclerView(){

        FirestoreRecyclerOptions<Note_store_noit> options = new FirestoreRecyclerOptions.Builder<Note_store_noit>()
                .setQuery(notiRef, Note_store_noit.class)
                .build();

        adapter = new StoreNotiSend_Adapter(options);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        notiList.setLayoutManager(linearLayoutManager);
        notiList.setHasFixedSize(true);
        notiList.setAdapter(adapter);
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
