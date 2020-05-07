package com.example.dotdot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

import static android.content.Context.MODE_PRIVATE;

public class FavoriteNotification_fragment extends Fragment {


    private View view;
    private RecyclerView FavRecycler;
    private Fav_noticeAdapter adapter;
    private CardView cardView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference MemRef = db.collection("Member");
    private CollectionReference FavRef = db.collection("Notice_store");


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
        Query query = FavRef.orderBy("time"  , Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Note_store_noit> options = new FirestoreRecyclerOptions.Builder<Note_store_noit>()
                .setQuery(query, Note_store_noit.class)
                .build();
        adapter = new Fav_noticeAdapter(options);
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
