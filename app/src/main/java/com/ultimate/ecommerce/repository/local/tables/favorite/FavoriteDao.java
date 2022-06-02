package com.ultimate.ecommerce.repository.local.tables.favorite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

import java.util.List;

@Dao
public interface FavoriteDao extends BaseDao<Favorite> {
    @Query("SELECT * FROM Favorite")
    LiveData<List<Favorite>> getFavoriteProducts();
}
