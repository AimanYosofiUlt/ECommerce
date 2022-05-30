package com.ultimate.ecommerce.repository.repos.product;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCartDao;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.add_review.AddReviewResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_product.GetProductResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsResponse;
import com.ultimate.ecommerce.ui.fragment.rate_order_inner.RateOrder;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class ProductRepo extends BaseRepo {
    @Inject
    ProductCartDao cartDao;

    @Inject
    public ProductRepo() {

    }

    public void getProducts(@NonNull Category category, int pageNo, ResponsesCallBack<GetProductsResponse> callBack) {
        RequestBody request = BaseRequest.getGetProductsRequest(category.getTitle(), pageNo);
        api.getProducts(request).enqueue(callBack);
    }

    public void getProductDetail(int id, ResponsesCallBack<GetProductResponse> callBack) {
        RequestBody request = BaseRequest.getGetProductRequest(id);
        api.getProduct(request).enqueue(callBack);
    }

    public void addReview(int orderId, @NonNull RateOrder rateOrder, ResponsesCallBack<AddReviewResponse> callBack) {
        //        RequestBody request = BaseRequest.getOrdersRequest(userId);
        //todo remove test code
        RequestBody request = BaseRequest.getAddReviewRequest("15259", orderId, rateOrder.getRate(), rateOrder.getComment());
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                String tokenKey = userDao.g   etTokenKey();
                //todo remove test code
                String tokenKey = "ed3e8e2829fe213e8f370f7a173f5ef0bbc2358d128500d7c3c0ddfbceb6e98c";
                api.addReview(request, tokenKey).enqueue(callBack);
            }
        });
    }

    public int getProductCartQuantity(int id) {
        return cartDao.getProductCartQuantity(id);
    }
}
