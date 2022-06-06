package com.ultimate.ecommerce.repository.repos.address;

import android.os.AsyncTask;

import com.ultimate.ecommerce.repository.local.user.UserDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldsResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class AddressRepo extends BaseRepo {
    @Inject
    UserDao userDao;

    @Inject
    public AddressRepo() {
    }

    public void getAddressFields(ResponsesCallBack<GetAddressFieldsResponse> callBack) {
        AsyncTask.execute(() -> {
            String tokenKey = userDao.getTokenKey();
            RequestBody request = BaseRequest.getRequestByUserID(userDao.getUserId());
            api.getAddressFields(request, tokenKey).enqueue(callBack);
        });
    }
}
