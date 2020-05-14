package com.example.dotdot.MemberCouponManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.dotdot.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class MemberUseCoupon extends Activity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_use_coupon);
        TextView wordWarning = (TextView) findViewById(R.id.wordWarning);
        TextView text = (TextView) findViewById(R.id.text);
        TextView word1 = (TextView) findViewById(R.id.word1);
        Button doubleCheckBtn = (Button) findViewById(R.id.doubleCheckBtn);
        Button cancelBtn = findViewById(R.id.CancelBtn2);
        Button doubleCeckBtn2 = findViewById(R.id.doubleCheckBtn2);

        doubleCeckBtn2.setVisibility(View.INVISIBLE);

        //coupon的id
        String ownedCouponId = getSharedPreferences("save_ownedCouponId", MODE_PRIVATE)
                .getString("owned_coupon_id", "沒選到Coupon");

        //loyalty_card的亂碼Id
        String loyalty_card_id = getSharedPreferences("save_loyalty_card_id", MODE_PRIVATE)
                .getString("loyalty_card_id", "沒選擇店家");
        //member的亂碼Id
        String memberId = getSharedPreferences("save_useraccount", MODE_PRIVATE)
                .getString("user_id", "沒人登入");

        //把優惠券從會員資料刪除
        doubleCheckBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doubleCheckBtn.setVisibility(View.INVISIBLE);
                cancelBtn.setVisibility(View.INVISIBLE);
                doubleCeckBtn2.setVisibility(View.VISIBLE);

                //記得改成活的 新增資料到Coupon
                db.collection("Member").document(memberId)
                        .collection("loyalty_card").document(loyalty_card_id)
                        .collection("Owned_Coupon").document(ownedCouponId)
                        .delete();
                word1.setText("");
                text.setText("使用成功><");

                doubleCeckBtn2.setOnClickListener(new View.OnClickListener() {
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
        params.y =-10;

        getWindow().setAttributes(params);
    }
}
