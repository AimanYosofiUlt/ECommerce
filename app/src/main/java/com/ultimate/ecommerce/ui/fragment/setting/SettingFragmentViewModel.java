package com.ultimate.ecommerce.ui.fragment.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.repos.configuration.ConfigRepo;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SettingFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    LiveData<Boolean> userIsLoginMDL;

    @Inject
    public SettingFragmentViewModel(@NonNull Application application,UserRepo userRepo) {
        super(application);
        this.userRepo = userRepo;
        userIsLoginMDL = userRepo.checkUser();
    }
}