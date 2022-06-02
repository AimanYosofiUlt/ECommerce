package com.ultimate.ecommerce.repository.repos.favorite;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.local.tables.favorite.FavoriteDao;

import java.util.List;

import javax.inject.Inject;

public class FavoriteRepo {
    @Inject
    FavoriteDao favoriteDao;

    @Inject
    public FavoriteRepo() {
    }

    public LiveData<List<Favorite>> getFavoriteProducts() {
        return favoriteDao.getFavoriteProducts();
    }

    public void addFavorite(Favorite favorite) {
        AsyncTask.execute(() -> favoriteDao.insert(favorite));
    }

    public void removeFavorite(Favorite favorite) {
        AsyncTask.execute(() -> favoriteDao.delete(favorite));
    }
}
