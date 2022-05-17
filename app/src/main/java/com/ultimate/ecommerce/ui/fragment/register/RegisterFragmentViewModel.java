package com.ultimate.ecommerce.ui.fragment.register;
import static com.ultimate.ecommerce.utilities.ValidateSt.EMAIL_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NAME_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NOT_AGREE_TERMS_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NOT_EMAIL_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;
import static com.ultimate.ecommerce.utilities.ValidateSt.PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.PHONE_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.SMALL_PASSWORD_ERROR;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.ultimate.ecommerce.app.GlobalVariable;
import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.repos.setting.AppSettingRepo;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.add_user.AddUserResponse;
import com.ultimate.ecommerce.repository.server.response.add_user.UserResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class RegisterFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    @Inject
    AppSettingRepo settingRepo;

    @Inject
    GoogleSignInClient googleClient;

    MutableLiveData<ResponseState> registerResponseMDL;
    MutableLiveData<ResponseState> validateResponseStateMDL;

    @Inject
    public RegisterFragmentViewModel(@NonNull Application application) {
        super(application);
        registerResponseMDL = new MutableLiveData<>();
        validateResponseStateMDL = new MutableLiveData<>();
    }

    public void validateRegisterUser(Context context, String name, String phone, String email, String password) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        if (name.trim().isEmpty()) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(NAME_EMPTY_FILED_ERROR));
                            return false;
                        }

                        String emailPattern = "[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if (email.trim().isEmpty()) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(EMAIL_EMPTY_FILED_ERROR));
                            return false;
                        } else if (!email.trim().matches(emailPattern)) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(NOT_EMAIL_ERROR));
                            return false;
                        }

                        if (password.trim().isEmpty()) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(PASSWORD_EMPTY_FILED_ERROR));
                            return false;
                        } else if (password.trim().length() < 8) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(SMALL_PASSWORD_ERROR));
                            return false;
                        }

                        if (phone.trim().isEmpty()) {
                            validateResponseStateMDL.setValue(ResponseState.failureState(PHONE_EMPTY_FILED_ERROR));
                            return false;
                        }

                        return true;
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        registerUser(name, email, password, phone);
                    }

                    @Override
                    public void onDisconnect() {
                        validateResponseStateMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    public void registerUser(String userName, String userPhone, String userEmail, String userPassword) {
        userRepo.registerUser(userName, userPhone, userEmail, userPassword, new ResponsesCallBack<AddUserResponse>() {
            @Override
            public void onSuccess(AddUserResponse response) {
                GlobalVariable.tokenKey = response.getData().getTokenkey();
                settingRepo.updateTokenKey();
                UserResponse user = response.getData().getUser();
                updateUser(user.getId(), user.getCaps().getSubscriber());
                registerResponseMDL.setValue(ResponseState.successState());
            }

            private void updateUser(int id, boolean isSubscriber) {
                User user = new User(id, userName, userPhone, userEmail, isSubscriber);
                userRepo.addUser(user);
            }

            @Override
            public void onFailure(String state, String msg) {
                registerResponseMDL.setValue(ResponseState.failureState(state + "  " + msg));
            }
        });
    }

    public void registerGoogleUser() {
        googleClient.silentSignIn().addOnSuccessListener(account -> {
            String id = account.getId();
            String name = account.getDisplayName();
            String email = account.getEmail();
            registerUser(name, email, email, id);
        }).addOnFailureListener(e -> {

        }).addOnCanceledListener(() -> {

        });
    }

    
}