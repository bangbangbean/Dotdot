package com.example.dotdot;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class MapInforWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference accountRef = db.collection("Member").document();
    private CollectionReference memRef = db.collection("Member");
    private TextView getinfo;
    private Context context;

    public MapInforWindowAdapter(Context context){
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getInfoWindow(Marker marker) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_infor_map,null);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(marker.getTitle());
       // tvTitle.setCompoundDrawablesWithIntrinsicBounds(null,
                //null,null,context.getResources().getDrawable(R.drawable.c, null));

        tvTitle.setCompoundDrawablePadding(10);


        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {

        return null;
    }


}
