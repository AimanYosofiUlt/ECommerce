package com.ultimate.ecommerce.ui.fragment.profile;

import static com.ultimate.ecommerce.utilities.ValidateSt.CONFIRM_NEW_PASSWORD_NO_SAME_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.CONFIRM_PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NEW_PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;
import static com.ultimate.ecommerce.utilities.ValidateSt.PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.SMALL_NEW_PASSWORD_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.WRONG_PASSWORD_ERROR;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.add_user.UserData;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_user_profile.GetUserProfileResponse;
import com.ultimate.ecommerce.repository.server.response.update_password.UpdatePasswordResponse;
import com.ultimate.ecommerce.repository.server.response.update_profile.UpdateProfileResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.change_password.EPassword;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.profile_edit.Profile;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class ProfileFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    LiveData<User> profileLiveData;
    MutableLiveData<ResponseState> getUserProfileResponseMDL;
    MutableLiveData<ResponseState> validateChangeResponseMDL;
    MutableLiveData<ResponseState> changePasswordResponseMDL;
    MutableLiveData<ResponseState> validateProfileEditResponseMDL;
    MutableLiveData<ResponseState> updateProfileResponseMDL;

    @Inject
    public ProfileFragmentViewModel(@NonNull Application application, UserRepo userRepo) {
        super(application);
        profileLiveData = userRepo.getUserProfile();
        getUserProfileResponseMDL = new MutableLiveData<>();
        validateChangeResponseMDL = new MutableLiveData<>();
        changePasswordResponseMDL = new MutableLiveData<>();
        validateProfileEditResponseMDL = new MutableLiveData<>();
        updateProfileResponseMDL = new MutableLiveData<>();
    }

    public void validateGetProfile(Context requireContext) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(requireContext, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        getUserProfile();
                    }

                    @Override
                    public void onDisconnect() {
                        getUserProfileResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    public void getUserProfile() {
        userRepo.getUserProfile(new ResponsesCallBack<GetUserProfileResponse>() {
            @Override
            public void onSuccess(GetUserProfileResponse response) {
                getUserProfileResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getUserProfileResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    public void validateChangePassword(Context requireContext, EPassword ePassword) {
        AsyncTask.execute(() -> StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        String currentPassword = ePassword.getCurrentPassword();
                        if (currentPassword.trim().isEmpty()) {
                            validateChangeResponseMDL.postValue(ResponseState.failureState(PASSWORD_EMPTY_FILED_ERROR));
                            return false;
                        }

                        String newPassword = ePassword.getNewPassword();
                        if (newPassword.trim().isEmpty()) {
                            validateChangeResponseMDL.postValue(ResponseState.failureState(NEW_PASSWORD_EMPTY_FILED_ERROR));
                            return false;
                        } else if (newPassword.trim().length() < 8) {
                            validateChangeResponseMDL.postValue(ResponseState.failureState(SMALL_NEW_PASSWORD_FILED_ERROR));
                            return false;
                        }

                        if (ePassword.getConfirmPassword().trim().isEmpty()) {
                            validateChangeResponseMDL.postValue(ResponseState.failureState(CONFIRM_PASSWORD_EMPTY_FILED_ERROR));
                            return false;
                        } else if (!ePassword.getConfirmPassword().trim().equals(newPassword)) {
                            validateChangeResponseMDL.postValue(ResponseState.failureState(CONFIRM_NEW_PASSWORD_NO_SAME_ERROR));
                            return false;
                        }
                        return true;
                    }
                })
                .checkNetwork(requireContext, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        changePassword(ePassword);
                    }

                    @Override
                    public void onDisconnect() {
                        validateChangeResponseMDL.postValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                }));
    }

    private void changePassword(EPassword ePassword) {
        userRepo.changePassword(ePassword, new ResponsesCallBack<UpdatePasswordResponse>() {
            @Override
            public void onSuccess(UpdatePasswordResponse response) {
                changePasswordResponseMDL.postValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                changePasswordResponseMDL.postValue(ResponseState.failureState(msg));
            }
        });
    }

    public void validateUpdateProfile(Context requireContext, Profile profile) {
        AsyncTask.execute(() -> StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        if (profile.getEmail().trim().isEmpty()) {
                            validateProfileEditResponseMDL.postValue(ResponseState.failureState(ValidateSt.EMAIL_EMPTY_FILED_ERROR));
                            return false;
                        }
                        return true;
                    }
                })
                .checkNetwork(requireContext, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        updateProfile(profile);
                    }

                    @Override
                    public void onDisconnect() {
                        validateProfileEditResponseMDL.postValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                }));
    }

    private void updateProfile(Profile profile) {
        userRepo.updateProfile(profile, new ResponsesCallBack<UpdateProfileResponse>() {
            @Override
            public void onSuccess(UpdateProfileResponse response) {
                updateProfileResponseMDL.postValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                updateProfileResponseMDL.postValue(ResponseState.failureState(msg));
            }
        });
    }


}