package com.ridhwankn.spkapp.model.bean;

public class UserBean {
    private static UserBean mInstance = null;
    private String username;


    public static synchronized UserBean getInstance(){
        if (mInstance == null){
            mInstance = new UserBean();
        }
        return mInstance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
