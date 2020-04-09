package com.example.dotdot;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

    public class CouponExchange extends AppCompatActivity {

        BottomNavigationView bottomNavigationView;

        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                =(item) ->{
            Fragment selectedFragment = null;


            switch (item.getItemId()){
                case R.id.cusomer:
                    selectedFragment = new Profile();
                    break;
                case R.id.shopping:
                    selectedFragment = new frag2();
                    break;
                case R.id.shipping:
                    selectedFragment = new frag2();
                    break;
                case R.id.consume:
                    selectedFragment = new frag2();
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
            setContentView(R.layout.activity_member_overlook_coupon);

            bottomNavigationView = findViewById(R.id.nav);
            bottomNavigationView.inflateMenu(R.menu.main_menu);
            bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            bottomNavigationView.setItemIconTintList(null);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile()).commit();
        }
    }


