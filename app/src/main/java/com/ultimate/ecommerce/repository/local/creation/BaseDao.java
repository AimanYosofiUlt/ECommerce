package com.ultimate.ecommerce.repository.local.creation;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * Created by Youssif Hamdy on 2/16/2021.
 */

public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T object);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<T> object);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable update(T object);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable update(List<T> object);

    @RawQuery
    int deleteTable(SupportSQLiteQuery query);

    @Delete
    Completable delete(T object);

    @Delete
    Completable delete(List<T> object);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    Completable insertAll(List<T> objs);

    @RawQuery
    Single<String> getStringValue(SupportSQLiteQuery query);

    @RawQuery
    Single<Integer> getIntValue(SupportSQLiteQuery query);

    @RawQuery
    Single<T> runSQLquery(SupportSQLiteQuery query);

    @RawQuery
    Single<Integer> checkpoint(SupportSQLiteQuery query);
}
