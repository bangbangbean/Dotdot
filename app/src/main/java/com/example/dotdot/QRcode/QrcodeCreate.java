package com.example.dotdot.QRcode;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.dotdot.Coupon;
import com.example.dotdot.LoyaltyCard;
import com.example.dotdot.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static com.example.dotdot.App.CHANNEL_1_ID;

public class QrcodeCreate extends Activity {
    private ImageView concelbtn;
    private NotificationManagerCompat notificationManager;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    private CollectionReference storeRef = db.collection("store");

    String Des = "您在椒麻雞有可兌換的優惠券呦 ! ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_create);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 25;

        getWindow().setAttributes(params);

        getCode();
        notificationManager = NotificationManagerCompat.from(this);
        concelbtn = (ImageView) findViewById(R.id.concelbtn);
        concelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                sendOnChannel1();
            }
        });
    }

    public void getCode() {
        ImageView ivCode = (ImageView) findViewById(R.id.codeView);
        BarcodeEncoder encoder = new BarcodeEncoder();
        //member的亂碼Id
        String memberId =getSharedPreferences("save_memberId", MODE_PRIVATE)
                .getString("user_id", "沒會員登入");
        try {
            Bitmap bit;
            bit = encoder.encodeBitmap(memberId, BarcodeFormat.QR_CODE, 250, 250);
            ivCode.setImageBitmap(bit);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void sendOnChannel1() {
        //member的亂碼Id
        String memberId =getSharedPreferences("save_memberId", MODE_PRIVATE)
                .getString("user_id", "沒會員登入");

        memRef.document(memberId)
                .collection("loyalty_card")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            LoyaltyCard loyalty = documentSnapshot.toObject(LoyaltyCard.class);
                            int points_owned = Integer.parseInt(loyalty.getPoints_owned());
                            //String name = loyalty.getName();

                            storeRef.document((String) documentSnapshot.get("store"))
                                    .collection("coupon")
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            int count = 0;
                                            String stop = "";
                                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                Coupon coupon = documentSnapshot.toObject(Coupon.class);
                                                if (coupon.getDotNeed() <= points_owned) {
                                                    //stop = name;
                                                    count++;
                                                }
                                            }
                                            if (count != 0) {
                                                Des = "你在" + stop + "有" + count + "張優惠券可以兌換\n";
                                            } else {
                                                Des = "5555";
                                            }
                                        }
                                    });
                        }
                    }
                });

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_ok)
                .setContentTitle("點點通知")
                .setContentText(Des)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
}
