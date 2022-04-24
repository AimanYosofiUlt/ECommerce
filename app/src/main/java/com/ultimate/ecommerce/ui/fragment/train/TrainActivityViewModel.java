package com.ultimate.ecommerce.ui.fragment.train;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.dataprovider.configuration.ConfigRepo;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class TrainActivityViewModel extends BaseViewModel {
    @Inject
    ConfigRepo configRepo;

    LiveData<Configuration> configurationLiveData;

    @Inject
    public TrainActivityViewModel(@NonNull Application application, ConfigRepo configRepo) {
        super(application);
        configRepo.initConfig();
        configurationLiveData = configRepo.getConfiguration();
    }

    public void saveConfigColor(String main, String sec,
                                String gsColor, String geColor,
                                String img, String review) {
        Configuration configuration = configurationLiveData.getValue();
        configuration.setMainColor(main);
        configuration.setSecondColor(sec);
        configuration.setGradientStartColor(gsColor);
        configuration.setGradientEndColor(geColor);
        configuration.setImageBackground(img);
        configuration.setReviewColor(review);
        configRepo.saveConfig(configuration);
    }
}
