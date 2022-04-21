package com.ultimate.ecommerce.repository.server.request.base;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BaseRequest {
    public static RequestBody getConfigurationRequest(String page) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("page", page)
                .build();
    }
}