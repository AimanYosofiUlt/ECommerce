package com.ultimate.ecommerce.ui.fragment.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.repos.favorite.FavoriteRepo;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class FavoriteFragmentViewModel extends BaseViewModel {

    LiveData<List<Favorite>> favoriteLiveData;

    @Inject
    public FavoriteFragmentViewModel(@NonNull Application application, FavoriteRepo favoriteRepo) {
        super(application);
        favoriteLiveData = favoriteRepo.getFavoriteProducts();
    }

}