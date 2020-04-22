package com.example.dotdot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotdot.recycleritemanim.ExpandableViewHoldersUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FavoriteNotification_fragment extends Fragment {


    private View view;
    private RecyclerView FavRecycler;
    private NoticeAdapter adapter;
    private CardView cardView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference FavRef = db.collection("Notice_store");
    private FirebaseFirestore db2 = FirebaseFirestore.getInstance();
    private CollectionReference storeRef = db2.collection("store");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favoritenotification , container , false);

        FavRecycler = (RecyclerView) view.findViewById(R.id.fav_recycler);
        setUpRecyclerView();

        FavRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExpandableViewHoldersUtil.getInstance().init().setNeedExplanedOnlyOne(false);
        ExpandableViewHoldersUtil.getInstance().resetExpanedList();
        return view;
    }

    public void setUpRecyclerView() {

        FirestoreRecyclerOptions<Note_store_noit> options = new FirestoreRecyclerOptions.Builder<Note_store_noit>()
                .setQuery(FavRef, Note_store_noit.class)
                .build();
        adapter = new NoticeAdapter(options);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        FavRecycler.setLayoutManager(linearLayoutManager);
        FavRecycler.setHasFixedSize(true);
        FavRecycler.setAdapter(adapter);

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
