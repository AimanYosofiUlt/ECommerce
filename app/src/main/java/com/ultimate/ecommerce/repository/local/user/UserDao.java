package com.ultimate.ecommerce.repository.local.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

@Dao
public interface UserDao extends BaseDao<User> {
    @Query("SELECT COUNT(id) >= 1 FROM User")
    LiveData<Boolean> isUserLogin();

    @Query("SELECT id From User")
    Integer getUserId();

    @Query("SELECT userPhone From User")
    String getUserPhone();
}
