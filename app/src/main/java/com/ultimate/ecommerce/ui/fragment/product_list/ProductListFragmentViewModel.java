package com.ultimate.ecommerce.ui.fragment.product_list;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.repository.repos.product.ProductRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_products.Categories;
import com.ultimate.ecommerce.repository.server.response.get_products.FiltersData;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsData;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.List;

import javax.inject.Inject;

public class ProductListFragmentViewModel extends BaseViewModel {
    @Inject
    ProductRepo productRepo;

    @Inject
    CartRepo cartRepo;


    MutableLiveData<ResponseState> validateGetProductsMDL;
    MutableLiveData<ResponseState> getProductResponseStateMDL;
    MutableLiveData<ResponseState> updateCartResStateMDL;
    MutableLiveData<List<Categories>> subCategoriesMDL;
    MutableLiveData<List<ProductData>> productsMDL;
    MutableLiveData<List<FiltersData>> filtersMDL;

    @Inject
    public ProductListFragmentViewModel(@NonNull Application application) {
        super(application);
        validateGetProductsMDL = new MutableLiveData<>();
        getProductResponseStateMDL = new MutableLiveData<>();
        updateCartResStateMDL = new MutableLiveData<>();
        subCategoriesMDL = new MutableLiveData<>();
        productsMDL = new MutableLiveData<>();
        filtersMDL = new MutableLiveData<>();
    }

    public void validateGetProducts(Context context, Category category, int pageNo) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        getProducts(category, pageNo);
                    }

                    @Override
                    public void onDisconnect() {
                        validateGetProductsMDL.setValue(ResponseState.failureState(context.getString(R.string.no_internet_connection)));
                    }
                });
    }

    private void getProducts(Category category, int pageNo) {
        productRepo.getProducts(category, pageNo, new ResponsesCallBack<GetProductsResponse>() {
            @Override
            public void onSuccess(GetProductsResponse response) {
                GetProductsData responseData = response.getData();
                subCategoriesMDL.setValue(responseData.getSubCategories());
                productsMDL.setValue(responseData.getProducts());
                filtersMDL.setValue(responseData.getFilters());

                getProductResponseStateMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getProductResponseStateMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    public void validateAddToCart(Context context, ProductCart productCart, String couponCode, String shipping) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        addToCart(productCart, couponCode, shipping);
                    }

                    @Override
                    public void onDisconnect() {
                        updateCartResStateMDL.setValue(ResponseState.failureState(context.getString(R.string.no_internet_connection)));
                    }
                });
    }

    public void addToCart(ProductCart productCart, String couponCode, String shipping) {
        cartRepo.addToOfflineCart(productCart);
        cartRepo.updateCartApi(couponCode, shipping, new ResponsesCallBack<UpdateCartResponse>() {
            @Override
            public void onSuccess(UpdateCartResponse response) {
                updateCartResStateMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                updateCartResStateMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }


}