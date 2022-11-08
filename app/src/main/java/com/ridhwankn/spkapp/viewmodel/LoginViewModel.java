package com.ridhwankn.spkapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<Boolean> isSuccess = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isFailed =  new MutableLiveData<>(false);
}
