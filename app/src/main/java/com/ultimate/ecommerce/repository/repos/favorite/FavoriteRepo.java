package com.ultimate.ecommerce.repository.repos.favorite;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.local.tables.favorite.FavoriteDao;
import com.ultimate.ecommerce.ui.fragment.favorite.views.product.FavoriteAdapterData;

import java.util.List;

import javax.inject.Inject;

public class FavoriteRepo {
    @Inject
    FavoriteDao favoriteDao;

    @Inject
    public FavoriteRepo() {
    }

    public LiveData<List<FavoriteAdapterData>> getFavoriteProducts() {
        return favoriteDao.getFavoriteProducts();
    }

    public void addFavorite(Favorite favorite) {
        AsyncTask.execute(() -> favoriteDao.addFavorite(favorite));
    }

    public void removeFavorite(Favorite favorite) {
        AsyncTask.execute(() -> favoriteDao.deleteFavorite(favorite.getId()));
    }

    public boolean isInFavorite(int id) {
        return favoriteDao.isInFavorite(id);
    }
}
