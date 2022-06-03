package com.ultimate.ecommerce.ui.fragment.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.repository.repos.favorite.FavoriteRepo;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.favorite.views.product.FavoriteAdapterData;

import java.util.List;

import javax.inject.Inject;

public class FavoriteFragmentViewModel extends BaseViewModel {
    @Inject
    FavoriteRepo favoriteRepo;

    @Inject
    CartRepo cartRepo;

    LiveData<List<FavoriteAdapterData>> favoriteLiveData;

    @Inject
    public FavoriteFragmentViewModel(@NonNull Application application, FavoriteRepo favoriteRepo) {
        super(application);
        favoriteLiveData = favoriteRepo.getFavoriteProducts();
    }

    public void removeFromFavorite(Favorite data) {
        favoriteRepo.removeFavorite(data);
    }

    public void addToCart(ProductCart productCart) {
        cartRepo.addToCart(productCart);
    }
}