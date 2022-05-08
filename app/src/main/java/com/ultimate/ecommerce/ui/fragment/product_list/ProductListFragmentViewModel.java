package com.ultimate.ecommerce.ui.fragment.product_list;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.repos.product.ProductRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_products.FiltersData;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsData;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.repository.server.response.get_products.SubCategoryData;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.List;

import javax.inject.Inject;

public class ProductListFragmentViewModel extends BaseViewModel {
    @Inject
    ProductRepo productRepo;

    MutableLiveData<ResponseState> responseStateMDL;
    MutableLiveData<List<SubCategoryData>> subCategoriesMDL;
    MutableLiveData<List<ProductData>> productsMDL;
    MutableLiveData<List<FiltersData>> filtersMDL;

    @Inject
    public ProductListFragmentViewModel(@NonNull Application application) {
        super(application);
        responseStateMDL = new MutableLiveData<>();
        subCategoriesMDL = new MutableLiveData<>();
        productsMDL = new MutableLiveData<>();
        filtersMDL = new MutableLiveData<>();
    }

    public void validateGetProducts(Context context, Category category) {
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
                        getProductList(category);
                    }

                    @Override
                    public void onDisconnect() {
                        responseStateMDL.setValue(ResponseState.failureState(context.getString(R.string.no_internet_connection)));
                    }
                });
    }

    private void getProductList(Category category) {
        productRepo.getProductList(category, new ResponsesCallBack<GetProductsResponse>() {
            @Override
            public void onSuccess(GetProductsResponse response) {
                GetProductsData responseData = response.getData();
                subCategoriesMDL.setValue(responseData.getSubCategories());
                productsMDL.setValue(responseData.getProducts());
                filtersMDL.setValue(responseData.getFilters());
            }

            @Override
            public void onFailure(String state, String msg) {
                responseStateMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}