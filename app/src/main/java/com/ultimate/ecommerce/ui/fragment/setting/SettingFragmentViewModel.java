package com.ultimate.ecommerce.ui.fragment.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.repos.configuration.ConfigRepo;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SettingFragmentViewModel extends BaseViewModel {
    @Inject
    ConfigRepo configRepo;

    LiveData<Configuration> configLiveData;

    @Inject
    public SettingFragmentViewModel(@NonNull Application application,ConfigRepo configRepo) {
        super(application);
        configLiveData = configRepo.getConfiguration();
    }
}