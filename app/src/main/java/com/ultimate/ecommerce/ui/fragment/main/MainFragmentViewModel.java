package com.ultimate.ecommerce.ui.fragment.main;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.logout.LogoutResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class MainFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> logoutResponseMDL;

    @Inject
    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        logoutResponseMDL = new MutableLiveData<>();
    }

    public void validateLogout(Context context) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        logout();
                    }

                    @Override
                    public void onDisconnect() {
                        logoutResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    public void logout() {
        userRepo.logout(new ResponsesCallBack<LogoutResponse>() {
            @Override
            public void onSuccess(LogoutResponse response) {
                logoutResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                logoutResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}
