package com.ultimate.ecommerce.app.di;

import android.app.Application;
import android.content.Context;

import com.ultimate.ecommerce.repository.local.creation.AppDatabase;
import com.ultimate.ecommerce.repository.local.tables.category.CategoryDao;
import com.ultimate.ecommerce.repository.local.tables.configuration.ConfigurationDao;
import com.ultimate.ecommerce.repository.local.tables.page.PageDao;
import com.ultimate.ecommerce.repository.local.tables.setting.AppSetting;
import com.ultimate.ecommerce.repository.local.tables.setting.AppSettingDao;
import com.ultimate.ecommerce.repository.local.user.UserDao;
import com.ultimate.ecommerce.repository.server.remote.UltimateApi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ActivityComponent.class)
public abstract class AppModule {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    private @interface UltimateApiBaseLink {
    }

    @ActivityScoped
    @Provides
    public static Context provideContext(Application application) {
        return application;
    }

    @UltimateApiBaseLink
    @Provides
    public static String provideBaseUrl() {
        return "https://samir.ultimate-demos.com/wp-json/app/v1/";
    }


    @ActivityScoped
    @Provides
    public static HttpLoggingInterceptor provideHttpLogging() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @ActivityScoped
    @Provides
    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
    }

    @ActivityScoped
    @Provides
    public static Retrofit provideRetrofit(@UltimateApiBaseLink String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @ActivityScoped
    @Provides
    public static UltimateApi provideUltimateApi(Retrofit retrofit) {
        return retrofit.create(UltimateApi.class);
    }

    @ActivityScoped
    @Provides
    public static AppDatabase provideAppDatabase(Application application) {
        return AppDatabase.getInstance(application);
    }

    @ActivityScoped
    @Provides
    public static ConfigurationDao provideConfigurationDao(AppDatabase appDatabase) {
        return appDatabase.configurationDao();
    }

    @ActivityScoped
    @Provides
    public static CategoryDao provideCategoryDao(AppDatabase appDatabase) {
        return appDatabase.categoryDao();
    }

    @ActivityScoped
    @Provides
    public static AppSettingDao provideAppSetting(AppDatabase appDatabase) {
        return appDatabase.appSettingDao();
    }

    @ActivityScoped
    @Provides
    public static PageDao providePageDao(AppDatabase appDatabase) {
        return appDatabase.pageDao();
    }

    @ActivityScoped
    @Provides
    public static UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }
}
