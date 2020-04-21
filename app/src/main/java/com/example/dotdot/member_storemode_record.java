package com.example.dotdot;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class member_storemode_record extends Activity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memref = db.collection("Member")
            .document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_storemode_record);

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
                            TextView store = (TextView)findViewById(R.id.memberRecStoreName);
                            TextView points_owned = (TextView)findViewById(R.id.getDot);
                            TextView points_use = (TextView)findViewById(R.id.useDot);
                            TextView coupon = (TextView)findViewById(R.id.getCoupon);

//                            store.setText(loyalty_card.getStore());
//                            points_owned.setText(loyalty_card.getPoints_owned());
//                            points_use.setText(loyalty_card.getPoints_use());
//                            coupon.setText(loyalty_card.getCoupon());
                        }

                    }
                });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);
    }
}
