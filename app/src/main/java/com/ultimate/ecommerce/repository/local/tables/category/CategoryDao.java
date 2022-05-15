package com.ultimate.ecommerce.repository.local.tables.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;

import java.util.List;

@Dao
public interface CategoryDao extends BaseDao<Category> {
    @Query("INSERT INTO Configuration " +
            "SELECT 0,'en','123456','1','phone','#37123C','#AFA8B1','#FE938C','#FFF87C','#FFEDEC','#E7E3E7'" +
            " WHERE NOT EXISTS (SELECT 1 FROM Configuration WHERE id = 0)")
    void initConfig();

    @Query("SELECT * FROM Configuration")
    LiveData<Configuration> getConfig();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateConfig(Configuration configuration);

    @Query("SELECT * FROM Category")
    List<Category> getCategories();
}

