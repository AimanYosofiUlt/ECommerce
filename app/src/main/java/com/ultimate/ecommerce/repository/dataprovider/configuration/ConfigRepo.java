package com.ultimate.ecommerce.repository.dataprovider.configuration;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.dataprovider.base.BaseDataProvider;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.local.tables.configuration.ConfigurationDao;
import com.ultimate.ecommerce.repository.server.remote.UltimateApi;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigurationResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class ConfigRepo extends BaseDataProvider {
    @Inject
    UltimateApi api;

    @Inject
    ConfigurationDao dao;

    @Inject
    public ConfigRepo(Context context) {
        super(context);
    }

    public LiveData<Configuration> getConfig() {
        return dao.getConfig();
    }

    public void initConfig() {
        AsyncTask.execute(() -> {
            dao.initConfig();
        });
    }

    public void saveConfig(Configuration configuration) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateConfig(configuration);
            }
        });
    }

    public void getConfigFromApi(ResponsesCallBack<ConfigurationResponse> callBack) {
        RequestBody requestBody = BaseRequest.getConfigurationRequest("1");
        api.getConfiguration(requestBody)
                .enqueue(new ResponsesCallBack<ConfigurationResponse>() {
                    @Override
                    public void onSuccess(ConfigurationResponse response) {

                    }

                    @Override
                    public void onFailure(String state, String msg) {

                    }
                });
    }
}
