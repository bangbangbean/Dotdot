package com.example.dotdot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static android.content.Context.MODE_PRIVATE;

public class TransactionRecord extends Fragment {
    /*String memberId =getActivity().getSharedPreferences("save_memberId", MODE_PRIVATE)
            .getString("user_id", "沒會員登入");*/
    private RecyclerView recyclerView;
    private Switch modeSwitch = null;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memref = db.collection("Member");
    /*.document(memberId)
            .collection("loyalty_card");*/

    private RecordAdapter adapter;
    private Loyalty_cardAdapter adapter2;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_transaction_record, container, false);

        setUpRecyclerView();
        setUpRecyclerView2();

        modeSwitch = view.findViewById(R.id.modeSwitch);
        recyclerView = view.findViewById(R.id.recycler_view);

        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    recyclerView.setAdapter(adapter);
                }else{
                    recyclerView.setAdapter(adapter2);
                }
            }
        });


        return view;
    }




    private void setUpRecyclerView() {

        String memberId = getActivity().getSharedPreferences("save_memberId", MODE_PRIVATE)
                .getString("user_id", "沒會員登入");
        String loyalty_card_id = getActivity().getSharedPreferences("save_loyalty_card_id", MODE_PRIVATE)
                .getString("loyalty_card_id", "沒選擇店家");


        Query query = memref.document(memberId)
                .collection("loyalty_card")
                .document(loyalty_card_id)    //BUG
                .collection("Record");

        FirestoreRecyclerOptions<Record> options = new FirestoreRecyclerOptions.Builder<Record>()
                .setQuery(query, Record.class)
                .build();

        adapter = new RecordAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUpRecyclerView2() {
        String memberId =getActivity().getSharedPreferences("save_memberId", MODE_PRIVATE)
                .getString("user_id", "沒會員登入");

        Query query = memref.document(memberId)
                .collection("loyalty_card");
        FirestoreRecyclerOptions<Loyalty_card> options = new FirestoreRecyclerOptions.Builder<Loyalty_card>()
                .setQuery(query, Loyalty_card.class)
                .build();

        adapter2 = new Loyalty_cardAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter2);

        SharedPreferences loyalty_card = this.getActivity().getSharedPreferences("loyalty_card", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = loyalty_card.edit();

        adapter2.setOnItemClickListener(new Loyalty_cardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Loyalty_card loyalty_card = documentSnapshot.toObject(Loyalty_card.class);
                String store = loyalty_card.getStore();
                editor.putString("user_id", store);
                editor.commit();
                Intent intent = new Intent(getActivity(), member_storemode_record.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        adapter2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter2.stopListening();
    }


}
