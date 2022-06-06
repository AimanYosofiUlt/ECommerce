package com.ultimate.ecommerce.repository.repos.user;

import android.os.AsyncTask;
import android.util.Log;

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
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldsResponse;
import com.ultimate.ecommerce.repository.server.response.get_user_profile.GetUserProfileResponse;
import com.ultimate.ecommerce.repository.server.response.login_user.LoginUserResponse;
import com.ultimate.ecommerce.repository.server.response.logout.LogoutResponse;
import com.ultimate.ecommerce.repository.server.response.payment_methods.PaymentMethodsResponse;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsResponse;
import com.ultimate.ecommerce.repository.server.response.update_password.UpdatePasswordResponse;
import com.ultimate.ecommerce.repository.server.response.update_profile.UpdateProfileData;
import com.ultimate.ecommerce.repository.server.response.update_profile.UpdateProfileResponse;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.change_password.EPassword;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.profile_edit.Profile;

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

    public LiveData<Boolean> checkUserLogin() {
        return userDao.isUserLogin();
    }

    public boolean isUserLogin() {
        return userDao.isUserLoginCheck();
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
        UserData userData = response.getUserData();
        String id = userData.getId();
        String name = userData.getDisplayName();
        String email = userData.getUserEmail();
        String phone = userData.getUserLogin();
        boolean isSubs = response.getCaps().getSubscriber();
        return new User(id, name, phone, email, tokenkey);
    }

    public void getUserProfile(ResponsesCallBack<GetUserProfileResponse> callBack) {
        AsyncTask.execute(() -> {
            String tokenKey = userDao.getTokenKey();
            RequestBody request = BaseRequest.getGetUserProfileRequest(userDao.getUserId());
            api.getUserProfile(request, tokenKey).enqueue(new ResponsesCallBack<GetUserProfileResponse>() {
                @Override
                public void onSuccess(GetUserProfileResponse response) {
                    AsyncTask.execute(() -> {
                        UserData userData = response.getData().getUserResponse().getUserData();
                        User user = convertUserDataToUser(userData);
                        setAppUser(user);
                    });
                    callBack.onSuccess(response);
                }

                private User convertUserDataToUser(UserData userData) {
                    String tokenKey = userDao.getTokenKey();
                    return new User(userData.getId(), userData.getDisplayName()
                            , userData.getUserLogin(), userData.getUserEmail(), tokenKey);
                }

                @Override
                public void onFailure(String state, String msg) {
                    callBack.onFailure(state, msg);
                }
            });
        });
    }


    public String getTokenKey() {
        return userDao.getTokenKey();
    }

    public String getUserPhone() {
        return userDao.getUserPhone();
    }

    public void changePassword(EPassword ePassword, ResponsesCallBack<UpdatePasswordResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getUpdatePasswordRequest(userDao.getUserId(), ePassword);
            String tokenKey = userDao.getTokenKey();
            api.updatePassword(request, tokenKey).enqueue(callBack);
        });
    }

    public User getUser() {
        return userDao.getUser();
    }

    public void getUserAddress(ResponsesCallBack<GetAddressFieldsResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getOrdersRequest(userDao.getUserId());
            String tokenKey = userDao.getTokenKey();
            api.getAddressFields(request, tokenKey).enqueue(callBack);
        });
    }

    public void getAddressFields(ResponsesCallBack<GetAddressFieldsResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getRequestByUserID(userDao.getUserId());
            String tokenKey = userDao.getTokenKey();
            api.getAddressFields(request, tokenKey).enqueue(callBack);
        });
    }

    public void getShipmentMethods(ResponsesCallBack<ShippingMethodsResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getRequestByUserID(userDao.getUserId());
            String tokenKey = userDao.getTokenKey();
            api.shippingMethods(request, tokenKey).enqueue(callBack);
        });
    }

    public void getPaymentMethods(ResponsesCallBack<PaymentMethodsResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getRequestByUserID(userDao.getUserId());
            String tokenKey = userDao.getTokenKey();
            api.paymentMethods(request, tokenKey).enqueue(callBack);
        });
    }

    public LiveData<User> getUserProfile() {
        return userDao.getUserProfile();
    }

    public void updateProfile(Profile profile, ResponsesCallBack<UpdateProfileResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getUpdateUserProfileRequest(userDao.getUserId(), profile);
            String tokenKey = userDao.getTokenKey();
            api.updateProfile(request, tokenKey).enqueue(new ResponsesCallBack<UpdateProfileResponse>() {
                @Override
                public void onSuccess(UpdateProfileResponse response) {
                    UpdateProfileData data = response.getData();
                    AsyncTask.execute(() -> userDao.updateProfile(data.getName(), profile.getEmail()));
                    callBack.onSuccess(response);
                }

                @Override
                public void onFailure(String state, String msg) {
                    callBack.onFailure(state, msg);
                }
            });
        });
    }

    public void logout(ResponsesCallBack<LogoutResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getRequestByUserID(userDao.getUserId());
            api.logout(request).enqueue(new ResponsesCallBack<LogoutResponse>() {
                @Override
                public void onSuccess(LogoutResponse response) {
                    Log.d("TAG", "onSuccess: 29382l");
                    AsyncTask.execute(() -> userDao.deleteCurrentUser());
                    callBack.onSuccess(response);
                }

                @Override
                public void onFailure(String state, String msg) {
                    callBack.onFailure(state, msg);
                }
            });
        });
    }


}
