package com.example.dotdot;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.Contract;

import java.text.SimpleDateFormat;

import static com.facebook.FacebookSdk.getApplicationContext;

public class StoreNotificationSend extends Fragment {

    private View PrivateView;
    private RecyclerView notiList;
    private StoreNotiSend_Adapter adapter;
    private Button btn;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notiRef = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("Notice");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PrivateView = inflater.inflate(R.layout.store_notification_send , container , false);
        notiList = (RecyclerView) PrivateView.findViewById(R.id.StoreNotiRecyclerView);
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
        btn = (Button)PrivateView.findViewById(R.id.newbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h = new Intent(getApplicationContext(), StoreNotiSending.class);
                startActivity(h);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
