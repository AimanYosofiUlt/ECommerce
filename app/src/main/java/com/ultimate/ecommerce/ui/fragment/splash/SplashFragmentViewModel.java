package com.ultimate.ecommerce.ui.fragment.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SplashFragmentViewModel extends BaseViewModel {

    @Inject
    public SplashFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    void getData(){
    }
}
