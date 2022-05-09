package com.ultimate.ecommerce.repository.repos.user;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.local.user.UserDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.add_user.AddUserResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_user_profile.GetUserProfileResponse;
import com.ultimate.ecommerce.repository.server.response.login_user.LoginUserResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class UserRepo extends BaseRepo {
    @Inject
    UserDao userDao;

    @Inject
    public UserRepo() {

    }

    public void registerUser(String userName, String userPhone, String userEmail, String userPassword, ResponsesCallBack<AddUserResponse> callBack) {
        RequestBody request = BaseRequest.getAddUserRequest(userName, userPhone, userEmail, userPassword);
        api.addUser(request).enqueue(new ResponsesCallBack<AddUserResponse>() {
            @Override
            public void onSuccess(AddUserResponse response) {
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(String state, String msg) {
                callBack.onFailure(state, msg);
            }
        });
    }

    public void addUser(User user) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
            }
        });
    }

    public LiveData<Boolean> checkUser() {
        return userDao.isUserLogin();
    }

    public void login(String userPhone, String userPassword, ResponsesCallBack<LoginUserResponse> callBack) {
        RequestBody request = BaseRequest.getLoginUserRequest(userPhone, userPassword);
        api.loginUser(request).enqueue(callBack);
    }

    public void getUserProfile(ResponsesCallBack<GetUserProfileResponse> callBack) {
        RequestBody request = BaseRequest.getGetUserProfileRequest(getUserId());
        api.getUserProfile(request).enqueue(new ResponsesCallBack<GetUserProfileResponse>() {
            @Override
            public void onSuccess(GetUserProfileResponse response) {
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(String state, String msg) {
                callBack.onFailure(state, msg);
            }
        });
    }

    private String getUserId() {
        return String.valueOf(userDao.getUserId());
    }

    public String getUserPhone() {
        return userDao.getUserPhone();
    }
}
