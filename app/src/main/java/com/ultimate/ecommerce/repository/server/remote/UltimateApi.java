package com.ultimate.ecommerce.repository.server.remote;

import com.ultimate.ecommerce.repository.server.response.configuration.ConfigurationResponse;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryResponse;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UltimateApi {
    public static final String LANGUAGE = "en";
    public static final String TOKEN_KEY = "-1";
    public static final String SECRET_KEY = "123456";

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("configuration")
    Call<ConfigurationResponse> getConfiguration(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("homepage")
    Call<HomePageResponse> getHomePage(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getCategories")
    Call<GetCategoryResponse> getCategories(@Body RequestBody requestBody);

    ///////////////////////////////////////////////////////

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("aboutUs")
    Call<GetCategoryResponse> aboutUs(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("contactUs")
    Call<GetCategoryResponse> contactUs(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("help")
    Call<GetCategoryResponse> help(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getProduct")
    Call<GetCategoryResponse> getProduct(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getProducts")
    Call<GetCategoryResponse> getProducts(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getAllReviews")
    Call<GetCategoryResponse> getAllReviews(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("searchProduct")
    Call<GetCategoryResponse> searchProduct(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("filterProducts")
    Call<GetCategoryResponse> filterProducts(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("addReview")
    Call<GetCategoryResponse> addReview(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updateCart")
    Call<GetCategoryResponse> updateCart(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getCoupon")
    Call<GetCategoryResponse> getCoupon(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("shippingMethods")
    Call<GetCategoryResponse> shippingMethods(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("paymentMethods")
    Call<GetCategoryResponse> paymentMethods(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("countries")
    Call<GetCategoryResponse> countries(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getUserOrders")
    Call<GetCategoryResponse> getUserOrders(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getOrder")
    Call<GetCategoryResponse> getOrder(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("refundOrder")
    Call<GetCategoryResponse> refundOrder(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("createOrder")
    Call<GetCategoryResponse> createOrder(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getUserProfile")
    Call<GetCategoryResponse> getUserProfile(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updateProfile")
    Call<GetCategoryResponse> updateProfile(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getAddressFields")
    Call<GetCategoryResponse> getAddressFields(@Body RequestBody requestBody);


    // todo in postman they use parameter like getAuth?page=register
    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getAuth")
    Call<GetCategoryResponse> getAuth(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("loginUser")
    Call<GetCategoryResponse> loginUser(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("logout")
    Call<GetCategoryResponse> logout(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("sendSms")
    Call<GetCategoryResponse> sendSms(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updateShippingAddress")
    Call<GetCategoryResponse> updateShippingAddress(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updateBillingAddress")
    Call<GetCategoryResponse> updateBillingAddress(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("forgetPassword")
    Call<GetCategoryResponse> forgetPassword(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updatePassword")
    Call<GetCategoryResponse> updatePassword(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + TOKEN_KEY,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("addUser")
    Call<GetCategoryResponse> addUser(@Body RequestBody requestBody);
}