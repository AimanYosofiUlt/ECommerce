package com.ultimate.ecommerce.repository.local.user;

import androidx.room.Dao;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

@Dao
public interface UserDao extends BaseDao<User> {
}
