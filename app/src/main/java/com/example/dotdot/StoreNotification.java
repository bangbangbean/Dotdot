package com.example.dotdot;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class StoreNotification extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_notification);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9) , (int)(height*.8));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = -180;

        getWindow().setAttributes(params);

        init();

    }

    private void init() {
        tabLayout=(TabLayout)findViewById(R.id.tab_bar);
        viewPager=(ViewPager)findViewById(R.id.views_pager);
        //設定TabLayout標籤的顯示方式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //迴圈注入標籤
        for (String tab:titles){
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        //設定TabLayout點選事件
        tabLayout.setOnTabSelectedListener(this);

        fragments.add(new StoreNotificationSend()); //發送通知
        fragments.add(new StoreNotificationNormal()); //一般通知

        viewPagerAdapter= new StoreNotificationAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StoreNotificationAdapter viewPagerAdapter;
    //TabLayout標籤
    private String[] titles=new String[]{"發送通知","一般"};
    private List<Fragment> fragments=new ArrayList<>();


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
