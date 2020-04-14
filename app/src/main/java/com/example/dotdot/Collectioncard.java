package com.example.dotdot;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Collectioncard extends AppCompatActivity {
    private CollectioncardAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //記得改成活的
    private CollectionReference note = db.collection("Member")
            .document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collectioncard);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        Query query = note;
        FirestoreRecyclerOptions<Loyalty_card> options = new FirestoreRecyclerOptions.Builder<Loyalty_card>()
                .setQuery(query, Loyalty_card.class)
                .build();

        adapter = new CollectioncardAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //store的亂碼Id
        SharedPreferences storepref = getSharedPreferences("save_storeId", MODE_PRIVATE);

        adapter.setOnItemClickListener(new CollectioncardAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Loyalty_card loyalty_card = documentSnapshot.toObject(Loyalty_card.class);
                storepref.edit()
                        .putString("store_id", loyalty_card.getStore())
                        .apply();
                Intent intent = new Intent(getApplicationContext(), MemberOverlookCoupon.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

