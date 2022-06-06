package com.ultimate.ecommerce.ui.fragment.product_list;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

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
import com.ultimate.ecommerce.repository.server.response.filter_products.FilterProductResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.Categories;
import com.ultimate.ecommerce.repository.server.response.get_products.FiltersData;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsData;
import com.ultimate.ecommerce.repository.server.response.get_products.GetProductsResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.product_list.bottomsheets.filter.Filter;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.utilities.ValidateSt;
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

    MutableLiveData<ResponseState> getProductResponseMDL;
    MutableLiveData<ResponseState> getProductByFilterResponseMDL;
    MutableLiveData<ResponseState> updateCartResponseMDL;
    MutableLiveData<List<Categories>> subCategoriesMDL;
    MutableLiveData<List<ProductAdapterData>> productsMDL;
    MutableLiveData<List<FiltersData>> filtersMDL;

    @Inject
    public ProductListFragmentViewModel(@NonNull Application application) {
        super(application);
        getProductResponseMDL = new MutableLiveData<>();
        getProductByFilterResponseMDL = new MutableLiveData<>();
        updateCartResponseMDL = new MutableLiveData<>();
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
                        getProductResponseMDL.setValue(ResponseState.failureState(context.getString(R.string.no_internet_connection)));
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
                getProductResponseMDL.setValue(ResponseState.successState());

                synchronizeWithCart(responseData.getProducts());
            }

            @Override
            public void onFailure(String state, String msg) {
                getProductResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    private void synchronizeWithCart(List<ProductData> products) {
        AsyncTask.execute(() -> {
            ArrayList<ProductAdapterData> dataList = new ArrayList<>();
            for (ProductData product : products) {
                int cartQuantity = productRepo.getProductCartQuantity(product.getId());
                boolean isInFavorite = favoriteRepo.isInFavorite(product.getId());
                ProductAdapterData data = new ProductAdapterData(product, cartQuantity,isInFavorite);
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
                , productData.getPrice(), productData.getShortDescription(), productData.getDiscountPercentage(), productData.getRatingCount());
    }

    public void removeFromFavorite(ProductAdapterData data) {
        Favorite favorite = convertProductToFavorite(data);
        favoriteRepo.removeFavorite(favorite);
    }

    public void validateGetProductByFilter(Context context, Category category, Filter filter) {
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
                        getProductsByFilter(category, filter);
                    }

                    @Override
                    public void onDisconnect() {
                        getProductByFilterResponseMDL.setValue(ResponseState.failureState(ValidateSt.NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getProductsByFilter(Category category, Filter filter) {
        productRepo.getProductsByFilter(category, filter, new ResponsesCallBack<FilterProductResponse>() {
            @Override
            public void onSuccess(FilterProductResponse response) {
                List<ProductData> products = response.getData().getProducts();
                synchronizeWithCart(products);
                getProductByFilterResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getProductByFilterResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}