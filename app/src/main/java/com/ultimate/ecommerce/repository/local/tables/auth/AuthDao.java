package com.ultimate.ecommerce.repository.local.tables.auth;

import androidx.room.Dao;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

@Dao
public interface AuthDao extends BaseDao<Auth> {

    @Query("SELECT jsonData FROM Auth where screenName =:screen")
    String getAuth(String screen);
}
