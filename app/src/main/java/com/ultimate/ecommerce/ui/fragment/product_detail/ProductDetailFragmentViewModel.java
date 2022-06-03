package com.ultimate.ecommerce.ui.fragment.product_detail;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.repository.repos.favorite.FavoriteRepo;
import com.ultimate.ecommerce.repository.repos.product.ProductRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_product.GetProductData;
import com.ultimate.ecommerce.repository.server.response.get_product.GetProductResponse;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class ProductDetailFragmentViewModel extends BaseViewModel {
    @Inject
    ProductRepo productRepo;

    @Inject
    FavoriteRepo favoriteRepo;

    @Inject
    CartRepo cartRepo;

    MutableLiveData<ResponseState> getDetailResponseMDL;
    MutableLiveData<GetProductData> productResponseMDL;

    @Inject
    public ProductDetailFragmentViewModel(@NonNull Application application) {
        super(application);
        getDetailResponseMDL = new MutableLiveData<>();
        productResponseMDL = new MutableLiveData<>();
    }

    public void validateGetProductDetail(Context context, int id) {
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
                        getProductDetail(id);
                    }

                    @Override
                    public void onDisconnect() {
                        getDetailResponseMDL.setValue(ResponseState.failureState(ValidateSt.NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getProductDetail(int id) {
        productRepo.getProductDetail(id, new ResponsesCallBack<GetProductResponse>() {
            @Override
            public void onSuccess(GetProductResponse response) {
                GetProductData data = response.getData();
                productResponseMDL.setValue(data);
            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });
    }

    public void addToFavorite(ProductAdapterData data) {
        Favorite favorite = convertProductToFavorite(data);
        favoriteRepo.addFavorite(favorite);
    }

    private Favorite convertProductToFavorite(ProductAdapterData data) {
        ProductData productData = data.getData();
        return new Favorite(productData.getId(), productData.getTitle(), productData.getImageUrl()
                , productData.getPrice(),productData.getShortDescription()
                , productData.getDiscountPercentage(), productData.getRatingCount());
    }

    public void removeFromFavorite(ProductAdapterData data) {
        Favorite favorite = convertProductToFavorite(data);
        favoriteRepo.removeFavorite(favorite);
    }

    public void addToCart(ProductCart productCart) {
        cartRepo.addToCart(productCart);
    }
}