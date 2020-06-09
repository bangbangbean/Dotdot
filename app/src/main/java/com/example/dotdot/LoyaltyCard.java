package com.example.dotdot;

import com.google.firebase.database.Exclude;

public class LoyaltyCard {
    private String points_owned;
    private String store;
    private Boolean favorite;
    private String dotGet;
    LoyaltyCard loyaltyCard;

    public LoyaltyCard(){

    }

    public LoyaltyCard(String points_owned, String store, Boolean favorite, String dotGet){
        this.points_owned = points_owned;
        this.store = store;
        this.favorite = favorite;
        this.dotGet = dotGet;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
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

    public String getDotGet() {
        return dotGet;
    }

    public void setDotGet(String dotGet) {
        this.dotGet = dotGet;
    }
}
