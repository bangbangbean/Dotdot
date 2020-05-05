package com.example.dotdot;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class StoreCreatLoyaltyCard extends Fragment {
    private String color = "blue";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference SloyaltyCardRef = db.document("store/nQnT8AAt4NYIRYZFZfAR");
    private DocumentReference SloyaltyCardRef1 = db.document("Member/iICTR1JL4eAG4B3QBi1S/loyalty_card/BxskPfoZCfztCUSuDUOu");
    EditText inputDollar;
    String Threshold;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //尚未設定集點卡
        View view = inflater.inflate(R.layout.activity_store_creat_loyalty_card, container, false);
        Button setRed = view.findViewById(R.id.redDot);
        Button setBlue = view.findViewById(R.id.blueDot);
        Button setGreen = view.findViewById(R.id.greenDot);
        Button setYellow = view.findViewById(R.id.yellowDot);
        Button saveBtn = view.findViewById(R.id.saveBtn);

        TextView dot1 = view.findViewById(R.id.dot1);
        TextView dot2 = view.findViewById(R.id.dot2);
        TextView dot3 = view.findViewById(R.id.dot3);
        TextView dot4 = view.findViewById(R.id.dot4);
        TextView dot5 = view.findViewById(R.id.dot5);
        TextView dot6 = view.findViewById(R.id.dot6);
        TextView dot7 = view.findViewById(R.id.dot7);
        inputDollar = view.findViewById(R.id.inputDollar);


        //選擇顏色---------------------------------------------------------------------------
        setGreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Drawable dot_green = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.green_dot);
                dot1.setBackgroundDrawable(dot_green);
                dot2.setBackgroundDrawable(dot_green);
                dot3.setBackgroundDrawable(dot_green);
                dot4.setBackgroundDrawable(dot_green);
                dot5.setBackgroundDrawable(dot_green);
                dot6.setBackgroundDrawable(dot_green);
                dot7.setBackgroundDrawable(dot_green);
                color = "green";
            }
        });setBlue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable dot_blue = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.blue_dot);
                dot1.setBackgroundDrawable(dot_blue);
                dot2.setBackgroundDrawable(dot_blue);
                dot3.setBackgroundDrawable(dot_blue);
                dot4.setBackgroundDrawable(dot_blue);
                dot5.setBackgroundDrawable(dot_blue);
                dot6.setBackgroundDrawable(dot_blue);
                dot7.setBackgroundDrawable(dot_blue);
                color = "blue";
            }
        });setRed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable dot_red = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.red_dot);
                dot1.setBackgroundDrawable(dot_red);
                dot2.setBackgroundDrawable(dot_red);
                dot3.setBackgroundDrawable(dot_red);
                dot4.setBackgroundDrawable(dot_red);
                dot5.setBackgroundDrawable(dot_red);
                dot6.setBackgroundDrawable(dot_red);
                dot7.setBackgroundDrawable(dot_red);
                color = "red";
            }
        });setYellow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable dot_yellow = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.yellow_dot);
                dot1.setBackgroundDrawable(dot_yellow);
                dot2.setBackgroundDrawable(dot_yellow);
                dot3.setBackgroundDrawable(dot_yellow);
                dot4.setBackgroundDrawable(dot_yellow);
                dot5.setBackgroundDrawable(dot_yellow);
                dot6.setBackgroundDrawable(dot_yellow);
                dot7.setBackgroundDrawable(dot_yellow);
                color = "yellow";
            }
        });
        //儲存---------------------------------------------------------
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(inputDollar.length() == 0){
                    Toast t = Toast.makeText(getContext(), "請輸入金錢點數比金額 !", Toast.LENGTH_SHORT);
                    TextView tv = (TextView) t.getView().findViewById(android.R.id.message);
                    tv.setTextSize(30);
                    t.show();
                }else{
                    String money = inputDollar.getText().toString();
                    Map<Object, Object> StoreCreatLoyaltyCard = new HashMap<>();
                    StoreCreatLoyaltyCard.put("color", color);
                    StoreCreatLoyaltyCard.put("Threshold", money);
                    SloyaltyCardRef.set(StoreCreatLoyaltyCard, SetOptions.merge());
                    SloyaltyCardRef1.set(StoreCreatLoyaltyCard, SetOptions.merge());
                    Toast t = Toast.makeText(getContext(), "設定集點卡成功 !", Toast.LENGTH_SHORT);
                    TextView tv = (TextView) t.getView().findViewById(android.R.id.message);
                    tv.setTextSize(30);
                    t.show();
                }
            }
        });

        //如果已經設定過集點卡--------------------------------------------------------------------------
        View view1 = inflater.inflate(R.layout.store_loyalty_card_overlook, container, false);

        TextView Title = view1.findViewById(R.id.Title);
        TextView Dot1 = view1.findViewById(R.id.Dot1);
        TextView Dot2 = view1.findViewById(R.id.Dot2);
        TextView Dot3 = view1.findViewById(R.id.Dot3);
        TextView Dot4 = view1.findViewById(R.id.Dot4);
        TextView Dot5 = view1.findViewById(R.id.Dot5);
        TextView Dot6 = view1.findViewById(R.id.Dot6);
        TextView Dot7 = view1.findViewById(R.id.Dot7);
        TextView coupon = view1.findViewById(R.id.coupon);

        SloyaltyCardRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Title.setText((String) documentSnapshot.get("name"));
                        String color = (String) documentSnapshot.get("color");
                        Threshold = (String) documentSnapshot.get("Threshold");

                        if (color.equals("red")) {
                            Drawable dot_red = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.red_dot);
                            Dot1.setBackgroundDrawable(dot_red);
                            Dot2.setBackgroundDrawable(dot_red);
                            Dot3.setBackgroundDrawable(dot_red);
                            Dot4.setBackgroundDrawable(dot_red);
                            Dot5.setBackgroundDrawable(dot_red);
                            Dot6.setBackgroundDrawable(dot_red);
                            Dot7.setBackgroundDrawable(dot_red);
                        }else if(color.equals("green")){
                            Drawable dot_green = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.green_dot);
                            Dot1.setBackgroundDrawable(dot_green);
                            Dot2.setBackgroundDrawable(dot_green);
                            Dot3.setBackgroundDrawable(dot_green);
                            Dot4.setBackgroundDrawable(dot_green);
                            Dot5.setBackgroundDrawable(dot_green);
                            Dot6.setBackgroundDrawable(dot_green);
                            Dot7.setBackgroundDrawable(dot_green);
                        }else if(color.equals("blue")){
                            Drawable dot_blue = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.blue_dot);
                            Dot1.setBackgroundDrawable(dot_blue);
                            Dot2.setBackgroundDrawable(dot_blue);
                            Dot3.setBackgroundDrawable(dot_blue);
                            Dot4.setBackgroundDrawable(dot_blue);
                            Dot5.setBackgroundDrawable(dot_blue);
                            Dot6.setBackgroundDrawable(dot_blue);
                            Dot7.setBackgroundDrawable(dot_blue);
                        }else if(color.equals("yellow")){
                            Drawable dot_yellow = StoreCreatLoyaltyCard.this.getResources().getDrawable(R.drawable.yellow_dot);
                            Dot1.setBackgroundDrawable(dot_yellow);
                            Dot2.setBackgroundDrawable(dot_yellow);
                            Dot3.setBackgroundDrawable(dot_yellow);
                            Dot4.setBackgroundDrawable(dot_yellow);
                            Dot5.setBackgroundDrawable(dot_yellow);
                            Dot6.setBackgroundDrawable(dot_yellow);
                            Dot7.setBackgroundDrawable(dot_yellow);
                        }
                    }
                });


        if(Threshold!=null){
            return view;
        }else {
            return view1;
        }
    }
}