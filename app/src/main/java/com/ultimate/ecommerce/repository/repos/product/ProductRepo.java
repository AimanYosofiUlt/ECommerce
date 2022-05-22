package com.ultimate.ecommerce.repository.repos.product;

import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class ProductRepo extends BaseRepo {
    @Inject
    public ProductRepo(){

    }

    public void getProductList(Category category,int pageNo, ResponsesCallBack<GetProductsResponse> callBack) {
        RequestBody request = BaseRequest.getGetProductsRequest(category.getTitle(),pageNo);
        api.getProducts(request).enqueue(callBack);
    }
}
