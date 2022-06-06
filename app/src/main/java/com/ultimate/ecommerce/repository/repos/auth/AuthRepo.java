package com.ultimate.ecommerce.repository.repos.auth;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ultimate.ecommerce.repository.local.tables.auth.Auth;
import com.ultimate.ecommerce.repository.local.tables.auth.AuthDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_auth.Field;
import com.ultimate.ecommerce.repository.server.response.get_auth.GetAuthResponse;

import java.util.List;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class AuthRepo extends BaseRepo {
    @Inject
    AuthDao authDao;

    @Inject
    public AuthRepo() {
    }

    public void getScreenAuth(String screenName, ResponsesCallBack<GetAuthResponse> callBack) {
        RequestBody request = BaseRequest.getBaseRequest();
        api.getAuth(request, screenName).enqueue(new ResponsesCallBack<GetAuthResponse>() {
            @Override
            public void onSuccess(GetAuthResponse response) {
                addAuthScreen(screenName, response.getData().getFields());
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(String state, String msg) {
                callBack.onFailure(state, msg);
            }
        });
    }

    private void addAuthScreen(String screenName, List<Field> fields) {
        AsyncTask.execute(() -> {
            String jsonData = new Gson().toJson(fields);
            Auth auth = new Auth(screenName, jsonData);
            authDao.insert(auth);
        });
    }

    public String getScreen(String screen) {
        return authDao.getAuth(screen);
    }
}
