package com.example.dotdot;

import android.content.SharedPreferences;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotdot.recycleritemanim.ExpandableViewHoldersUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.internal.DiskLruCache;

import java.text.SimpleDateFormat;
import java.util.Collection;

import static android.content.Context.MODE_PRIVATE;

public class StoreNotiSend_Adapter extends FirestoreRecyclerAdapter<Note_store_noit, StoreNotiSend_Adapter.viewHolder> {

    private ExpandableViewHoldersUtil.KeepOneHolder<viewHolder> keepOne;

    public StoreNotiSend_Adapter(@NonNull FirestoreRecyclerOptions<Note_store_noit> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StoreNotiSend_Adapter.viewHolder viewHolder, int i, @NonNull Note_store_noit note_store_noit) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date轉string
        viewHolder.notiTitle.setText(note_store_noit.getTitle());
        viewHolder.notiTime.setText(sdf.format(note_store_noit.getTime()));
        viewHolder.contentTv.setText(note_store_noit.getContxt());

        keepOne.bind(viewHolder, i);

        viewHolder.lvArrorwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keepOne.toggle(viewHolder);
            }
        });
    }

    @NonNull
    @Override
    public StoreNotiSend_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_notification_item,
                                parent, false);
                        return new viewHolder(v);
    }

    public void deleteItem(int position){
        //固定Session(店家)---------------------------------------
//        SharedPreferences session = getSharedPreferences("save_useraccount", MODE_PRIVATE);
//        final SharedPreferences.Editor editor = session.edit();
//        editor.putString("user_id", "nQnT8AAt4NYIRYZFZfAR"); //椒麻雞大王
//        editor.commit();
//        who = session.getString("user_id","目前沒人登入");

        //------------------------------------------------------------

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference stoRef = db.collection("store").document("nQnT8AAt4NYIRYZFZfAR")
                .collection("Notice");
        CollectionReference stoNoRef = db.collection("Notice_store");

        //刪除所要刪除的通知---------------------------------------------
        String id = getSnapshots().getSnapshot(position).getId();

        stoRef.document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Note_store_noit noti = documentSnapshot.toObject(Note_store_noit.class);
                        String store = noti.getStore();
                        String title = noti.getTitle();

                        stoNoRef.whereEqualTo("store" , store)
                                .whereEqualTo("title", title)
                                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    stoNoRef.document(documentSnapshot.getId()).delete();
                                    stoRef.document(id).delete();//刪除store資料庫的Notice文件
                                }
                            }
                        });

                    }
                });

    }

    public class viewHolder extends RecyclerView.ViewHolder implements ExpandableViewHoldersUtil.Expandable{
        private TextView notiTitle;
        private TextView notiTime;

        LinearLayout lvArrorwBtn;
        ImageView arrowImage;
        LinearLayout lvLinearlayout;
        TextView contentTv;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            notiTitle = itemView.findViewById(R.id.notiTitle);
            notiTime = itemView.findViewById(R.id.notiTime);

            lvArrorwBtn = itemView.findViewById(R.id.item_user_concern_arrow);
            lvLinearlayout = itemView.findViewById(R.id.item_user_concern_link_layout);
            arrowImage = itemView.findViewById(R.id.item_user_concern_arrow_image);
            contentTv = itemView.findViewById(R.id.item_user_concern_link_text);

            keepOne = ExpandableViewHoldersUtil.getInstance().getKeepOneHolder();

            lvLinearlayout.setVisibility(View.GONE);
            lvLinearlayout.setAlpha(0);
        }

        @Override
        public View getExpandView() {
            return lvLinearlayout;
        }

        @Override
        public void doCustomAnim(boolean isOpen) {
            if (isOpen) {
                ExpandableViewHoldersUtil.getInstance().rotateExpandIcon(arrowImage, 180, 0);
            } else {
                ExpandableViewHoldersUtil.getInstance().rotateExpandIcon(arrowImage, 0, 180);
            }
        }
    }
}
