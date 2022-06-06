package com.ultimate.ecommerce.ui.fragment.cart;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.repository.repos.setting.AppSettingRepo;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.request.update_cart.UpdateCartProductRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartData;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CartFragmentViewModel extends BaseViewModel {
    @Inject
    AppSettingRepo repo;

    @Inject
    CartRepo cartRepo;

    @Inject
    UserRepo userRepo;

    LiveData<List<ProductCart>> productCartLiveData;
    LiveData<Double> cartTotalLiveData;
    MutableLiveData<ResponseState> updateCartResponseMDL;
    MutableLiveData<UpdateCartData> cartDataMDL;

    MutableLiveData<Boolean> userLoginCheckMDL;

    @Inject
    public CartFragmentViewModel(@NonNull Application application, CartRepo cartRepo) {
        super(application);
        productCartLiveData = cartRepo.getCartProducts();
        cartTotalLiveData = cartRepo.getCartTotal();
        updateCartResponseMDL = new MutableLiveData<>();
        cartDataMDL = new MutableLiveData<>();
        userLoginCheckMDL = new MutableLiveData<>();
    }

    public void updateProductQty(Integer productId, int qty) {
        cartRepo.updateCardProduct(productId, qty);
    }

    public void removeCartProduct(Integer productId) {
        cartRepo.removeCartProduct(productId);
    }

    public void validateUpdateCart(Context requireContext, String coupon, List<ProductCart> list) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        if (list.isEmpty()) {
                            updateCartResponseMDL.setValue(ResponseState.failureState(ValidateSt.CART_PRODUCTS_EMPTY_ERROR));
                            return false;
                        }
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(requireContext, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        List<UpdateCartProductRequest> requestList = new ArrayList<>();
                        for (ProductCart productCart : list) {
                            UpdateCartProductRequest productRequest = new UpdateCartProductRequest(productCart.getProductId(), productCart.getProductPrice(), productCart.getProductQuantity());
                            requestList.add(productRequest);
                        }
                        updateCart(coupon, requestList);
                    }

                    @Override
                    public void onDisconnect() {
                        updateCartResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void updateCart(String coupon, List<UpdateCartProductRequest> requestList) {
        cartRepo.updateCartApi(coupon, requestList, new ResponsesCallBack<UpdateCartResponse>() {
            @Override
            public void onSuccess(UpdateCartResponse response) {
                UpdateCartData data = response.getData();
                cartDataMDL.setValue(data);
                updateCartResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                updateCartResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    public void clearCart() {
        cartRepo.clearCart();
    }

    public void validatePayment() {
        AsyncTask.execute(() -> {
            boolean isUserLogin = userRepo.isUserLogin();
            userLoginCheckMDL.postValue(isUserLogin);
        });
    }
}