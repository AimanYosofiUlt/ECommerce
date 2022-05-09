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

    MutableLiveData<User> userMDL;
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
                boolean subscriber = response.getData().getCaps().getSubscriber();
                UserData userData = response.getData().getData();
                User user = convertResponseToUser(userData, subscriber);
                userMDL.setValue(user);
            }

            private User convertResponseToUser(UserData userData, boolean subscriber) {
                Integer id = Integer.valueOf(userData.getId());
                String name = userData.getDisplayName();
                String email = userData.getUserEmail();
                String phone = userRepo.getUserPhone();
                return new User(id, name, phone, email, subscriber);
            }

            @Override
            public void onFailure(String state, String msg) {
                responseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}