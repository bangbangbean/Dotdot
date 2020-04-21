package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class StoreNotiSending extends Activity {

    private TextView title, contxt;
    private ImageView img;
    private Button btn;

    private String who;

    //FireStore---------------------------------------------------
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notiRef = db.collection("store");
    private CollectionReference notisRef = db.collection("Notice_store");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_noti_sending);

        //Pop視窗------------------------------------------------------------------
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9), (int)(height*.75));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -110;

        getWindow().setAttributes(params);
        //--------------------------------------------------------------------------
        //固定Session(店家)---------------------------------------
        SharedPreferences session = getSharedPreferences("save_useraccount", MODE_PRIVATE);
        final SharedPreferences.Editor editor = session.edit();
        editor.putString("user_id", "nQnT8AAt4NYIRYZFZfAR"); //椒麻雞大王
        editor.commit();
        who = session.getString("user_id","目前沒人登入");

        //------------------------------------------------------------

        title = (TextView)findViewById(R.id.sendTitle);
        contxt = (TextView)findViewById(R.id.sendContxt);
        img = (ImageView)findViewById(R.id.sendImg);
        btn = (Button)findViewById(R.id.sendBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStoreNoti();//新增通知
                finish();
            }
        });

    }

    public void addStoreNoti(){
        Date dt = new Date();//當下時間
        String tit = title.getText().toString().trim();
        String con = contxt.getText().toString();

        Note_store_noit noti = new Note_store_noit(tit, dt, con, who);
        notiRef.document(who).collection("Notice").add(noti);
        notisRef.add(noti);

    }
}

