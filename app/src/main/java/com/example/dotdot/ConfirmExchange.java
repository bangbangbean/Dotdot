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

public class ConfirmExchange extends Activity {



    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference couponRef = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("coupon");

    private CollectionReference memRef = db.collection("Member/iICTR1JL4eAG4B3QBi1S/loyalty_card");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_exchange);

        //coupon的title
        SharedPreferences coupon = getSharedPreferences("save_coupon", MODE_PRIVATE);
        String whichCoupon = coupon.getString("user_id", "沒選到Coupon");

        couponRef.whereEqualTo("couponTitle",whichCoupon)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Coupon coupon = documentSnapshot.toObject(Coupon.class);
                            TextView couponPoint = (TextView)findViewById(R.id.couponPoint);
                            couponPoint.setText(coupon.getDotNeed());
                        }
                    }
                });

//        //member的亂碼Id
//        SharedPreferences session = getSharedPreferences("save_useraccount", MODE_PRIVATE);
//        String member = session.getString("user_id", "沒人登入");
//
//        //store的亂碼Id
//        SharedPreferences store = getSharedPreferences("save_coupon", MODE_PRIVATE);
//        String storeId = store.getString("user_id", "沒選擇店家");
        memRef.whereEqualTo("store","nQnT8AAt4NYIRYZFZfAR")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            LoyaltyCard loyaltyCard = documentSnapshot.toObject(LoyaltyCard.class);
                            TextView memberPoint = (TextView)findViewById(R.id.memberPoint);
                            memberPoint.setText(loyaltyCard.getPoints_owned());
                        }
                    }
                });


        //設定彈出視窗---------------------------------------------------------------------
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = -10;

        getWindow().setAttributes(params);
    }
}
