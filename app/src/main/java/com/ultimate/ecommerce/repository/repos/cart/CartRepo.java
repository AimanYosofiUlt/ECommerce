package com.ultimate.ecommerce.repository.repos.cart;

import android.os.AsyncTask;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCartDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.request.base.ProductRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class CartRepo extends BaseRepo {

    @Inject
    ProductCartDao dao;

    @Inject
    public CartRepo() {

    }

    public void addToOfflineCart(ProductCart productCart) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(productCart);
            }
        });
    }

    public void updateCartApi(String couponCode, String shipping, ResponsesCallBack<UpdateCartResponse> callBack) {
        List<ProductRequest> productReqList = getProductReqList();
        RequestBody request = BaseRequest.getUpdateCartRequest(couponCode, shipping, productReqList);
        api.updateCart(request).enqueue(callBack);
    }

    private List<ProductRequest> getProductReqList() {
        List<ProductCart> cartList = dao.getCart();
        List<ProductRequest> productReqList = new ArrayList<>();
        for (ProductCart cart : cartList) {
            ProductRequest productRequest = new ProductRequest(cart.getProductId()
                    , cart.getProductPrice()
                    , cart.getProductQuantity());
            productReqList.add(productRequest);
        }
        return productReqList;
    }
}
