package com.example.dotdot;

import com.google.firebase.database.Exclude;

public class Member {
    private String name;
    private String phone;
    private String password;
    private String documentId;
    Member member;

    public Member() {

    }
    public Member(String name,String password,String phone){
        this.name = name;
        this.phone = phone;
        this.password = password;
    }
    @Exclude
    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId){
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getPassword() {
        return password;
    }
}
