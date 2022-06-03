package com.ultimate.ecommerce.ui.fragment.product_list;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.repository.repos.favorite.FavoriteRepo;
import com.ultimate.ecommerce.repository.repos.product.ProductRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_products.Categories;
import com.ultimate.ecommerce.repository.server.response.get_products.FiltersData;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsData;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductListFragmentViewModel extends BaseViewModel {
    private static final String TAG = "ProductListFragmentView";
    @Inject
    FavoriteRepo favoriteRepo;

    @Inject
    ProductRepo productRepo;

    @Inject
    CartRepo cartRepo;


    MutableLiveData<ResponseState> validateGetProductsMDL;
    MutableLiveData<ResponseState> getProductResponseStateMDL;
    MutableLiveData<ResponseState> updateCartResStateMDL;
    MutableLiveData<List<Categories>> subCategoriesMDL;
    MutableLiveData<List<ProductAdapterData>> productsMDL;
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
                filtersMDL.setValue(responseData.getFilters());
                getProductResponseStateMDL.setValue(ResponseState.successState());

                synchronizeWithCart(responseData.getProducts());
            }

            @Override
            public void onFailure(String state, String msg) {
                getProductResponseStateMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    private void synchronizeWithCart(List<ProductData> products) {
        AsyncTask.execute(() -> {
            Log.d(TAG, "synchronizeWithCart: 2ew39:"+products.size());
            ArrayList<ProductAdapterData> dataList = new ArrayList<>();
            for (ProductData product : products) {
                int cartQuantity = productRepo.getProductCartQuantity(product.getId());
                ProductAdapterData data = new ProductAdapterData(product, cartQuantity);
                dataList.add(data);
            }
            productsMDL.postValue(dataList);
        });
    }

    public void addToCart(ProductCart productCart) {
        cartRepo.addToCart(productCart);
    }


    public void addToFavorite(ProductAdapterData data) {
        Favorite favorite = convertProductToFavorite(data);
        favoriteRepo.addFavorite(favorite);
    }

    private Favorite convertProductToFavorite(ProductAdapterData data) {
        ProductData productData = data.getData();
        return new Favorite(productData.getId(), productData.getTitle(), productData.getImageUrl()
                , productData.getPrice(),productData.getShortDescription(), productData.getDiscountPercentage(), productData.getRatingCount());
    }

    public void removeFromFavorite(ProductAdapterData data) {
        Favorite favorite = convertProductToFavorite(data);
        favoriteRepo.removeFavorite(favorite);
    }
}