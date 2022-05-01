package com.ultimate.ecommerce.ui.fragment.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.app.GlobalVariable;
import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.add_user.UserData;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.login_user.LoginUserResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class LoginFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;
    MutableLiveData<ResponseState> responseStateMDL;

    @Inject
    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        responseStateMDL = new MutableLiveData<>();
    }

    public void login(String userKey, String userPassword) {
        userRepo.login(userKey, userPassword, new ResponsesCallBack<LoginUserResponse>() {
            @Override
            public void onSuccess(LoginUserResponse response) {
                GlobalVariable.tokenKey = response.getData().getTokenkey();
                UserData userData = response.getData().getUser().getData();
                addUser(userData, response.getData().getUser().getCaps().getSubscriber());
                responseStateMDL.setValue(ResponseState.successState());
            }

            private void addUser(UserData userData, boolean subscriber) {
                int id = Integer.parseInt(userData.getId());
                String name = userData.getDisplayName();
                String email = userData.getUserEmail();
                User user = new User(id, name, userKey, email, subscriber);
                userRepo.addUser(user);
            }

            @Override
            public void onFailure(String state, String msg) {
                responseStateMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}