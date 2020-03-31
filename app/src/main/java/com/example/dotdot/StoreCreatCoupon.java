package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StoreCreatCoupon extends Activity {
    private TextView et_startTime;
    private TextView et_endTime;

    private Date startTime = new Date();
    private Date endTime = new Date();

    String beginning, ending;

    private TimePickerView pvTime;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference couponRef = db.collection("store");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_store_creat_coupon);

        Button creatBtn = (Button) findViewById(R.id.creatBtn);
        Button backBtn = (Button)findViewById(R.id.backBtn);
        creatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                creatCoupon();
                Intent intent = new Intent(StoreCreatCoupon.this, StoreOverlookCoupon.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , StoreOverlookCoupon.class);
                startActivity(intent);
            }
        });

        //設定彈出視窗---------------------------------------------------------------------

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .9));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = -10;

        getWindow().setAttributes(params);

        //設定時間--------------------------------------------------------------------------

        et_startTime = findViewById(R.id.et_startTime);
        et_endTime = findViewById(R.id.et_endTime);
        et_startTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.et_startTime:
                        if (pvTime != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(startTime);
                            pvTime.setDate(calendar);
                            pvTime.show(view);
                        }
                        break;
                    case R.id.et_endTime:
                        if (pvTime != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(endTime);
                            pvTime.setDate(calendar);
                            pvTime.show(view);
                        }
                        break;
                }
            }
        });
        et_endTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.et_startTime:
                        if (pvTime != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(startTime);
                            pvTime.setDate(calendar);
                            pvTime.show(view);
                        }
                        break;
                    case R.id.et_endTime:
                        if (pvTime != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(endTime);
                            pvTime.setDate(calendar);
                            pvTime.show(view);
                        }
                        break;
                }
            }
        });
        initTimePicker();
    }

    private void initTimePicker() {

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //如果是開始時間的EditText
                if (v.getId() == R.id.et_startTime) {
                    startTime = date;
                    TextView editText = (TextView) v;
                    editText.setText(getTime(date));
                    beginning = getTime(date);
                } else {
                    endTime = date;
                    TextView editText = (TextView) v;
                    editText.setText(getTime(date));
                    ending = getTime(date);
                }
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                    }
                })
                .setCancelText("取消")//取消按鈕文字
                .setSubmitText("確定")//確認按鈕文字
                .setTitleSize(20)//標題文字大小
                .setTitleText("選擇時間")//標題文字
                .setOutSideCancelable(false)//點選螢幕，點在控制元件外部範圍時，是否取消顯示
                .isCyclic(true)//是否迴圈滾動
                .setTitleColor(Color.BLACK)//標題文字顏色
                .setSubmitColor(Color.BLUE)//確定按鈕文字顏色
                .setCancelColor(Color.BLUE)//取消按鈕文字顏色
                .setTitleBgColor(Color.WHITE)//標題背景顏色 Night mode
                .setLabel("年", "月", "日", "時", "分", "秒")
                .isDialog(true)//是否顯示為對話方塊樣式
                .setType(new boolean[]{true, true, true, true, true, false})
                .build();
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改動畫樣式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部顯示
            }
        }
    }

    private String getTime(Date date) {//可根據需要自行擷取資料顯示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date getDateFromString(String datetoSaved) {

        try {
            Date date = format.parse(datetoSaved);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public void creatCoupon() {
        EditText couponTitle = (EditText)findViewById(R.id.couponTitle);
        AutoCompleteTextView couponContent = (AutoCompleteTextView)findViewById(R.id.couponContent);
        EditText dotNeed = (EditText)findViewById(R.id.dotNeed);

        String title = couponTitle.getText().toString();
        String content = couponContent.getText().toString();
        String need = dotNeed.getText().toString();
        Coupon coupon = new Coupon(getDateFromString(beginning),getDateFromString(ending),title,content,need);
        couponRef.document("nQnT8AAt4NYIRYZFZfAR").collection("coupon")
                .add(coupon);
    }

    //-------------------------------------------------------------------------------------
}

