package com.ultimate.ecommerce.repository.repos.user;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.local.user.UserDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.add_user.AddUserData;
import com.ultimate.ecommerce.repository.server.response.add_user.AddUserResponse;
import com.ultimate.ecommerce.repository.server.response.add_user.UserData;
import com.ultimate.ecommerce.repository.server.response.add_user.UserResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_user_profile.GetUserProfileResponse;
import com.ultimate.ecommerce.repository.server.response.login_user.LoginUserResponse;
import com.ultimate.ecommerce.repository.server.response.update_password.UpdatePasswordResponse;

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
                AddUserData data = response.getData();
                User user = convertResponseToUser(data.getUser(), data.getTokenkey());
                setAppUser(user);
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(String state, String msg) {
                callBack.onFailure(state, msg);
            }
        });
    }

    public void setAppUser(User user) {
        AsyncTask.execute(() -> {
            userDao.deleteCurrentUser();
            userDao.insert(user);
        });
    }

    public LiveData<Boolean> checkUser() {
        return userDao.isUserLogin();
    }

    public void login(String userPhone, String userPassword, ResponsesCallBack<LoginUserResponse> callBack) {
        RequestBody request = BaseRequest.getLoginUserRequest(userPhone, userPassword);
        api.loginUser(request).enqueue(new ResponsesCallBack<LoginUserResponse>() {
            @Override
            public void onSuccess(LoginUserResponse response) {
                AddUserData data = response.getData();
                User user = convertResponseToUser(data.getUser(), data.getTokenkey());
                setAppUser(user);
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(String state, String msg) {
                callBack.onFailure(state, msg);
            }
        });
    }

    private User convertResponseToUser(UserResponse response, String tokenkey) {
        UserData userData = response.getData();
        String id = userData.getId();
        String name = userData.getDisplayName();
        String email = userData.getUserEmail();
        String phone = userData.getUserLogin();
        boolean isSubs = response.getCaps().getSubscriber();
        return new User(id, name, phone, email, tokenkey, isSubs);
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
        return userDao.getUserId();
    }

    public String getTokenKey() {
        return userDao.getTokenKey();
    }

    public String getUserPhone() {
        return userDao.getUserPhone();
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword, ResponsesCallBack<UpdatePasswordResponse> updatePasswordResponseResponsesCallBack) {

    }

    public User getUser() {
        return userDao.getUser();
    }
}
