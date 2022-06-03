package com.ultimate.ecommerce.ui.fragment.lang_currency;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.setting.AppSettingRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class LangCurrencyFragmentViewModel extends BaseViewModel {
    @Inject
    AppSettingRepo settingRepo;
    MutableLiveData<String> languageChangeMDL;

    @Inject
    public LangCurrencyFragmentViewModel(@NonNull Application application) {
        super(application);
        languageChangeMDL = new MutableLiveData<>();
    }

    public void changeLanguage(String language) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                settingRepo.changeLanguage(language);
                languageChangeMDL.postValue(language);
            }
        });
    }
}