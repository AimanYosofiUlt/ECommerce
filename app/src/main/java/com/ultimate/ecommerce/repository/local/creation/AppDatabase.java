package com.ultimate.ecommerce.repository.local.creation;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ultimate.ecommerce.repository.local.tables.auth.Auth;
import com.ultimate.ecommerce.repository.local.tables.auth.AuthDao;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCartDao;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.local.tables.category.CategoryDao;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.local.tables.configuration.ConfigurationDao;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.repository.local.tables.favorite.FavoriteDao;
import com.ultimate.ecommerce.repository.local.tables.page.Page;
import com.ultimate.ecommerce.repository.local.tables.page.PageDao;
import com.ultimate.ecommerce.repository.local.tables.setting.AppSetting;
import com.ultimate.ecommerce.repository.local.tables.setting.AppSettingDao;
import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.local.user.UserDao;

@Database(version = 1,
        entities = {Configuration.class, Category.class, User.class
                , AppSetting.class, Page.class, Favorite.class
                , ProductCart.class, Auth.class},
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "ECommerceDB";

    public abstract ConfigurationDao configurationDao();

    public abstract CategoryDao categoryDao();

    public abstract PageDao pageDao();

    public abstract AppSettingDao appSettingDao();

    public abstract UserDao userDao();

    public abstract FavoriteDao favoriteDao();

    public abstract ProductCartDao productCartDao();

    public abstract AuthDao authDao();

    public static AppDatabase INSTANCE = null;

    public static synchronized AppDatabase getInstance(Context context) {
        AppDatabase temp = INSTANCE;
        if (temp != null)
            return temp;

        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }
}
