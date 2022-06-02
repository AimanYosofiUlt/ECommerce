package com.ultimate.ecommerce.repository.local.tables.setting;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

@Dao
public interface AppSettingDao extends BaseDao<AppSetting> {
    @Query("INSERT INTO AppSetting SELECT 0, '-1' WHERE NOT EXISTS (SELECT 1 FROM AppSetting WHERE id = 0)")
    void initAppSetting();

    @Query("SELECT * FROM AppSetting")
    LiveData<AppSetting> getAppSetting();
}
