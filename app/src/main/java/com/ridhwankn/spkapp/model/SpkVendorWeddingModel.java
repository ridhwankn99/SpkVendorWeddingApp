package com.ridhwankn.spkapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SpkVendorWeddingModel implements Parcelable {
    private String nameVendor;
    private String nameGedung;
    private double price;
    private double rating;
    private double luasGedung;
    private double luasParkir;
    private int rasaMakanan;

    public SpkVendorWeddingModel(String nameVendor, String nameGedung, double price, double rating,
                                 double luasGedung, double luasParkir, int rasaMakanan){
        this.nameVendor = nameVendor;
        this.nameGedung = nameGedung;
        this.price = price;
        this.rating = rating;
        this.luasGedung = luasGedung;
        this.luasParkir = luasParkir;
        this.rasaMakanan = rasaMakanan;

    }

    protected SpkVendorWeddingModel(Parcel in) {
        nameVendor = in.readString();
        nameGedung = in.readString();
        price = in.readDouble();
        rating = in.readDouble();
        luasGedung = in.readDouble();
        luasParkir = in.readDouble();
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

    public double getLuasGedung() {
        return luasGedung;
    }

    public void setLuasGedung(double luasGedung) {
        this.luasGedung = luasGedung;
    }

    public double getLuasParkir() {
        return luasParkir;
    }

    public void setLuasParkir(double luasParkir) {
        this.luasParkir = luasParkir;
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
        dest.writeDouble(luasGedung);
        dest.writeDouble(luasParkir);
        dest.writeInt(rasaMakanan);
    }
}
