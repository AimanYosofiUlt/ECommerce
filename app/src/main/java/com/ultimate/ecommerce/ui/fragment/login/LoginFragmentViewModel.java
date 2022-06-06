package com.ultimate.ecommerce.ui.fragment.login;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.auth.Auth;
import com.ultimate.ecommerce.repository.repos.auth.AuthRepo;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_auth.Field;
import com.ultimate.ecommerce.repository.server.response.login_user.LoginUserResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.login.views.auth_edittext.AuthEditResponseState;
import com.ultimate.ecommerce.ui.fragment.login.views.auth_edittext.AuthEdittext;
import com.ultimate.ecommerce.utilities.AuthSt;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LoginFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    @Inject
    AuthRepo authRepo;

    MutableLiveData<ResponseState> responseStateMDL;
    MutableLiveData<AuthEditResponseState> validateResponseStateMDL;
    MutableLiveData<List<Field>> authsMDL;

    @Inject
    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        responseStateMDL = new MutableLiveData<>();
        validateResponseStateMDL = new MutableLiveData<>();
        authsMDL = new MutableLiveData<>();
    }

    public void validateLogin(Context context, ArrayList<AuthEdittext> fieldList) {

        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        // todo how to handle multiple field with request need only the userfield and password
                        boolean hasNoValidation = true;
                        for (int i = 0; i < fieldList.size(); i++) {
                            String validation = fieldList.get(i).getValidation();
                            hasNoValidation = validation.equals(ValidateSt.NO_VALIDATION);

                            if (!hasNoValidation) {
                                AuthEditResponseState responseState = AuthEditResponseState.failureState(i, validation);
                                validateResponseStateMDL.setValue(responseState);
                                break;
                            }
                        }

                        return hasNoValidation;
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        LoginData data = getData(fieldList);
                        if (data.hasEmpty()) {
                            AuthEditResponseState responseState = AuthEditResponseState.failureState(0, ValidateSt.FILED_NOT_STRUCTURE_WELL_ERROR);
                            validateResponseStateMDL.postValue(responseState);
                            return;
                        }

                        login(data.getUserField().trim(), data.getPassword().trim());
                    }

                    private LoginData getData(ArrayList<AuthEdittext> fieldList) {
                        LoginData data = new LoginData();
                        for (AuthEdittext authEdittext : fieldList) {
                            boolean isPassword = authEdittext.getField().getName().equalsIgnoreCase(AuthSt.PASSWORD);
                            if (isPassword)
                                data.setPassword(authEdittext.getText());
                            else
                                data.setUserField(authEdittext.getText());
                        }
                        return data;
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

    public void initLoginScreenAuth() {
        AsyncTask.execute(() -> {
            String jsonData = authRepo.getScreen(Auth.LOGIN);
            List<Field> tempList = convertJsonToList(jsonData);
            List<Field> fields = getUnHiddenFields(tempList);
            authsMDL.postValue(fields);
        });
    }

    private List<Field> getUnHiddenFields(List<Field> tempList) {
        List<Field> fields = new ArrayList<>();
        for (Field field : tempList) {
            if (!field.isHidden())
                fields.add(field);
        }
        return fields;
    }

    private List<Field> convertJsonToList(String jsonData) {
        Type listType = new TypeToken<List<Field>>() {
        }.getType();
        return new Gson().fromJson(jsonData, listType);
    }

    static class LoginData {
        String userField;
        String password;

        public void setUserField(String userField) {
            this.userField = userField;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserField() {
            return userField;
        }

        public String getPassword() {
            return password;
        }

        public boolean hasEmpty() {
            if (userField.isEmpty())
                return true;

            return password.isEmpty();
        }
    }
}