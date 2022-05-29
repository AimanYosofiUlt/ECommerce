package com.ultimate.ecommerce.repository.server.remote;

import com.ultimate.ecommerce.repository.server.response.about_us.AboutUsResponse;
import com.ultimate.ecommerce.repository.server.response.add_review.AddReviewResponse;
import com.ultimate.ecommerce.repository.server.response.add_user.AddUserResponse;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigurationResponse;
import com.ultimate.ecommerce.repository.server.response.contact_us.ContactUsResponse;
import com.ultimate.ecommerce.repository.server.response.countries.CountriesResponse;
import com.ultimate.ecommerce.repository.server.response.coupon.GetCouponResponse;
import com.ultimate.ecommerce.repository.server.response.filter_products.FilterProductResponse;
import com.ultimate.ecommerce.repository.server.response.forget_password.ForgetPasswordResponse;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldsResponse;
import com.ultimate.ecommerce.repository.server.response.get_all_reviews.GatAllReviewsResponse;
import com.ultimate.ecommerce.repository.server.response.get_auth.GetAuthResponse;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryResponse;
import com.ultimate.ecommerce.repository.server.response.get_order.GetOrderResponse;
import com.ultimate.ecommerce.repository.server.response.get_product.GetProductResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsResponse;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.GetUserOrdersResponse;
import com.ultimate.ecommerce.repository.server.response.get_user_profile.GetUserProfileResponse;
import com.ultimate.ecommerce.repository.server.response.help.HelpResponse;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageResponse;
import com.ultimate.ecommerce.repository.server.response.login_user.LoginUserResponse;
import com.ultimate.ecommerce.repository.server.response.logout.LogoutResponse;
import com.ultimate.ecommerce.repository.server.response.payment_methods.PaymentMethodsResponse;
import com.ultimate.ecommerce.repository.server.response.search_product.SearchProductResponse;
import com.ultimate.ecommerce.repository.server.response.send_sms.SendSmsResponse;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsResponse;
import com.ultimate.ecommerce.repository.server.response.update_billing_address.UpdateBillingAddressResponse;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartResponse;
import com.ultimate.ecommerce.repository.server.response.update_password.UpdatePasswordResponse;
import com.ultimate.ecommerce.repository.server.response.update_profile.UpdateProfileResponse;
import com.ultimate.ecommerce.repository.server.response.update_shipping_address.UpdateShippingAddressResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UltimateApi {
    public static final String LANGUAGE = "en";
    public static final String tokenKey = "-1";
    public static final String SECRET_KEY = "123456";

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("configuration")
    Call<ConfigurationResponse> getConfiguration(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("homepage")
    Call<HomePageResponse> getHomePage(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getCategories")
    Call<GetCategoryResponse> getCategories(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("aboutUs")
    Call<AboutUsResponse> aboutUs(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("contactUs")
    Call<ContactUsResponse> contactUs(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("help")
    Call<HelpResponse> help(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getProduct")
    Call<GetProductResponse> getProduct(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getProducts")
    Call<GetProductsResponse> getProducts(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getAllReviews")
    Call<GatAllReviewsResponse> getAllReviews(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("searchProduct")
    Call<SearchProductResponse> searchProduct(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("filterProducts")
    Call<FilterProductResponse> filterProducts(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("addReview")
    Call<AddReviewResponse> addReview(@Body RequestBody requestBody);


    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updateCart")
    Call<UpdateCartResponse> updateCart(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getCoupon")
    Call<GetCouponResponse> getCoupon(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("shippingMethods")
    Call<ShippingMethodsResponse> shippingMethods(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("paymentMethods")
    Call<PaymentMethodsResponse> paymentMethods(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("countries")
    Call<CountriesResponse> countries(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getUserOrders")
    Call<GetUserOrdersResponse> getUserOrders(@Body RequestBody requestBody, @Header("tokenKey") String tokenKey);


    @Headers({"lang: " + LANGUAGE,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getOrder")
    Call<GetOrderResponse> getOrder(@Body RequestBody requestBody, @Header("tokenKey") String tokenKey);


//    @Headers({"lang: " + LANGUAGE,
//            "tokenKey: " + TOKEN_KEY,
//            "secretKey: " + SECRET_KEY,
//            "osType: android"})
//    @POST("refundOrder")
//    Call<GetCategoryResponse> refundOrder(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("createOrder")
    Call<GetCategoryResponse> createOrder(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getUserProfile")
    Call<GetUserProfileResponse> getUserProfile(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updateProfile")
    Call<UpdateProfileResponse> updateProfile(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getAddressFields")
    Call<GetAddressFieldsResponse> getAddressFields(@Body RequestBody requestBody);


    // todo in postman they use parameter like getAuth?page=register
    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("getAuth")
    Call<GetAuthResponse> getAuth(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("loginUser")
    Call<LoginUserResponse> loginUser(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("logout")
    Call<LogoutResponse> logout(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("sendSms")
    Call<SendSmsResponse> sendSms(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updateShippingAddress")
    Call<UpdateShippingAddressResponse> updateShippingAddress(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updateBillingAddress")
    Call<UpdateBillingAddressResponse> updateBillingAddress(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("forgetPassword")
    Call<ForgetPasswordResponse> forgetPassword(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("updatePassword")
    Call<UpdatePasswordResponse> updatePassword(@Body RequestBody requestBody);

    @Headers({"lang: " + LANGUAGE,
            "tokenKey: " + tokenKey,
            "secretKey: " + SECRET_KEY,
            "osType: android"})
    @POST("addUser")
    Call<AddUserResponse> addUser(@Body RequestBody requestBody);
}