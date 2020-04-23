package com.example.dotdot.QRcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.dotdot.R;
import com.example.dotdot.StoreIndex;

public class QrcodeScannerFinal extends Activity {

    private TextView point_given;
    private Button finishbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner_final);

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
        //--------------------------------------------------------------------------
        point_given = (TextView)findViewById(R.id.point_given);
        finishbtn = (Button)findViewById(R.id.finishbtn);

        Bundle bundle00 =this.getIntent().getExtras();
        String i =bundle00.getString("point_given");
        point_given.setText(i);

        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QrcodeScannerFinal.this, StoreIndex.class);
                startActivity(i);
            }
        });

    }
}
