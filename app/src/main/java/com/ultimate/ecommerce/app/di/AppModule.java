package com.ultimate.ecommerce.app.di;

import android.app.Application;

import com.ultimate.ecommerce.repository.local.creation.AppDatabase;
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
    private @interface UltimateApiBaseLink{

    }

    @UltimateApiBaseLink
    @Provides
    public static String provideBaseUrl() {
        return "https://ultimate-demos.com/app/wp-json/app/v1/";
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
}
