package com.ridhwankn.spkapp.model;

import java.io.Serializable;

public class DetailVendor implements Serializable {
    public String nameVendor;
    public double numStar;
    public String city;
    public String aboutMe;
    public String address;
    public String location;
    public String service;
    public String image;

    public String getNameVendor() {
        return nameVendor;
    }

    public void setNameVendor(String nameVendor) {
        this.nameVendor = nameVendor;
    }

    public double getNumStar() {
        return numStar;
    }

    public void setNumStar(double numStar) {
        this.numStar = numStar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
