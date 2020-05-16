package com.example.dotdot.StoreCouponManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.dotdot.Coupon;
import com.example.dotdot.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

//OK
public class CouponContent extends Activity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference storeRef = db.collection("store");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_coupon_content);

        //coupon的title
        String whichCoupon = getSharedPreferences("save_coupon", MODE_PRIVATE)
                .getString("coupon_title", "沒選到Coupon");

        //店家ID
        String storeID = getSharedPreferences("save_storeId", MODE_PRIVATE)
                .getString("user_id", "沒會員登入");


        storeRef.document(storeID).collection("coupon")
                .whereEqualTo("couponTitle", whichCoupon)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Coupon coupon = documentSnapshot.toObject(Coupon.class);
                            TextView title = (TextView) findViewById(R.id.title);
                            TextView point = (TextView) findViewById(R.id.point);
                            TextView content = (TextView) findViewById(R.id.content);
                            TextView creatTime = (TextView) findViewById(R.id.creatTime);
                            TextView deadLine = (TextView) findViewById(R.id.deadLine);
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
