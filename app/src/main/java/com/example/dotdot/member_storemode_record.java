package com.example.dotdot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.facebook.share.internal.DeviceShareDialogFragment.TAG;

public class member_storemode_record extends Activity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memref = db.collection("Member")
            .document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card");

    private CollectionReference storeref = db.collection("store");
    private CollectionReference couponref = db.collection("Member")
            .document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card")
            .document("BxskPfoZCfztCUSuDUOu").collection("Owned_Coupon");

    private Button button1;
    private Button button2;
    private Button button3;
    private TextView storename;
    private DotUseAdapter adapter1;
    private DotGetAdapter adapter2;
    private CouponGetAdapter adapter3;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_storemode_record);

        button1 = (Button) findViewById(R.id.useDot);
        button2 = (Button) findViewById(R.id.getDot);
        button3 = (Button) findViewById(R.id.getCoupon);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        setUpRecyclerView1();
        setUpRecyclerView2();
        setUpRecyclerView3();

        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(adapter1);
            }
        });

        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(adapter2);
            }
        });
        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(adapter3);
            }
        });

        SharedPreferences loyalty_card = getSharedPreferences("loyalty_card", MODE_PRIVATE);
        final SharedPreferences.Editor editor = loyalty_card.edit();
        String whichLoyalty_card = loyalty_card.getString("user_id", "沒選到Loyalty_card");

        memref.whereEqualTo("store",whichLoyalty_card)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Loyalty_card loyalty_card = documentSnapshot.toObject(Loyalty_card.class);
                            Button points_owned = (Button) findViewById(R.id.getDot);
                            button1 = (Button) findViewById(R.id.useDot);
                            button3 = (Button) findViewById(R.id.getCoupon);
                            button1.setText(loyalty_card.getDotUse());
                            button3.setText(loyalty_card.getCouponCount());
                            points_owned.setText(loyalty_card.getDotGet());
                        }
                    }
                });


        storeref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Store store = documentSnapshot.toObject(Store.class);
                    TextView store1 = (TextView)findViewById(R.id.memberRecStoreName);
                    store1.setText(store.getName());
                }
            }
        });







        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*1), (int)(height*.9));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);



    }

    private void setUpRecyclerView3() {

        Query query = memref.document("BxskPfoZCfztCUSuDUOu")
                .collection("DotUse");

        FirestoreRecyclerOptions<DotUse> options = new FirestoreRecyclerOptions.Builder<DotUse>()
                .setQuery(query, DotUse.class)
                .build();

        adapter1 = new DotUseAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter1);
    }

    private void setUpRecyclerView2() {
        Query query = memref.document("BxskPfoZCfztCUSuDUOu")
                .collection("DotGet");

        FirestoreRecyclerOptions<DotGet> options = new FirestoreRecyclerOptions.Builder<DotGet>()
                .setQuery(query, DotGet.class)
                .build();

        adapter2 = new DotGetAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter2);
    }
    private void setUpRecyclerView1() {
        Query query = memref.document("BxskPfoZCfztCUSuDUOu")
                .collection("DotUse");

        FirestoreRecyclerOptions<CouponGet> options = new FirestoreRecyclerOptions.Builder<CouponGet>()
                .setQuery(query, CouponGet.class)
                .build();

        adapter3 = new CouponGetAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter3);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
        adapter2.startListening();
        adapter3.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
        adapter2.stopListening();
        adapter3.stopListening();

    }

}
