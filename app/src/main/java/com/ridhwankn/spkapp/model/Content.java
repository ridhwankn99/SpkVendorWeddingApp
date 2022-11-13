package com.ridhwankn.spkapp.model;

import com.ridhwankn.spkapp.model.bean.DetailVendorBean;

import java.io.Serializable;
import java.util.ArrayList;

public class Content implements Serializable {
    public ArrayList<DetailVendorBean> detailVendorBean;

    public ArrayList<DetailVendorBean> getDetailVendor() {
        return detailVendorBean;
    }

    public void setDetailVendor(ArrayList<DetailVendorBean> detailVendorBean) {
        this.detailVendorBean = detailVendorBean;
    }
}
