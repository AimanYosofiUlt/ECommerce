package com.ultimate.ecommerce.ui.fragment.profile.dialogs.change_password;

import static com.ultimate.ecommerce.utilities.ValidateSt.CONFIRM_NEW_PASSWORD_SAME_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.CONFIRM_PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NEW_PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;
import static com.ultimate.ecommerce.utilities.ValidateSt.PASSWORD_EMPTY_FILED_ERROR;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.update_password.UpdatePasswordResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class ChangePasswordDialogViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> validateChangePasswordMDL;

    @Inject
    public ChangePasswordDialogViewModel(@NonNull Application application) {
        super(application);
        validateChangePasswordMDL = new MutableLiveData<>();
    }

    public void validateChangePassword(Context context, String currentPassword, String newPassword, String confirmPassword) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        if (currentPassword.isEmpty()) {
                            validateChangePasswordMDL.setValue(ResponseState.failureState(PASSWORD_EMPTY_FILED_ERROR));
                            return false;
                        }

                        if (newPassword.isEmpty()) {
                            validateChangePasswordMDL.setValue(ResponseState.failureState(NEW_PASSWORD_EMPTY_FILED_ERROR));
                            return false;
                        }

                        if (confirmPassword.isEmpty()) {
                            validateChangePasswordMDL.setValue(ResponseState.failureState(CONFIRM_PASSWORD_EMPTY_FILED_ERROR));
                            return false;
                        }

                        if (!confirmPassword.equals(newPassword)) {
                            validateChangePasswordMDL.setValue(ResponseState.failureState(CONFIRM_NEW_PASSWORD_SAME_ERROR));
                            return false;
                        }

                        return true;
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        changePassword(currentPassword, newPassword, confirmPassword);
                    }

                    @Override
                    public void onDisconnect() {
                        validateChangePasswordMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        userRepo.changePassword(currentPassword,newPassword,confirmPassword, new ResponsesCallBack<UpdatePasswordResponse>() {
            @Override
            public void onSuccess(UpdatePasswordResponse response) {

            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });
    }
}
