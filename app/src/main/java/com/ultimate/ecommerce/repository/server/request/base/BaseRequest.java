package com.ultimate.ecommerce.repository.server.request.base;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BaseRequest {
    public static RequestBody getConfigurationRequest(String userId) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userId)
                .build();
    }

    public static RequestBody getHomePageRequest(String page) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", page)
                .build();
    }


}