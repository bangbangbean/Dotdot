package com.example.dotdot;

import com.google.firebase.database.Exclude;

public class Store {
    private String name;
    private String phone;
    private String address;
    private String Threshold;
    private String documentId;
    Store store;

    public Store() {
    }

    public Store(String name,String phone,String Threshold){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.Threshold = Threshold;
    }
    @Exclude
    public String getDocumentId(){
        return documentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setThreshold(String threshold) {
        Threshold = threshold;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getThreshold(){return Threshold;}
}
