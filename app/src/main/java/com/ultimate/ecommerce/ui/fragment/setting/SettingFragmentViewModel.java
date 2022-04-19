package com.ultimate.ecommerce.ui.fragment.setting;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SettingFragmentViewModel extends BaseViewModel {

    @Inject
    public SettingFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    void getData(){
    }
}