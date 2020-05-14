package com.example.dotdot;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class test extends AppCompatActivity {
    //    private EditText nameinput;
//    private EditText passwordinput;
//    private EditText phoneinput;
//    private TextView getinfo;
//
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference accountRef = db.collection("Member").document();
//    private CollectionReference memRef = db.collection("Member");
//
//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//
//        nameinput = findViewById(R.id.name);
//        passwordinput = findViewById(R.id.password);
//        phoneinput = findViewById(R.id.inputStore);
//        getinfo = findViewById(R.id.getinfo);
    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
////        memRef.addSnapshotListener(this,new EventListener<QuerySnapshot>() {
////            @Override
////            public void onEvent(QuerySnapshot queryDocumentSnapshots,FirebaseFirestoreException e) {
////                if (e != null) {
////                    return;
////                }
////               String data = "";
////                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
////                    Member mem = documentSnapshot.toObject(Member.class);
////
////                    String name = mem.getName();
////                    String password = mem.getPassword();
////                    String phone = mem.getPhone();
////
////                    data += "name:" + name + "\npassword:" + password + "\nphone:" + phone + "\n\n" ;
////                }
////                getinfo.setText(data);
////            }
////        });
//    }
//
//    public void insertAccount(View v){
//        String name = nameinput.getText().toString();
//        String password = passwordinput.getText().toString();
//        String phone = phoneinput.getText().toString();
//
//        Member account = new Member(name,password,phone);
//        SharedPreferences session = getSharedPreferences("save_useraccount",MODE_PRIVATE);
//        SharedPreferences.Editor editor=session.edit();
//        String who = session.getString("user_id","目前沒人登入");
//
//        memRef.document(who).collection("record").add(account);
//    }
//    public void getAccount(View v){
//        SharedPreferences session = getSharedPreferences("save_useraccount",MODE_PRIVATE);
//        SharedPreferences.Editor editor=session.edit();
//        String who = session.getString("user_id","目前沒人登入");
//        memRef.document(who).collection("record").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        String data = "";
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                            Member mem = documentSnapshot.toObject(Member.class);
//                            String name = mem.getName();
////                            String password = mem.getPassword();
////                            String phone = mem.getPhone();
//                            data += "name:" + name  + "\n\n" ;
//                        }//+ "\npassword:" + password + "\nphone:" + phone
//                        getinfo.setText(data);
//                    }
//                });
//    }
//
}
