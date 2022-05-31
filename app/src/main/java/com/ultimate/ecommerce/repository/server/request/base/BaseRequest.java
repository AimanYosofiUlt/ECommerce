package com.ultimate.ecommerce.repository.server.request.base;

import android.util.Log;

import com.google.gson.Gson;
import com.ultimate.ecommerce.repository.server.remote.UltimateApi;
import com.ultimate.ecommerce.repository.server.request.create_order.CreateProductRequest;
import com.ultimate.ecommerce.repository.server.request.create_order.OrderProducts;
import com.ultimate.ecommerce.repository.server.request.update_cart.UpdateCartProductRequest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BaseRequest {

    public static RequestBody getBaseRequest() {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("tokenKey", UltimateApi.tokenKey)
                .addFormDataPart("secretKey", UltimateApi.SECRET_KEY)
                .build();
    }


    public static RequestBody getRequestByUserID(String userId) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userId)
                .build();
    }

    public static RequestBody getHomePageRequest(String page) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("page", page)
                .build();
    }

    public static RequestBody getSearchProductRequest(String title) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", title)
                .build();
    }

    // todo maybe don't work because in postman didn't work
    public static RequestBody getGetOrderRequest(String userId, int orderId) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userId)
                .addFormDataPart("orderID", String.valueOf(orderId))
                .build();
    }

    public static RequestBody getRefundOrderRequest(int orderId) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("orderID", String.valueOf(orderId))
                .build();
    }

    public static RequestBody getOrdersRequest(String userId) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userId)
                .build();
    }

    public static RequestBody getGetProductsRequest(String category, int page) {
        Log.d("BaseRequest", "getGetProductsRequest: 3o4872: " + category);
        // todo there is parameter not explained (id, page) and without any use after test with postman
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
//                .addFormDataPart("id", id)
                .addFormDataPart("type", category)
                .addFormDataPart("page", String.valueOf(page))
                .build();
    }

    public static RequestBody getGetProductRequest(int id) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", String.valueOf(id))
                .build();
    }

    public static RequestBody getGetAllReviewsRequest(int id) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", String.valueOf(id))
                .build();
    }

    public static RequestBody getFilterProductRequest(String minPrice, String maxPrice, String catId) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("minPrice", minPrice)
                .addFormDataPart("maxPrice", maxPrice)
                .addFormDataPart("catID", catId)
                .build();
    }

    public static RequestBody getAddReviewRequest(
            String userId, int productId,
            int rating, String content) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userId)
                .addFormDataPart("productID", String.valueOf(productId))
                .addFormDataPart("content", content)
                .addFormDataPart("rating", String.valueOf(rating))
                .build();
    }


    public static RequestBody getUpdateCartRequest(String couponCode, String shipping, List<UpdateCartProductRequest> productReqList) {
        Gson gson = new Gson();
        String products = gson.toJson(productReqList).toString();

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("products", products)
                .addFormDataPart("couponCode", couponCode)
                .addFormDataPart("shipping", shipping)
                .build();
    }

    public static RequestBody getGetCouponRequest(String couponCode, List<ProductRequest> productReqList) {
        Gson gson = new Gson();
        String products = gson.toJson(productReqList).toString();

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("products", products)
                .addFormDataPart("couponCode", couponCode)
                .build();
    }

    public static RequestBody getShippingMethodsRequest(String userID, String use_shipping_address) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userID)
                .addFormDataPart("use_shipping_address", use_shipping_address)
                .build();
    }

    public static RequestBody getCreateOrderRequest(String userId, List<OrderProducts> request) {
        Gson gson = new Gson();
        String orderProducts = gson.toJson(request).toString();

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("order_products", orderProducts)
                .addFormDataPart("order_userID", userId)
                .build();
    }

    public static RequestBody getCreateOrderRequest(CreateProductRequest request) {
        Gson gson = new Gson();
        String orderProducts = gson.toJson(request.getOrderProducts()).toString();
        String orderUserId = gson.toJson(request.getOrderUserId()).toString();
        String orderBillingAddress = gson.toJson(request.getOrderBillingAddress()).toString();
        String orderShippingAddress = gson.toJson(request.getOrderShippingAddress()).toString();
        String orderCouponItems = gson.toJson(request.getOrderCouponItems()).toString();
        String orderPaymentMethod = gson.toJson(request.getOrderPaymentMethod()).toString();
        String orderVat = gson.toJson(request.getOrderVat()).toString();
        String orderShippingMethod = gson.toJson(request.getOrderShippingMethod()).toString();


        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("order_products", orderProducts)
                .addFormDataPart("order_userID", orderUserId)
                .addFormDataPart("order_billing_address", orderBillingAddress)
                .addFormDataPart("order_shipping_address", orderShippingAddress)
                .addFormDataPart("order_coupon_items", orderCouponItems)
                .addFormDataPart("order_payment_method", orderPaymentMethod)
                .addFormDataPart("order_vat", orderVat)
                .addFormDataPart("order_shipping_method", orderShippingMethod)
                .build();
    }


    public static RequestBody getGetUserProfileRequest(String userId) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userId)
                .addFormDataPart("tokenKey", UltimateApi.tokenKey)
                .addFormDataPart("secretKey", UltimateApi.SECRET_KEY)
                .build();
    }

    public static RequestBody getUpdateUserProfileRequest(
            String userId, String email, String firstName,
            String lastName, String displayName) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userId)
                .addFormDataPart("email", email)
                .addFormDataPart("first_name", firstName)
                .addFormDataPart("last_name", lastName)
                .addFormDataPart("display_name", displayName)
                .build();
    }

    // todo how the user name is the key, in header use phone but disable and didn't work
    public static RequestBody getLoginUserRequest(String userPhone, String password) {
        // todo here the api take userPhone but the key name username
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                // this 'username' naming is wrong from the server but is get the user phone
                .addFormDataPart("username", userPhone)
                .addFormDataPart("password", password)
                .build();
    }

    public static RequestBody getSendSmsRequest(String phone, String email) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("phone", phone)
                .addFormDataPart("email", email)
                .build();
    }

    public static RequestBody getForgetPasswordRequest(String phone) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("phone", phone)
                .build();
    }

    public static RequestBody getUpdatePasswordRequest(String userId, String newPassword, String confirmPassword, String userStatus) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userId)
                .addFormDataPart("newPassword", newPassword)
                .addFormDataPart("confirmNewPassword", confirmPassword)
                .addFormDataPart("userStatus", userStatus)
                .build();
    }

    public static RequestBody getAddUserRequest(String name, String phone, String email, String password) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name", name)
                .addFormDataPart("phone", phone)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build();
    }

    public static RequestBody getGetCouponRequest(String couponCode, String shipping, UpdateCartProductRequest productsRequest) {
        String products = new Gson().toJson(productsRequest);

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("products", products)
                .addFormDataPart("couponCode", couponCode)
                .addFormDataPart("shipping", shipping)
                .build();
    }

    public static RequestBody getUpdateShippingAddressRequest(
            String userID, String shippingFirstName, String shippingLastName
            , String shippingCountry, String shippingRegion, String shippingCity
            , String shippingDistrict, String shippingAddress1, String shippingAddress2) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userID", userID)
                .addFormDataPart("shipping_first_name", shippingFirstName)
                .addFormDataPart("shipping_last_name", shippingLastName)
                .addFormDataPart("shipping_country", shippingCountry)
                .addFormDataPart("shipping_region", shippingRegion)
                .addFormDataPart("shipping_city", shippingCity)
                .addFormDataPart("shipping_district", shippingDistrict)
                .addFormDataPart("shipping_address_1", shippingAddress1)
                .addFormDataPart("shipping_address_2", shippingAddress2)
                .build();
    }


    public static RequestBody getUpdateBillingAddressRequest(
            String billingEmail, String billingCountry, String billingRegion
            , String billingCity, String billingDistrict, String billingAddress1
            , String billingAddress2, String billingPostcode
            , String billingPhone, String billingReceipt) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("billing_email", billingEmail)
                .addFormDataPart("billing_country", billingCountry)
                .addFormDataPart("billing_region", billingRegion)
                .addFormDataPart("billing_city", billingCity)
                .addFormDataPart("billing_district", billingDistrict)
                .addFormDataPart("billing_address_1", billingAddress1)
                .addFormDataPart("billing_address_2", billingAddress2)
                .addFormDataPart("billing_postcode", billingPostcode)
                .addFormDataPart("billing_phone", billingPhone)
                .addFormDataPart("billing_receipt", billingReceipt)
                .build();
    }
}