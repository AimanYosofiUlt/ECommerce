package com.ultimate.ecommerce.ui.fragment.splash;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.repos.configuration.ConfigRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigData;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigurationResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SplashFragmentViewModel extends BaseViewModel {
    private static final String TAG = "SplashFragmentViewModel";
    @Inject
    ConfigRepo configRepo;

    LiveData<Configuration> configurationLiveData;
    MutableLiveData<ResponseState> responseMDL;

    @Inject
    public SplashFragmentViewModel(@NonNull Application application, ConfigRepo configRepo) {
        super(application);
        configRepo.initConfig();
        configurationLiveData = configRepo.getConfiguration();
        responseMDL = new MutableLiveData<>();
    }

    void getConfiguration() {
        configRepo.getConfigFromApi(new ResponsesCallBack<ConfigurationResponse>() {
            @Override
            public void onSuccess(ConfigurationResponse response) {
                Log.d("SplashFragmentViewModel", "onSuccess: 845132.");
                Configuration configuration = convertResponseToConfiguration(response);
                configRepo.saveConfig(configuration);
                responseMDL.setValue(ResponseState.successState());
            }

            private Configuration convertResponseToConfiguration(ConfigurationResponse response) {
                ConfigData configData = response.getData();
                return new Configuration(configData.getDefaultLanguage()
                        , configData.getTokenKey()
                        , configData.getCountriesVersion()
                        , configData.getLoginField()
                        , configData.getColors().getMainColor()
                        , configData.getColors().getSecondColor()
                        , configData.getColors().getGradientStartColor()
                        , configData.getColors().getGradientEndColor()
                        , configData.getColors().getImageBackground()
                        , configData.getColors().getReviewColor());
            }

            @Override
            public void onFailure(String state, String msg) {
                Log.d("SplashFragmentViewModel", "onFailure: With State{" + state + "}:" + msg);
                responseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}
