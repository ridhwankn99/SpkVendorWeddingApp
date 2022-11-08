package com.ridhwankn.spkapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Content implements Serializable {
    public ArrayList<DetailVendor> detailVendor;

    public ArrayList<DetailVendor> getDetailVendor() {
        return detailVendor;
    }

    public void setDetailVendor(ArrayList<DetailVendor> detailVendor) {
        this.detailVendor = detailVendor;
    }
}
