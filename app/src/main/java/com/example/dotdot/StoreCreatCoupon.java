package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StoreCreatCoupon extends AppCompatActivity {
    private TextView et_startTime;
    private TextView et_endTime;

    private Date startTime = new Date();
    private Date endTime = new Date();

    private TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_store_creat_coupon);

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
                if(v.getId() == R.id.et_startTime){
                    startTime = date;
                }else {
                    endTime = date;
                }
                TextView editText = (TextView)v;
                editText.setText(getTime(date));
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
                .setLabel("年","月","日","時","分","秒")
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
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public Date getDateFromString(String datetoSaved){

        try {
            Date date = format.parse(datetoSaved);
            return date ;
        } catch (ParseException e){
            return null ;
        }

    }

    public void savetoDatabase(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> addAnimal = new HashMap<>();
        addAnimal.put("dob",getDateFromString("2017-10-5T09:27:37Z"));

        db.collection("users").document("animals").set(addAnimal);

    }
}

