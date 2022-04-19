package com.ultimate.ecommerce.repository.local.creation;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ultimate.ecommerce.repository.local.tables.ToDeleteEntity;

@Database(version = 1,
        entities = {ToDeleteEntity.class},
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


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
