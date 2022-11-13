package com.ridhwankn.spkapp.model;

public class SpkVendorWeddingModel {
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
}
