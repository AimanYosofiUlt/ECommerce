package com.ultimate.ecommerce.ui.fragment.setting;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SettingFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    LiveData<Boolean> userIsLoginLiveData;
    MutableLiveData<User> userMDL;

    @Inject
    public SettingFragmentViewModel(@NonNull Application application, UserRepo userRepo) {
        super(application);
        this.userRepo = userRepo;
        userIsLoginLiveData = userRepo.checkUserLogin();
        userMDL = new MutableLiveData<>();
    }

    public void getCurrentUser() {
        AsyncTask.execute(() -> userMDL.postValue(userRepo.getUser()));
    }
}