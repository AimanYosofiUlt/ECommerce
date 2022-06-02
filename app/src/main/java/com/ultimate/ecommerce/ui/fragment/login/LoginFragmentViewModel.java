package com.ultimate.ecommerce.ui.fragment.login;

import static com.ultimate.ecommerce.utilities.ValidateSt.PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.PHONE_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.SMALL_PASSWORD_ERROR;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.login_user.LoginUserResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class LoginFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;
    MutableLiveData<ResponseState> responseStateMDL;
    MutableLiveData<ResponseState> validateResponseStateMDL;

    @Inject
    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        responseStateMDL = new MutableLiveData<>();
        validateResponseStateMDL = new MutableLiveData<>();
    }

    public void validateLogin(Context context, String phone, String password) {

        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        if (phone.trim().isEmpty()) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(PHONE_EMPTY_FILED_ERROR));
                            return false;
                        }

                        if (password.trim().isEmpty()) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(PASSWORD_EMPTY_FILED_ERROR));
                            return false;
                        } else if (password.trim().length() < 8) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(SMALL_PASSWORD_ERROR));
                            return false;
                        }

                        return true;
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        login(phone.trim(), password.trim());
                    }

                    @Override
                    public void onDisconnect() {
                        responseStateMDL.setValue(ResponseState.failureState(context.getString(R.string.no_internet_connection)));
                    }
                });
    }

    public void login(String userKey, String userPassword) {
        userRepo.login(userKey, userPassword, new ResponsesCallBack<LoginUserResponse>() {
            @Override
            public void onSuccess(LoginUserResponse response) {
                responseStateMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                responseStateMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

}