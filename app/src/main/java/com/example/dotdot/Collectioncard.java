package com.example.dotdot;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Collectioncard extends AppCompatActivity {
    private TextView title;
    private TextView points;
    private TextView dot1;
    private TextView dot2;
    private TextView dot3;
    private TextView dot4;
    private TextView dot5;
    private TextView dot6;
    private TextView dot7;
    private TextView dot8;
    private TextView dot9;
    private TextView dot10;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference storemem = db.collection("store");
    private DocumentReference note = db.collection("Member").document("BFyN264km5dWWtTPYivZ")
            .collection("loyalty_card").document("3fVoEdfNqgmBlwjgAFMQ");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dot1 = (TextView) findViewById(R.id.Dot1);
        dot2 = (TextView) findViewById(R.id.Dot2);
        dot3 = (TextView) findViewById(R.id.Dot3);
        dot4 = (TextView) findViewById(R.id.Dot4);
        dot5 = (TextView) findViewById(R.id.Dot5);
        dot6 = (TextView) findViewById(R.id.Dot6);
        dot7 = (TextView) findViewById(R.id.Dot7);
        dot8 = (TextView) findViewById(R.id.Dot8);
        dot9 = (TextView) findViewById(R.id.Dot9);
        dot10 = (TextView) findViewById(R.id.Dot10);
        title = (TextView) findViewById(R.id.Title);
        points = (TextView) findViewById(R.id.points);

    }


    protected void onStart() {
        super.onStart();
        note.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Loyalty_card mem = documentSnapshot.toObject(Loyalty_card.class);
                    String qq = mem.getPoints_owned();
                    int qqq = Integer.valueOf(qq);
                    points.setText(qq);

                    if (qqq == 1) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);

                    } else if (qqq == 2) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);

                    } else if (qqq == 3) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);
                        dot3.setBackgroundDrawable(dot_pink);

                    } else if (qqq == 4) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);
                        dot3.setBackgroundDrawable(dot_pink);
                        dot4.setBackgroundDrawable(dot_pink);

                    } else if (qqq == 5) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);
                        dot3.setBackgroundDrawable(dot_pink);
                        dot4.setBackgroundDrawable(dot_pink);
                        dot5.setBackgroundDrawable(dot_pink);

                    } else if (qqq == 6) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);
                        dot3.setBackgroundDrawable(dot_pink);
                        dot4.setBackgroundDrawable(dot_pink);
                        dot5.setBackgroundDrawable(dot_pink);
                        dot6.setBackgroundDrawable(dot_pink);

                    } else if (qqq == 7) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);
                        dot3.setBackgroundDrawable(dot_pink);
                        dot4.setBackgroundDrawable(dot_pink);
                        dot5.setBackgroundDrawable(dot_pink);
                        dot6.setBackgroundDrawable(dot_pink);
                        dot7.setBackgroundDrawable(dot_pink);
                    } else if (qqq == 8) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);
                        dot3.setBackgroundDrawable(dot_pink);
                        dot4.setBackgroundDrawable(dot_pink);
                        dot5.setBackgroundDrawable(dot_pink);
                        dot6.setBackgroundDrawable(dot_pink);
                        dot7.setBackgroundDrawable(dot_pink);
                        dot8.setBackgroundDrawable(dot_pink);
                    } else if (qqq == 9) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);
                        dot3.setBackgroundDrawable(dot_pink);
                        dot4.setBackgroundDrawable(dot_pink);
                        dot5.setBackgroundDrawable(dot_pink);
                        dot6.setBackgroundDrawable(dot_pink);
                        dot7.setBackgroundDrawable(dot_pink);
                        dot8.setBackgroundDrawable(dot_pink);
                        dot9.setBackgroundDrawable(dot_pink);
                    } else if (qqq == 10) {
                        Drawable dot_pink = Collectioncard.this.getResources().getDrawable(R.drawable.pink_dot);
                        dot1.setBackgroundDrawable(dot_pink);
                        dot2.setBackgroundDrawable(dot_pink);
                        dot3.setBackgroundDrawable(dot_pink);
                        dot4.setBackgroundDrawable(dot_pink);
                        dot5.setBackgroundDrawable(dot_pink);
                        dot6.setBackgroundDrawable(dot_pink);
                        dot7.setBackgroundDrawable(dot_pink);
                        dot8.setBackgroundDrawable(dot_pink);
                        dot9.setBackgroundDrawable(dot_pink);
                        dot10.setBackgroundDrawable(dot_pink);
                    }
                }
            }
        });


        storemem.whereEqualTo("name", "椒麻雞大王")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Store mem = documentSnapshot.toObject(Store.class);
                            String name = mem.getName();
                            title.setText(name);
                        }


                    }
                });


    }


}

