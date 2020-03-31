package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrcodeCreate extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_create);
        getCode();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 25;

        getWindow().setAttributes(params);
    }

    public void getCode(){
        ImageView ivCode=(ImageView)findViewById(R.id.codeView);
        BarcodeEncoder encoder = new BarcodeEncoder();
        SharedPreferences session = getSharedPreferences("save_useraccount", MODE_PRIVATE);
        final SharedPreferences.Editor editor = session.edit();
        String who = session.getString("user_id","目前沒人登入");
        try{
            Bitmap bit;
            bit = encoder.encodeBitmap(who, BarcodeFormat.QR_CODE,250,250);
            ivCode.setImageBitmap(bit);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }
}
