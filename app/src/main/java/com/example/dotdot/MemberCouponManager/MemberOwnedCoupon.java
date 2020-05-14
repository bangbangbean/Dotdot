package com.example.dotdot.MemberCouponManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotdot.Coupon;
import com.example.dotdot.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.AccessTokenManager.TAG;

public class MemberOwnedCoupon extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference memRef = db.collection("Member");
    private MemberOwnedCouponAdapter adapter;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_member_owned_coupon, container, false);
        setUpRecyclerView();


        //跳轉頁到MemberCanExchangeCoupon
        Button canExchangeBtn = (Button) view.findViewById(R.id.canExchangeBtn);
        canExchangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MemberCanExchangeCoupon llf = new MemberCanExchangeCoupon();
                ft.replace(R.id.fragment_container, llf);
                ft.commit();
            }
        });
        //跳轉頁到MemberOverlookCoupon
        Button overlookBtn = (Button) view.findViewById(R.id.overlookBtn);
        overlookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MemberOverlookCoupon llf = new MemberOverlookCoupon();
                ft.replace(R.id.fragment_container, llf);
                ft.commit();
            }
        });




        return view;
    }

    private void setUpRecyclerView() {

        //loyalty_card的亂碼Id
        String loyalty_card_id = getActivity().getSharedPreferences("save_loyalty_card_id", MODE_PRIVATE)
                .getString("loyalty_card_id", "沒選擇店家");

        //member的亂碼Id
        String memberId = getActivity().getSharedPreferences("save_useraccount", MODE_PRIVATE)
                .getString("user_id", "沒人登入");

        Query query = memRef.document(memberId)
                .collection("loyalty_card").document(loyalty_card_id)
                .collection("Owned_Coupon");

        FirestoreRecyclerOptions<Coupon> options = new FirestoreRecyclerOptions.Builder<Coupon>()
                .setQuery(query, Coupon.class)
                .build();

        adapter = new MemberOwnedCouponAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);


        //coupon的title名稱
        SharedPreferences couponpref = this.getActivity().getSharedPreferences("save_coupon", MODE_PRIVATE);

        //coupon的Id名稱
        SharedPreferences couponIdpref = this.getActivity().getSharedPreferences("save_couponId", MODE_PRIVATE);

        //ownedcoupon的Id名稱
        SharedPreferences ownedCouponIdpref = this.getActivity().getSharedPreferences("save_ownedCouponId", MODE_PRIVATE);

        adapter.setOnItemClickListener(new MemberOwnedCouponAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                if(position != -1) {
                    smoothMoveToPosition(recyclerView, position);
                }
                Coupon coupon = documentSnapshot.toObject(Coupon.class);
                ownedCouponIdpref.edit()
                        .putString("owned_coupon_id", documentSnapshot.getId())
                        .commit();
                couponpref.edit()
                        .putString("coupon_title", coupon.getCouponTitle())
                        .commit();
                couponIdpref.edit()
                        .putString("coupon_id", documentSnapshot.getId())
                        .commit();
                Intent intent = new Intent(getActivity(), MemberOwnedCouponContent.class);
                startActivity(intent);
            }
        });

    }
    private boolean mShouldScroll;
    private int mToPosition;

    private void smoothMoveToPosition(RecyclerView recyclerView , final int position){

        int firstItemPosition = -1;
        int lastItemPosition = -1;


        firstItemPosition = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0));
        lastItemPosition = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(recyclerView.getChildCount() - 1));

        Log.i(TAG, "smoothMoveToPosition: firstItemPosition::" + firstItemPosition + " lastItemPosition::" + lastItemPosition + "\n");

        if (position < firstItemPosition) {
            // 第一种可能:跳转位置在第一个可见位置之前
            recyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItemPosition) {
            // 第二种可能:跳转位置在第一个可见位置之后,在最后一个可见项之前
            int movePosition = position - firstItemPosition;
            if (movePosition >= 0 && movePosition < recyclerView.getChildCount()) {
                int top = recyclerView.getChildAt(movePosition).getTop();
                recyclerView.smoothScrollBy(0, top);//dx>0===>向左  dy>0====>向上
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            recyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {//
                    mShouldScroll = false;
                    smoothMoveToPosition(recyclerView, mToPosition);
                }
            }
        });

    }




    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}