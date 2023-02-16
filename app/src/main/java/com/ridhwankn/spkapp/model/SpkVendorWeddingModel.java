package com.ridhwankn.spkapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SpkVendorWeddingModel implements Parcelable {
    private String nameVendor;
    private String nameGedung;
    private double price;
    private double rating;
    private int MaxGuest;
    private int TotalInvitation;
    private int rasaMakanan;

    public SpkVendorWeddingModel(String nameVendor, String nameGedung, double price, double rating,
                                 int MaxGuest, int TotalInvitation, int rasaMakanan){
        this.nameVendor = nameVendor;
        this.nameGedung = nameGedung;
        this.price = price;
        this.rating = rating;
        this.MaxGuest = MaxGuest;
        this.TotalInvitation = TotalInvitation;
        this.rasaMakanan = rasaMakanan;

    }

    protected SpkVendorWeddingModel(Parcel in) {
        nameVendor = in.readString();
        nameGedung = in.readString();
        price = in.readDouble();
        rating = in.readDouble();
        MaxGuest = in.readInt();
        TotalInvitation = in.readInt();
        rasaMakanan = in.readInt();
    }

    public static final Creator<SpkVendorWeddingModel> CREATOR = new Creator<SpkVendorWeddingModel>() {
        @Override
        public SpkVendorWeddingModel createFromParcel(Parcel in) {
            return new SpkVendorWeddingModel(in);
        }

        @Override
        public SpkVendorWeddingModel[] newArray(int size) {
            return new SpkVendorWeddingModel[size];
        }
    };

    public String getNameVendor() {
        return nameVendor;
    }

    public void setNameVendor(String nameVendor) {
        this.nameVendor = nameVendor;
    }

    public String getNameGedung() {
        return nameGedung;
    }

    public void setNameGedung(String nameGedung) {
        this.nameGedung = nameGedung;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getMaxGuest() {
        return MaxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.MaxGuest = maxGuest;
    }

    public int getTotalInvitation() {
        return TotalInvitation;
    }

    public void setTotalInvitation(int totalInvitation) {
        this.TotalInvitation = totalInvitation;
    }

    public int getRasaMakanan() {
        return rasaMakanan;
    }

    public void setRasaMakanan(int rasaMakanan) {
        this.rasaMakanan = rasaMakanan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameVendor);
        dest.writeString(nameGedung);
        dest.writeDouble(price);
        dest.writeDouble(rating);
        dest.writeDouble(MaxGuest);
        dest.writeDouble(TotalInvitation);
        dest.writeInt(rasaMakanan);
    }
}
