package com.ultimate.ecommerce.ui.fragment.home.product_search;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;
import static com.ultimate.ecommerce.utilities.ValidateSt.SEARCH_EMPTY_ERROR;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.repository.repos.favorite.FavoriteRepo;
import com.ultimate.ecommerce.repository.repos.product.ProductRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.repository.server.response.search_product.SearchProductResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductSearchFragmentViewModel extends BaseViewModel {
    @Inject
    ProductRepo productRepo;

    @Inject
    CartRepo cartRepo;

    @Inject
    FavoriteRepo favoriteRepo;

    MutableLiveData<ResponseState> validateSearchMDL;
    MutableLiveData<ResponseState> searchResponseMDL;
    MutableLiveData<List<ProductAdapterData>> productsMDL;

    @Inject
    public ProductSearchFragmentViewModel(@NonNull Application application) {
        super(application);
        searchResponseMDL = new MutableLiveData<>();
        validateSearchMDL = new MutableLiveData<>();
        productsMDL = new MutableLiveData<>();
    }

    public void validateSearch(Context requireContext, String searchText) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        if (searchText.isEmpty()) {
                            validateSearchMDL.setValue(ResponseState.failureState(SEARCH_EMPTY_ERROR));
                            return false;
                        }
                        return true;
                    }
                })
                .checkNetwork(requireContext, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        searchProducts(searchText);
                    }

                    @Override
                    public void onDisconnect() {
                        validateSearchMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void searchProducts(String searchText) {
        productRepo.searchProduct(searchText, new ResponsesCallBack<SearchProductResponse>() {
            @Override
            public void onSuccess(SearchProductResponse response) {
                List<ProductData> productsList = response.getData().getProducts();
                synchronizeWithCart(productsList);
                searchResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                searchResponseMDL.setValue(ResponseState.failureState(msg));
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
                , productData.getPrice(),productData.getShortDescription(), productData.getDiscountPercentage(), productData.getRatingCount());
    }

    public void removeFromFavorite(ProductAdapterData data) {
        Favorite favorite = convertProductToFavorite(data);
        favoriteRepo.removeFavorite(favorite);
    }
}