package com.ultimate.ecommerce.ui.fragment.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.tables.auth.Auth;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.repos.auth.AuthRepo;
import com.ultimate.ecommerce.repository.repos.configuration.ConfigRepo;
import com.ultimate.ecommerce.repository.repos.setting.AppSettingRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigData;
import com.ultimate.ecommerce.repository.server.response.configuration.ConfigurationResponse;
import com.ultimate.ecommerce.repository.server.response.get_auth.GetAuthResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SplashFragmentViewModel extends BaseViewModel {
    private static final String TAG = "SplashFragmentViewModel";
    @Inject
    ConfigRepo configRepo;

    @Inject
    AuthRepo authRepo;

    LiveData<Configuration> configurationLiveData;
    MutableLiveData<ResponseState> responseMDL;

    String[] authScreens;
    int screenIndex = 0;

    @Inject
    public SplashFragmentViewModel(@NonNull Application application, ConfigRepo configRepo, AppSettingRepo settingRepo) {
        super(application);
        settingRepo.initAppSetting();
        configRepo.initConfig();
        configurationLiveData = configRepo.getConfiguration();
        responseMDL = new MutableLiveData<>();
        authScreens = new String[]{Auth.LOGIN, Auth.REGISTER};
    }

    void getConfiguration() {
        configRepo.getConfigFromApi(new ResponsesCallBack<ConfigurationResponse>() {
            @Override
            public void onSuccess(ConfigurationResponse response) {
                Configuration configuration = convertResponseToConfiguration(response);
                configRepo.saveConfig(configuration);
                getScreensStructure();
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
                responseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    private void getScreensStructure() {
        String screenName = authScreens[screenIndex];
        authRepo.getScreenAuth(screenName, new ResponsesCallBack<GetAuthResponse>() {
            @Override
            public void onSuccess(GetAuthResponse response) {
                screenIndex++;
                if (screenIndex < authScreens.length)
                    getScreensStructure();
                else
                    responseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                responseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}
