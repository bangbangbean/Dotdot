package com.example.dotdot;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConfirmExchange extends Activity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference storeRef = db.collection("store");
    private CollectionReference memRef = db.collection("Member");
    private DocumentReference loyaltyCardRef = db.collection("Member")
            .document("iICTR1JL4eAG4B3QBi1S");
    int memberPointOwned;
    int storeDotNeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_exchange);
        TextView memberPoint = findViewById(R.id.memberPoint);
        TextView couponPoint = (TextView) findViewById(R.id.couponPoint);
        TextView wordDot = (TextView) findViewById(R.id.wordDot);
        TextView wordDot2 = (TextView) findViewById(R.id.wordDot2);
        TextView word1 = (TextView) findViewById(R.id.word1);
        TextView word2 = (TextView) findViewById(R.id.word2);
        TextView wordWarning = (TextView) findViewById(R.id.wordWarning);
        TextView text = (TextView) findViewById(R.id.text);
        Button doubleCheckBtn = (Button) findViewById(R.id.doubleCheckBtn);


        //coupon的title名稱
        String whichCoupon = getSharedPreferences("save_coupon", MODE_PRIVATE)
                .getString("coupon_title", "沒選到Coupon");

        //member的亂碼Id
        String member = getSharedPreferences("save_useraccount", MODE_PRIVATE)
                .getString("user_id", "沒人登入");

        //coupon的id
        String CouponId = getSharedPreferences("save_couponId", MODE_PRIVATE)
                .getString("coupon_id", "沒選到Coupon");

        //store的亂碼Id
        String storeId = getSharedPreferences("save_storeId", MODE_PRIVATE)
                .getString("store_id", "沒選擇店家");

        //loyalty_card的亂碼Id
        String loyalty_card_id = getSharedPreferences("save_loyalty_card_id", MODE_PRIVATE)
                .getString("loyalty_card_id", "沒選擇店家");

        storeRef.document(storeId).collection("coupon")
                .whereEqualTo("couponTitle", whichCoupon)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Coupon coupon = documentSnapshot.toObject(Coupon.class);
                            couponPoint.setText(Integer.toString(coupon.getDotNeed()));
                            storeDotNeed = coupon.getDotNeed();
                        }
                    }
                });

        //記得要改成活的
        memRef.document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card")
                .whereEqualTo("store", storeId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Loyalty_card loyalty_card = documentSnapshot.toObject(Loyalty_card.class);
                            memberPoint.setText(loyalty_card.getPoints_owned());
                            memberPointOwned = Integer.parseInt(loyalty_card.getPoints_owned());
                        }
                    }
                });


        doubleCheckBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                memberPoint.setText("");
                couponPoint.setText("");
                wordDot.setText("");
                wordDot2.setText("");
                wordWarning.setText("");
                word1.setText("");
                word2.setText("");
                //點數運算
                if (memberPointOwned < storeDotNeed) {
                    text.setText("你的點數不夠喔><");
                } else {
                    int total = memberPointOwned - storeDotNeed;
                    text.setText("兌換成功您的點數剩餘" + total + "點");

                    //記得改成活的 新增資料到Coupon
                    memRef.document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card")
                            .whereEqualTo("store", storeId)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                                        //新增資料到已有的Coupon中
                                        Map<Object, Object> ownedCoupon = new HashMap<>();
                                        ownedCoupon.put("couponTitle", whichCoupon);
                                        ownedCoupon.put("couponId", CouponId);
                                        ownedCoupon.put("dotNeed", storeDotNeed);
                                        ownedCoupon.put("time", new Date());
                                        memRef.document("iICTR1JL4eAG4B3QBi1S")
                                                .collection("loyalty_card").document(loyalty_card_id)
                                                .collection("Owned_Coupon").add(ownedCoupon);

                                        //修改會員剩餘的點數
                                        Map<Object, Object> upData = new HashMap<>();
                                        upData.put("points_owned", Integer.toString(total));
                                        upData.put("store", storeId);
                                        loyaltyCardRef.collection("loyalty_card").document(loyalty_card_id)
                                                .set(upData, SetOptions.merge());

                                        //新增資料到DotUseRecord
                                        Map<Object, Object> dotUseRecord = new HashMap<>();
                                        dotUseRecord.put("store_couponId", CouponId);
                                        dotUseRecord.put("storeId", storeId);
                                        dotUseRecord.put("point_use", Integer.toString(storeDotNeed));
                                        dotUseRecord.put("time", new Date());
                                        memRef.document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card")
                                                .document(loyalty_card_id).collection("Record")
                                                .add(dotUseRecord);

                                        //新增資料到店家紀錄
                                        Map<Object, Object> couponBeenExchange = new HashMap<>();
                                        couponBeenExchange.put("store_couponId", CouponId);
                                        couponBeenExchange.put("time", new Date());
                                        storeRef.document(storeId).collection("couponBeenExchange")
                                                .add(couponBeenExchange);
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {//如果會員沒有領取過集點卡
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });

                }
                doubleCheckBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
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
