package com.example.dotdot.QRcode;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.dotdot.LoyaltyCard;
import com.example.dotdot.MemberPointRec;
import com.example.dotdot.R;
import com.example.dotdot.Store;
import com.example.dotdot.StorePointRec;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.Date;

public class QrcodeScannerNext extends Activity {

    private EditText consume;
    private Button btn;
    private String points_given;
    private String who;
    private Boolean favorite;

    //FireStore---------------------------------------------------
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference storeRef = db.collection("store").document("nQnT8AAt4NYIRYZFZfAR");
    private CollectionReference stoRef = db.collection("store");
    private CollectionReference memRef = db.collection("Member");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner_next);
        //Pop視窗------------------------------------------------------------------
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 25;

        getWindow().setAttributes(params);

        //固定Session(店家)---------------------------------------
        SharedPreferences session = getSharedPreferences("save_useraccount", MODE_PRIVATE);
        final SharedPreferences.Editor editor = session.edit();
        editor.putString("user_id", "nQnT8AAt4NYIRYZFZfAR"); //椒麻雞大王
        editor.commit();
        who = session.getString("user_id","目前沒人登入");

        //------------------------------------------------------------

        consume = (EditText)findViewById(R.id.consume);//消費金額
        btn = (Button)findViewById(R.id.confirmbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countPoint();
            }
        });
    }
    
    public void countPoint(){
        storeRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Store store = documentSnapshot.toObject(Store.class);
                        String q = store.getThreshold();
                        int i = Integer.parseInt(q);

                        String input = consume.getText().toString().trim();
                        int j = Integer.parseInt(String.valueOf(input));
                        points_given = "" + j / i; //應該得到的點數


                        Intent h = new Intent(getApplicationContext(), QrcodeScannerFinal.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("point_given",points_given);
                        h.putExtras(bundle);

                        startActivity(h);

                        addStoreRecord(); //新增店家點數紀錄
                        addMemberRec(); //新增會員點數紀錄
                        addCardPoint();//增加集點卡點數
                        }
                });
    }

    public void addStoreRecord(){
        Date dt = new Date(); //當下時間
        //Bundle-----------------------------------------------------
        //取的intent中的bundle物件
        Bundle bundle66 =this.getIntent().getExtras();
        String whoData = bundle66.getString("whoData");//QRScanner 得到的會員資料
        //String whoData = "iICTR1JL4eAG4B3QBi1S";//test

        StorePointRec rec = new StorePointRec(whoData,points_given,dt);
        stoRef.document(who).collection("giveDotRecord").add(rec);
    }

    public void addMemberRec(){
        Date dt = new Date();//當下時間
        //Bundle-----------------------------------------------------
        //取的intent中的bundle物件
        Bundle bundle66 =this.getIntent().getExtras();
        String whoData = bundle66.getString("whoData");//QRScanner 得到的會員資料
        String points_get = "+" + points_given;

        MemberPointRec rec = new MemberPointRec(points_get, who, dt);
        memRef.document(whoData).collection("loyalty_card")
                .whereEqualTo("store",who)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            String oo = documentSnapshot.getId();
                            memRef.document(whoData).collection("loyalty_card")
                                    .document(oo).collection("Record").add(rec);
                        }
                    }
                });
    }

    public void addCardPoint(){
        Date dt = new Date();
        //Bundle-----------------------------------------------------
        //取的intent中的bundle物件
        Bundle bundle66 =this.getIntent().getExtras();
        String whoData = bundle66.getString("whoData");//QRScanner 得到的會員資料
        String points_get = points_given;
        //String whoData = "iICTR1JL4eAG4B3QBi1S";//test

        memRef.document(whoData).collection("loyalty_card").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        int g = 1;
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            LoyaltyCard loyalty = documentSnapshot.toObject(LoyaltyCard.class);
                            String points_owned = loyalty.getPoints_owned();
                            String store = loyalty.getStore();
                            favorite = loyalty.getFavorite();

                            String docID = documentSnapshot.getId();

                            if(store.equals(who)){//如果此會員已有這店家的集點卡，則增加本次交易的點數
                                int a = Integer.parseInt(points_get);
                                int b = Integer.parseInt(points_owned);
                                String pointsFinal = "" + (a + b);

                                LoyaltyCard rec = new LoyaltyCard(pointsFinal, who, favorite);
                                memRef.document(whoData).collection("loyalty_card").document(docID)
                                        .set(rec, SetOptions.merge());
                                g += 1;

                                break;
                            }

                        }
                        if(g == 1){ //如果此會員沒有這店家集點卡，則新增
                            favorite = false;
                            LoyaltyCard rec = new LoyaltyCard(points_get, who, favorite);
                            memRef.document(whoData).collection("loyalty_card").add(rec);
                        }

                    }
                });
    }
}
