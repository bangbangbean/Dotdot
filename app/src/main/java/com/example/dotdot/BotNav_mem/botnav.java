package com.example.dotdot.BotNav_mem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.dotdot.Collectioncard;
import com.example.dotdot.MemberCouponManager.MemberOverlookCoupon;
import com.example.dotdot.Profile;
import com.example.dotdot.R;
import com.example.dotdot.TransactionRecord;
import com.example.dotdot.frag2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class botnav extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            =(item) ->{
        Fragment selectedFragment = null;


        switch (item.getItemId()){
            case R.id.cusomer:
                selectedFragment = new Profile();
                break;
            case R.id.shopping:
                selectedFragment = new Collectioncard();
                break;
            case R.id.shipping:
                selectedFragment = new MemberOverlookCoupon();
                break;
            case R.id.consume:
                selectedFragment = new TransactionRecord();
                break;
            case R.id.setting:
                selectedFragment = new frag2();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botnav);

        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.inflateMenu(R.menu.main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setItemIconTintList(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile()).commit();
    }
}
