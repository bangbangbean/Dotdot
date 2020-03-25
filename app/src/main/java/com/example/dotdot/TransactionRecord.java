package com.example.dotdot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TransactionRecord extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_record);

        listView =(ListView)findViewById(R.id.recordList);
        listView.setAdapter((MyAdapter) new MyAdapter());

    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 2;
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
