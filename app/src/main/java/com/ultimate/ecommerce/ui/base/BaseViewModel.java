package com.ultimate.ecommerce.ui.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.logout.LogoutResponse;

import javax.inject.Inject;

public class BaseViewModel extends AndroidViewModel {

    @Inject
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
