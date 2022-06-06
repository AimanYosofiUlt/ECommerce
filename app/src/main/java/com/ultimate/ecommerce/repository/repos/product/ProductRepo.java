package com.ultimate.ecommerce.repository.repos.product;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCartDao;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.local.user.UserDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.add_review.AddReviewResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.filter_products.FilterProductResponse;
import com.ultimate.ecommerce.repository.server.response.get_product.GetProductResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsResponse;
import com.ultimate.ecommerce.repository.server.response.search_product.SearchProductResponse;
import com.ultimate.ecommerce.ui.fragment.product_list.bottomsheets.filter.Filter;
import com.ultimate.ecommerce.ui.fragment.rate_order_inner.RateOrder;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class ProductRepo extends BaseRepo {
    @Inject
    ProductCartDao cartDao;

    @Inject
    UserDao userDao;

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
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getAddReviewRequest(userDao.getUserId(), orderId, rateOrder.getRate(), rateOrder.getComment());
            String tokenKey = userDao.getTokenKey();
            api.addReview(request, tokenKey).enqueue(callBack);
        });
    }

    public int getProductCartQuantity(int id) {
        return cartDao.getProductCartQuantity(id);
    }


    public void searchProduct(String searchText, ResponsesCallBack<SearchProductResponse> callBack) {
        RequestBody request = BaseRequest.getSearchProductRequest(searchText);
        api.searchProduct(request).enqueue(callBack);
    }

    public void getProductsByFilter(Category category, Filter filter, ResponsesCallBack<FilterProductResponse> callBack) {
        RequestBody request = BaseRequest.getFilterProductRequest(category.getId(), filter);
        api.filterProducts(request).enqueue(callBack);

    }
}
