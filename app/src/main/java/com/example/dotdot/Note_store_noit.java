package com.example.dotdot;
import com.google.firebase.database.Exclude;
import java.util.Date;

public class Note_store_noit {
    private String title;
    private Date time;
    private String contxt;
    private String store;

    public Note_store_noit(){
        //empty constructor needed
    }

    public Note_store_noit(String title, Date time, String contxt, String store){
        this.title = title;
        this.time = time;
        this.contxt = contxt;
        this.store = store;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContxt(){return contxt;}

    public String getStore(){return store;}
}
