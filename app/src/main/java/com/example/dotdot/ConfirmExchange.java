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
    private CollectionReference couponRef = db.collection("store");
    private CollectionReference memRef = db.collection("Member");
    private CollectionReference addCouponRef = db.collection("Coupon");
    private DocumentReference loyaltyCardRef = db.collection("Member").document("iICTR1JL4eAG4B3QBi1S");
    private CollectionReference DotUseRecordRef = db.collection("Member");
    int memberPointOwned;
    int storeDotNeed;
    String loyalty_card_id;
    String coupon_id ;

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

        couponRef.document(storeId).collection("coupon")
                .whereEqualTo("couponTitle", whichCoupon)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Coupon coupon = documentSnapshot.toObject(Coupon.class);
                            couponPoint.setText(coupon.getDotNeed());
                            storeDotNeed = Integer.parseInt(coupon.getDotNeed());
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
                            loyalty_card_id = documentSnapshot.getId();
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
                    addCouponRef.whereEqualTo("member", "iICTR1JL4eAG4B3QBi1S")
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                                        //新增資料到已有的Coupon中
                                        coupon_id = documentSnapshot.getId();
                                        Map<Object,Object> ownedCoupon = new HashMap<>();
                                        ownedCoupon.put("store_couponId",CouponId);
                                        ownedCoupon.put("storeId",storeId);
                                        ownedCoupon.put("time",new Date());
                                        addCouponRef.document(coupon_id)
                                                .collection("Owned_coupon").add(ownedCoupon);

                                        //修改會員剩餘的點數
                                        Map<Object,Object> upData = new HashMap<>();
                                        upData.put("points_owned",Integer.toString(total));
                                        upData.put("storeId",storeId);
                                        loyaltyCardRef.collection("loyalty_card").document(loyalty_card_id)
                                                .set(upData);

                                        //新增資料到DotUseRecord
                                        Map<Object,Object> dotUseRecord = new HashMap<>();
                                        dotUseRecord.put("store_couponId",CouponId);
                                        dotUseRecord.put("storeId",storeId);
                                        dotUseRecord.put("pointUesd",storeDotNeed);
                                        dotUseRecord.put("time",new Date());
                                        DotUseRecordRef.document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card")
                                                .document(loyalty_card_id).collection("DotUseRecord")
                                                .add(dotUseRecord);

                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    addCouponRef.add("eBd4gJSxZQiamKAbEH0P");//記得改成改活的

                                    Map<Object,Object> ownedCoupon = new HashMap<>();
                                    ownedCoupon.put("store_couponId",CouponId);
                                    ownedCoupon.put("store",storeId);
                                    ownedCoupon.put("time",new Date());
                                    //新增資料到Coupon中
                                    addCouponRef.document("好棒棒").collection("Owned_coupon").add(ownedCoupon);

                                    //修改會員剩餘的點數
                                    Loyalty_card loyaltycard = new Loyalty_card(Integer.toString(total),storeId);
                                    loyaltyCardRef.collection("loyalty_card").document(loyalty_card_id)
                                            .set(loyaltycard, SetOptions.merge());
                                }
                            });

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
