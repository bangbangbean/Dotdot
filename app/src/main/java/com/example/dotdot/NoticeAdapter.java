package com.example.dotdot;

import android.content.ClipData;
import android.content.ContentProvider;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.dotdot.recycleritemanim.ExpandableViewHoldersUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

public class NoticeAdapter extends FirestoreRecyclerAdapter<Note_store_noit ,NoticeAdapter.viewHolder> {

    private ExpandableViewHoldersUtil.KeepOneHolder<viewHolder> keepOne;
    public NoticeAdapter(@NonNull FirestoreRecyclerOptions<Note_store_noit> options  ){
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull NoticeAdapter.viewHolder viewHolder, int i, @NonNull Note_store_noit note_store_noit) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date轉string
        viewHolder.fav_title.setText(note_store_noit.getTitle());
        viewHolder.fav_time.setText(sdf.format(note_store_noit.getTime()));
        viewHolder.fav_contxt.setText(note_store_noit.getContxt());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference storeRef = db.collection("store");
        storeRef.document(note_store_noit.getStore()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Store store = documentSnapshot.toObject(Store.class);
                viewHolder.fav_storename.setText(store.getName());
            }
        });
        keepOne.bind(viewHolder , i);
        viewHolder.fav_title.setOnClickListener((view -> {
            keepOne.toggle(viewHolder);
        }));
        viewHolder.fav_storename.setOnClickListener((view -> {
            keepOne.toggle(viewHolder);
        }));

    }

    @NonNull
    @Override
    public NoticeAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_notice_item,
                parent, false);

        return new NoticeAdapter.viewHolder(v);
    }

    public class viewHolder extends ViewHolder implements ExpandableViewHoldersUtil.Expandable{
        private TextView fav_storename;
        private TextView fav_title;
        private TextView fav_time;
        private TextView fav_contxt;
        RelativeLayout relativeLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            fav_storename = itemView.findViewById(R.id.fav_storename);
            fav_title = itemView.findViewById(R.id.fav_title);
            fav_time = itemView.findViewById(R.id.fav_time);
            fav_contxt = itemView.findViewById(R.id.fav_contxt);
            relativeLayout = itemView.findViewById(R.id.childCard);

            keepOne = ExpandableViewHoldersUtil.getInstance().getKeepOneHolder();

            relativeLayout.setVisibility(View.GONE);
            relativeLayout.setAlpha(0);
        }

        @Override
        public View getExpandView() {
            return relativeLayout;
        }

        @Override
        public void doCustomAnim(boolean isOpen) {

        }
    }


}
