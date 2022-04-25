package com.ultimate.ecommerce.repository.local.creation;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.local.tables.category.CategoryDao;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.local.tables.configuration.ConfigurationDao;

@Database(version = 1,
        entities = {Configuration.class, Category.class},
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ConfigurationDao configurationDao();
    public abstract CategoryDao categoryDao();

    public static AppDatabase INSTANCE = null;

    public static synchronized AppDatabase getInstance(Context context) {
        AppDatabase temp = INSTANCE;
        if (temp != null)
            return temp;

        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "ECommerceDB"
        ).build();
    }
}
