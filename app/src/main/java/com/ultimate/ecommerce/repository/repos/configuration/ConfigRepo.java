package com.ultimate.ecommerce.repository.repos.configuration;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.local.tables.configuration.ConfigurationDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigurationResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class ConfigRepo extends BaseRepo {
    @Inject
    ConfigurationDao dao;

    @Inject
    public ConfigRepo() {
    }

    public LiveData<Configuration> getConfiguration() {
        return dao.getConfig();
    }

    public void initConfig() {
        AsyncTask.execute(() -> {
            dao.initConfig();
        });
    }

    public void saveConfig(Configuration configuration) {
        AsyncTask.execute(() -> dao.update(configuration));
    }

    public void getConfigFromApi(ResponsesCallBack<ConfigurationResponse> callBack) {
        RequestBody requestBody = BaseRequest.getRequestByUserID("-1");
        api.getConfiguration(requestBody).enqueue(callBack);
    }
}
