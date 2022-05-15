package com.ultimate.ecommerce.ui.fragment.login;

import android.app.Application;
import android.content.Context;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.GlobalVariable;
import com.ultimate.ecommerce.databinding.FragmentLoginBinding;
import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.add_user.UserData;
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
    MutableLiveData<ValidateMessage> validateMessageMDL;

    @Inject
    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        responseStateMDL = new MutableLiveData<>();
        validateMessageMDL = new MutableLiveData<>();
    }

    public void validateLoginEd(FragmentLoginBinding bd, String phone, String password) {
        Context context = bd.getRoot().getContext();

        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        if (phone.isEmpty()) {
                            observeValidateMessage(bd.phoneED, R.string.empty_filed);
                            return false;
                        }

                        if (password.isEmpty()) {
                            observeValidateMessage(bd.passwordED, R.string.empty_filed);
                            return false;
                        } else if (password.length() < 4) {
                            observeValidateMessage(bd.passwordED, R.string.small_password);
                            return false;
                        }
                        return true;
                    }

                    private void observeValidateMessage(EditText editText, int R_id) {
                        String message = context.getString(R_id);
                        ValidateMessage validateMessage = new ValidateMessage(editText, message);
                        validateMessageMDL.setValue(validateMessage);
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        login(phone, password);
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

    public void login2(String name) {
        userRepo.login2(name, new ResponsesCallBack<LoginUserResponse>() {
            @Override
            public void onSuccess(LoginUserResponse response) {

            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });
    }
}