package com.ultimate.ecommerce.repository.repos.setting;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.setting.AppSetting;
import com.ultimate.ecommerce.repository.local.tables.setting.AppSettingDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;

import javax.inject.Inject;

public class AppSettingRepo extends BaseRepo {
    @Inject
    AppSettingDao settingDao;

    @Inject
    public AppSettingRepo() {
    }

    public void initAppSetting() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                settingDao.initAppSetting();
            }
        });
    }

    public LiveData<AppSetting> getAppSetting() {
        return settingDao.getAppSetting();
    }

    public void changeLanguage(String language) {
        settingDao.changeLanguage(language);
    }
}
