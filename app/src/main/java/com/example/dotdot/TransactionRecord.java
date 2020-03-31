package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionRecord extends Activity {

    private ListView listView;
    private Switch modeSwitch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_record);

        modeSwitch = (Switch)findViewById(R.id.modeSwitch);

        listView =(ListView)findViewById(R.id.recordList);

        //利用Switch切換模式
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    //顯示店家模式紀錄
                    listView.setAdapter((StoreAdapter) new StoreAdapter());
                    //點擊觸發事件_跳出選定店家紀錄
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext() , member_storemode_record.class);
                            startActivity(i);
                        }
                    });
                }
                else {
                    //顯示清單模式紀錄
                    listView.setAdapter(new TransactionRecord.ListAdapter());
                }
            }
        });

    }

    //自訂Adapter(清單模式)
    private class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        class holder{
            public TextView yearmonth, day, th, time, storeName, obj, situation;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            holder holder;
            if (v == null) {
                v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.member_listmode_list, null);
                holder = new ListAdapter.holder();
                holder.yearmonth = (TextView) findViewById(R.id.yearmonth);
                holder.day = (TextView) findViewById(R.id.day);
                holder.th = (TextView) findViewById(R.id.th);
                holder.time = (TextView) findViewById(R.id.time);
                holder.storeName = (TextView) findViewById(R.id.storeName);
                holder.obj = (TextView) findViewById(R.id.obj);
                holder.situation = (TextView) findViewById(R.id.situation);
                v.setTag(holder);
            } else{
                holder = (ListAdapter.holder) v.getTag();
            }
            return v;
        }
    }
    //自訂Adapter(店家模式)
    private class StoreAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 1;
        }//顯示數量


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        class Viewholder{
            public TextView memberRecStoreName, get, use, coupon, getDot, useDot, getCoupon
                    , textView22, textView24, textView26;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            Viewholder holder;
            if (v == null) {
                v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.member_storemode_list, null);
                holder = new Viewholder();
                holder.memberRecStoreName = (TextView) findViewById(R.id.memberRecStoreName);
                holder.get = (TextView) findViewById(R.id.get);
                holder.use = (TextView) findViewById(R.id.use);
                holder.coupon = (TextView) findViewById(R.id.coupon);
                holder.getDot = (TextView) findViewById(R.id.getDot);
                holder.useDot = (TextView) findViewById(R.id.useDot);
                holder.getCoupon = (TextView) findViewById(R.id.getCoupon);
                holder.textView22 = (TextView) findViewById(R.id.textView22);
                holder.textView24 = (TextView) findViewById(R.id.textView24);
                holder.textView26 = (TextView) findViewById(R.id.textView26);

                v.setTag(holder);
            } else{
                holder = (Viewholder) v.getTag();
            }
            return v;
        }
    }
}
