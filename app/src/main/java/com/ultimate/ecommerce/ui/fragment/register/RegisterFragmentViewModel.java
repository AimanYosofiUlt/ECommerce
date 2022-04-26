package com.ultimate.ecommerce.ui.fragment.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.app.GeneralVariable;
import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.repos.setting.AppSettingRepo;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.add_user.AddUserResponse;
import com.ultimate.ecommerce.repository.server.response.add_user.UserResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class RegisterFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    @Inject
    AppSettingRepo settingRepo;

    MutableLiveData<ResponseState> responseMDL;

    @Inject
    public RegisterFragmentViewModel(@NonNull Application application) {
        super(application);
        responseMDL = new MutableLiveData<>();
    }

    public void registerUser(String userName, String userPhone, String userEmail, String userPassword) {
        userRepo.registerUser(userName, userPhone, userEmail, userPassword, new ResponsesCallBack<AddUserResponse>() {
            @Override
            public void onSuccess(AddUserResponse response) {
                GeneralVariable.tokenKey = response.getData().getTokenkey();
                settingRepo.updateTokenKey();
                UserResponse user = response.getData().getUser();
                updateUser(user.getId(), user.getCaps().getSubscriber());
                responseMDL.setValue(ResponseState.successState());
            }

            private void updateUser(int id, boolean isSubscriber) {
                User user = new User(id, userName, userPhone, userEmail, isSubscriber);
                userRepo.addUser(user);
            }

            @Override
            public void onFailure(String state, String msg) {
                responseMDL.setValue(ResponseState.failureState(state + "  " + msg));
            }
        });
    }
}