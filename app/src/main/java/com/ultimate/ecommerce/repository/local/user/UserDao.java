package com.ultimate.ecommerce.repository.local.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

@Dao
public interface UserDao extends BaseDao<User> {
    @Query("SELECT COUNT(id) >= 1 FROM User")
    LiveData<Boolean> isUserLogin();

    @Query("SELECT COUNT(id) >= 1 FROM User")
    Boolean isUserLoginCheck();

    @Query("SELECT id From User")
    String getUserId();

    @Query("SELECT userPhone From User")
    String getUserPhone();

    @Query("DELETE FROM User")
    void deleteCurrentUser();

    @Query("SELECT tokenKey FROM User")
    String getTokenKey();

    @Query("SELECT * FROM User")
    User getUser();

    @Query("SELECT * FROM User")
    LiveData<User> getUserProfile();

    @Query("UPDATE User SET userName = :name,userEmail = :email")
    void updateProfile(String name, String email);
}
