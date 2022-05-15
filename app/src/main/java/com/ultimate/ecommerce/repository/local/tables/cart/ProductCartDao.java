package com.ultimate.ecommerce.repository.local.tables.cart;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

import java.util.List;

@Dao
public interface ProductCartDao extends BaseDao<ProductCart> {

    @Query("SELECT * FROM ProductCart")
    List<ProductCart> getCart();
}
