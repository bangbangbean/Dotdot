package com.example.dotdot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class StoreNotificationSend extends Fragment {

    private View PrivateView;
    private RecyclerView notiList;

    private StoreNotiSend_Adapter adapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notiRef = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("Notice");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PrivateView = inflater.inflate(R.layout.store_notification_send , container , false);
        notiList = (RecyclerView) PrivateView.findViewById(R.id.StoreNotiRecyclerView);
        //notiList.setLayoutManager(new LinearLayoutManager(getContext()));
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

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        FirestoreRecyclerOptions<Note_store_noit> options =
//                new FirestoreRecyclerOptions.Builder<Note_store_noit>()
//                .setQuery(notiRef, Note_store_noit.class)
//                .build();
//
//        FirestoreRecyclerAdapter<Note_store_noit, viewHolder> adapter =
//                new FirestoreRecyclerAdapter<Note_store_noit, viewHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull viewHolder viewHolder, int i, @NonNull Note_store_noit note_store_noit) {
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date轉string
//                        viewHolder.notiTitle.setText(note_store_noit.getNotiTitle());
//                        viewHolder.notiTime.setText(sdf.format(note_store_noit.getNotiTime()));
//                    }
//
//                    @NonNull
//                    @Override
//                    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_notification_item,
//                                parent, false);
//                        return new viewHolder(v);
//                    }
//                };
//        //notiList.setHasFixedSize(true);
//        notiList.setAdapter(adapter);
//        adapter.startListening();
//    }


//    public static class viewHolder extends RecyclerView.ViewHolder{
//
//        private TextView notiTitle;
//        private TextView notiTime;
//
//        public viewHolder(@NonNull View itemView) {
//            super(itemView);
//            notiTitle = itemView.findViewById(R.id.notiTitle);
//            notiTime = itemView.findViewById(R.id.notiTime);
//        }
//    }
}
