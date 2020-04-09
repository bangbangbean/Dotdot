package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TransactionRecord extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Switch modeSwitch = null;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memref = db.collection("Member").document(
            "iICTR1JL4eAG4B3QBi1S").collection("record");

    private CollectionReference memref2 = db.collection("Member").document(
            "iICTR1JL4eAG4B3QBi1S").collection("loyalty_card");

    private RecordAdapter adapter;
    private Loyalty_cardAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_record);

        setUpRecyclerView();
        setUpRecyclerView2();

        modeSwitch = (Switch)findViewById(R.id.modeSwitch);

        recyclerView =(RecyclerView) findViewById(R.id.recycler_view);

        //利用Switch切換模式
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    //顯示店家模式紀錄
                    recyclerView.setAdapter(adapter);
                    //點擊觸發事件_跳出選定店家紀錄

                }
                else {
                    //顯示清單模式紀錄
                    recyclerView.setAdapter(adapter2);
                }
            }
        });

    }




    private void setUpRecyclerView() {
        Query query = memref;
        FirestoreRecyclerOptions<Record> options = new FirestoreRecyclerOptions.Builder<Record>()
                .setQuery(query, Record.class)
                .build();

        adapter = new RecordAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setUpRecyclerView2() {
        Query query = memref2;
        FirestoreRecyclerOptions<Loyalty_card> options = new FirestoreRecyclerOptions.Builder<Loyalty_card>()
                .setQuery(query, Loyalty_card.class)
                .build();

        adapter2 = new Loyalty_cardAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter2);

        SharedPreferences loyalty_card = getSharedPreferences("loyalty_card", MODE_PRIVATE);
        final SharedPreferences.Editor editor = loyalty_card.edit();

        adapter2.setOnItemClickListener(new Loyalty_cardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Loyalty_card loyalty_card = documentSnapshot.toObject(Loyalty_card.class);
                String store = loyalty_card.getStore();
                editor.putString("user_id", store);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), member_storemode_record.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter2.stopListening();
    }


}
