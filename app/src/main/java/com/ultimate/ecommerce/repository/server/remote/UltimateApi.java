package com.ultimate.ecommerce.repository.server.remote;

import com.ultimate.ecommerce.repository.server.response.base.ResponseObject;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageResponse;
import com.ultimate.ecommerce.repository.server.response.ysobjecs.GenResponseObject;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigurationResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UltimateApi {
    @Headers({"lang: en", "tokenKey: -1",
            "secretKey: 123456", "osType: android"})
    @POST("configuration")
    Call<ConfigurationResponse> getConfiguration(@Body RequestBody requestBody);

    @Headers({"lang: en", "tokenKey: -1",
            "secretKey: 123456", "osType: android"})
    @POST("homepage")
    Call<HomePageResponse> getHomePage(@Body RequestBody requestBody);
}