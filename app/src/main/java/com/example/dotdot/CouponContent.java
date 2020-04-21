package com.example.dotdot;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

public class CouponContent extends Activity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference couponRef = db.collection("store")
            .document("nQnT8AAt4NYIRYZFZfAR").collection("coupon");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_coupon_content);

        //coupon的title
        SharedPreferences coupon = getSharedPreferences("save_coupon", MODE_PRIVATE);
        String whichCoupon = coupon.getString("coupon_title", "沒選到Coupon");

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
                            TextView creatTime = (TextView)findViewById(R.id.creatTime);
                            TextView deadLine = (TextView)findViewById(R.id.deadLine);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            title.setText(coupon.getCouponTitle());
                            point.setText(Integer.toString(coupon.getDotNeed()));
                            content.setText(coupon.getCouponContent());
                            creatTime.setText(sdf.format(coupon.getCreatTime()));
                            deadLine.setText(sdf.format(coupon.getDeadLine()));
                        }
                    }
                });
        //設定彈出視窗---------------------------------------------------------------------
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = -10;

        getWindow().setAttributes(params);


    }
}
