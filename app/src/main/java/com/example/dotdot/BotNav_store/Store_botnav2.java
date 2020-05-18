package com.example.dotdot.BotNav_store;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.dotdot.R;
import com.example.dotdot.StoreCouponManager.StoreOverlookCoupon;
import com.example.dotdot.StoreCreatLoyaltyCard;
import com.example.dotdot.StoreProfile;
import com.example.dotdot.StoreTransaction;
import com.example.dotdot.frag2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Store_botnav2 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            =(item) ->{
        Fragment selectedFragment = null;

        switch (item.getItemId()){
            case R.id.cusomer:
                selectedFragment = new StoreProfile();
                break;
            case R.id.shopping:
                selectedFragment = new StoreCreatLoyaltyCard();
                break;
            case R.id.shipping:
                selectedFragment = new StoreOverlookCoupon();
                break;
            case R.id.consume:
                selectedFragment = new StoreTransaction();
                break;
            case R.id.setting:
                selectedFragment = new frag2();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.store_fragment_container, selectedFragment).commit();
        return true;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_botnav);

        bottomNavigationView = findViewById(R.id.store_nav);
        bottomNavigationView.inflateMenu(R.menu.store_main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setItemIconTintList(null);

        getSupportFragmentManager().beginTransaction().replace(R.id.store_fragment_container, new StoreCreatLoyaltyCard()).commit();
    }
}
