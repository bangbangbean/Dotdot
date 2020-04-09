package com.example.dotdot;

import com.google.firebase.database.Exclude;

public class LoyaltyCard {
    private String favorite;
    private String points_owned;
    private String store;
    private String documentId;
    LoyaltyCard loyaltyCard;

    public LoyaltyCard(){

    }

    public LoyaltyCard(String points_owned, String store){
        this.points_owned = points_owned;
        this.store = store;
    }

    @Exclude
    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getPoints_owned() {
        return points_owned;
    }

    public void setPoints_owned(String points_owned) {
        this.points_owned = points_owned;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
