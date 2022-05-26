package com.ultimate.ecommerce.ui.fragment.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.add_user.UserData;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_user_profile.GetUserProfileResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class ProfileFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    MutableLiveData<UserData> userMDL;
    MutableLiveData<ResponseState> responseMDL;

    @Inject
    public ProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        userMDL = new MutableLiveData<>();
        responseMDL = new MutableLiveData<>();
    }

    public void getUserProfile() {
        userRepo.getUserProfile(new ResponsesCallBack<GetUserProfileResponse>() {
            @Override
            public void onSuccess(GetUserProfileResponse response) {
                UserData userData = response.getData().getData();
                userMDL.setValue(userData);
            }

            @Override
            public void onFailure(String state, String msg) {
                responseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}