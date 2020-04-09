package com.example.dotdot;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class MemCouponContent extends Activity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference couponRef = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("coupon");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mem_coupon_content);

        Button confirmBtn = (Button)findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConfirmExchange.class);
                startActivity(intent);
            }
        });

        //coupon的title名稱
        SharedPreferences coupon = getSharedPreferences("save_coupon", MODE_PRIVATE);
        String whichCoupon = coupon.getString("user_id", "沒選到Coupon");



        couponRef.whereEqualTo("couponTitle",whichCoupon)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Coupon coupon = documentSnapshot.toObject(Coupon.class);
                            TextView title = (TextView)findViewById(R.id.title);
                            TextView point = (TextView)findViewById(R.id.point);
                            TextView content = (TextView)findViewById(R.id.content);
                            TextView deadLine = (TextView)findViewById(R.id.deadLine);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            title.setText(coupon.getCouponTitle());
                            point.setText(coupon.getDotNeed());
                            content.setText(coupon.getCouponContent());
                            deadLine.setText(sdf.format(coupon.getDeadLine()));
                        }
                    }
                });
        //設定彈出視窗---------------------------------------------------------------------
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = -10;

        getWindow().setAttributes(params);
    }
}
