package com.ultimate.ecommerce.repository.server.remote;

import com.ultimate.ecommerce.repository.server.response.ysobjecs.GenResponseObject;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigurationResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UltimateApi {
    @Headers({"tokenKey: 123456", "secretKey: 123456",
            "lang: ar", "osType: android"})
    @POST("configuration")
    Call<ConfigurationResponse> getConfiguration(@Body RequestBody requestBody);
}