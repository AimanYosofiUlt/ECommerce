package com.ultimate.ecommerce.ui.fragment.home;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.repository.repos.category.CategoryRepo;
import com.ultimate.ecommerce.repository.repos.favorite.FavoriteRepo;
import com.ultimate.ecommerce.repository.repos.homepage.HomePageRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageData;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class HomeFragmentViewModel extends BaseViewModel {
    @Inject
    HomePageRepo homePageRepo;

    @Inject
    CartRepo cartRepo;

    @Inject
    FavoriteRepo favoriteRepo;

    private static final String TAG = "HomeFragmentViewModel";

    @Inject
    CategoryRepo categoryRepo;

    MutableLiveData<HomePageData> homePageDataMDL;
    MutableLiveData<ResponseState> homepageResponseMDL;

    int pageCount = 1;

    @Inject
    public HomeFragmentViewModel(@NonNull Application application, CategoryRepo categoryRepo) {
        super(application);
        homePageDataMDL = new MutableLiveData<>();
        homepageResponseMDL = new MutableLiveData<>();
    }

    public void getHomePageData(int page) {
        homePageRepo.getHomePageData(page, new ResponsesCallBack<HomePageResponse>() {
            @Override
            public void onSuccess(HomePageResponse response) {
                HomePageData data = response.getData();
                homePageDataMDL.setValue(data);
                Log.d(TAG, "onSuccess:65426 pages size is : "+response.getData().getPages());
                int pages = response.getData().getPages();
                if (pages > pageCount) {
                    pageCount++;
                    getHomePageData(pageCount);
                }
            }

            @Override
            public void onFailure(String state, String msg) {
                homepageResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    public void validateGetHomePageData(Context context) {
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
                        getHomePageData(1);
                    }

                    @Override
                    public void onDisconnect() {
                        homepageResponseMDL.setValue(ResponseState.failureState(context.getString(R.string.no_internet_connection)));
                    }
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
                , productData.getPrice(),productData.getShortDescription()
                , productData.getDiscountPercentage(), productData.getRatingCount());
    }

    public void removeFromFavorite(ProductAdapterData data) {
        Favorite favorite = convertProductToFavorite(data);
        favoriteRepo.removeFavorite(favorite);
    }
}
