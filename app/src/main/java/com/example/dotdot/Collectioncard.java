package com.example.dotdot;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import static android.content.Context.MODE_PRIVATE;

public class Collectioncard extends Fragment {
    private CollectioncardAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //記得改成活的
    private CollectionReference note = db.collection("Member")
            .document("iICTR1JL4eAG4B3QBi1S").collection("loyalty_card");
    private View view;

    private SwipeRecyclerView swipeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.collectioncard, container, false);

        swipeRecyclerView = (SwipeRecyclerView) view.findViewById(R.id.recyclerView);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        Query query = note;
        FirestoreRecyclerOptions<Loyalty_card> options = new FirestoreRecyclerOptions.Builder<Loyalty_card>()
                .setQuery(query, Loyalty_card.class)
                .build();

        swipeRecyclerView.setOnItemClickListener(mItemClickListener);
        swipeRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        swipeRecyclerView.setOnItemMenuClickListener(mItemMenuClickListener);

        adapter = new CollectioncardAdapter(options);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        swipeRecyclerView.setLayoutManager(linearLayoutManager);
        //swipeRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        swipeRecyclerView.addItemDecoration(
                new DefaultItemDecoration(ContextCompat.getColor(getContext(), R.color.divider_color)));

        swipeRecyclerView.setHasFixedSize(true);
        swipeRecyclerView.setAdapter(adapter);

        //store的亂碼Id
        SharedPreferences storepref = this.getActivity().getSharedPreferences("save_storeId", MODE_PRIVATE);

//        adapter.setOnItemClickListener(new CollectioncardAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//                Loyalty_card loyalty_card = documentSnapshot.toObject(Loyalty_card.class);
//                String storeId = loyalty_card.getStore().trim();
//                storepref.edit()
//                        .putString("store_id", storeId)
//                        .commit();
//                //跳轉頁到MemberOverlookCoupon
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                MemberOverlookCoupon llf = new MemberOverlookCoupon();
//                ft.replace(R.id.fragment_container, llf);
//                ft.commit();
//            }
//        });
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

    /**
     * RecyclerView的Item点击监听。
     */
    private OnItemClickListener mItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            Toast.makeText(getContext(), "第" + position + "个", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * RecyclerView的Item中的Menu点击监听。
     */
    private OnItemMenuClickListener mItemMenuClickListener = new OnItemMenuClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getContext(), "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                adapter.myLoveItem(position);

            }
        }
    };

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            SwipeMenuItem addItem = new SwipeMenuItem(getContext()).setBackground(R.drawable.selector_heart)
                    .setImage(R.drawable.icons8_heart)
                    .setWidth(width)
                    .setHeight(height);
            swipeLeftMenu.addMenuItem(addItem); // 添加菜单到左侧。

//            SwipeMenuItem closeItem = new SwipeMenuItem(getContext()).setBackground(R.drawable.selector_red)
//                    .setImage(R.drawable.ic_close_55dp)
//                    .setWidth(width)
//                    .setHeight(height);
//            swipeRightMenu.addMenuItem(closeItem); // 添加菜单到右侧。
        }
    };
}