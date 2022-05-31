package com.ultimate.ecommerce.repository.repos.cart;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCartDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.request.base.ProductRequest;
import com.ultimate.ecommerce.repository.server.request.update_cart.UpdateCartProductRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class CartRepo extends BaseRepo {

    @Inject
    ProductCartDao cartDao;

    @Inject
    public CartRepo() {

    }

    public void addToCart(ProductCart productCart) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.insert(productCart);
            }
        });
    }

    public LiveData<List<ProductCart>> getCartProducts() {
        return cartDao.getCart();
    }

    public void updateCardProduct(Integer productId, int qty) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.updateCartProduct(productId, qty);
            }
        });
    }

    public void removeCartProduct(Integer productId) {
        AsyncTask.execute(() -> cartDao.removeCartProduct(productId));
    }

    public void updateCartApi(String coupon, List<UpdateCartProductRequest> requestList, ResponsesCallBack<UpdateCartResponse> callBack) {
        //todo handel shipment value
        RequestBody request = BaseRequest.getUpdateCartRequest(coupon, "0", requestList);
        api.updateCart(request).enqueue(callBack);
    }

    public void clearCart() {
        AsyncTask.execute(() -> cartDao.clearCart());
    }

    public LiveData<Double> getCartTotal() {
        return cartDao.getTotal();
    }
}