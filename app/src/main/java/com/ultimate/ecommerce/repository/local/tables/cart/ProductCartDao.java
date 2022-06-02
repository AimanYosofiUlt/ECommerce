package com.ultimate.ecommerce.repository.local.tables.cart;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

import java.util.List;

@Dao
public interface ProductCartDao extends BaseDao<ProductCart> {
    @Query("SELECT * FROM ProductCart")
    LiveData<List<ProductCart>> getCart();

    @Query("SELECT " +
            "   CASE count(productQuantity) " +
            "   WHEN 0 THEN 0 " +
            "   ELSE productQuantity END " +
            "FROM ProductCart where productId = :id")
    int getProductCartQuantity(int id);

    @Query("UPDATE ProductCart SET productQuantity = :qty WHERE productId = :productId")
    void updateCartProduct(Integer productId, int qty);

    @Query("DELETE FROM ProductCart WHERE productId = :productId")
    void removeCartProduct(Integer productId);

    @Query("DELETE FROM ProductCart")
    void clearCart();

    @Query("SELECT " +
            "   CASE SUM(productPrice * productQuantity) IS NULL " +
            "   WHEN 1 THEN 0 " +
            "   ELSE SUM(productPrice * productQuantity) END " +
            "FROM ProductCart")
    LiveData<Double> getTotal();
}