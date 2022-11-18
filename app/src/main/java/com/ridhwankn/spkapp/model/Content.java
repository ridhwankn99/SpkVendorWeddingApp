package com.ridhwankn.spkapp.model;

import com.ridhwankn.spkapp.model.bean.DetailVendorBean;

import java.io.Serializable;
import java.util.ArrayList;

public class Content implements Serializable {
    public ArrayList<DetailVendorBean> detailVendor;

    public ArrayList<DetailVendorBean> getDetailVendor() {
        return detailVendor;
    }

    public void setDetailVendor(ArrayList<DetailVendorBean> detailVendorBean) {
        this.detailVendor = detailVendorBean;
    }
}
