package com.ridhwankn.spkapp.model.bean;

import android.os.Parcel;

import java.io.Serializable;

public class SpkVendorWeddingBean implements Serializable {
    private String vendorName;
    private String venue;
    private String price;
    private String rating;
    private String maximumGuests;
    private String invitation;
    private String tasteFood;
    private String uid;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMaximumGuests() {
        return maximumGuests;
    }

    public void setMaximumGuests(String maximumGuests) {
        this.maximumGuests = maximumGuests;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public String getTasteFood() {
        return tasteFood;
    }

    public void setTasteFood(String tasteFood) {
        this.tasteFood = tasteFood;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
