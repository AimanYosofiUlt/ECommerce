package com.ultimate.ecommerce.repository.local.tables.favorite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;
import com.ultimate.ecommerce.ui.fragment.favorite.views.product.FavoriteAdapterData;

import java.util.List;

@Dao
public interface FavoriteDao extends BaseDao<Favorite> {
    @Query("SELECT  " +
            "      (    SELECT CASE count(productQuantity) " +
            "           WHEN 0  THEN 0 " +
            "                   ELSE productQuantity  " +
            "           END  " +
            "           FROM ProductCart " +
            "           WHERE productId = favorite.id" +
            "       ) AS cartQuantity" +
            "       ,favorite.*  " +
            "FROM Favorite favorite")
    LiveData<List<FavoriteAdapterData>> getFavoriteProducts();

    @Query("  SELECT CASE count(id) " +
            "           WHEN 0  THEN 0 " +
            "                   ELSE 1  " +
            "           END  " +
            "           FROM Favorite " +
            "           WHERE id = :productId")
    boolean isInFavorite(int productId);

    @Query("DELETE FROM Favorite WHERE id = :id")
    void deleteFavorite(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavorite(Favorite favorite);
}
