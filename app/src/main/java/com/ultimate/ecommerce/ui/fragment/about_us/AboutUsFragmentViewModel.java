package com.ultimate.ecommerce.ui.fragment.about_us;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.ultimate.ecommerce.repository.local.tables.page.Page;
import com.ultimate.ecommerce.repository.repos.about_us.AboutUsRepo;
import com.ultimate.ecommerce.repository.server.response.about_us.AboutUsData;
import com.ultimate.ecommerce.repository.server.response.about_us.AboutUsResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class AboutUsFragmentViewModel extends BaseViewModel {
    @Inject
    AboutUsRepo aboutUsRepo;

    MutableLiveData<AboutUsData> aboutUsPageMDL;
    MutableLiveData<ResponseState> responseStateMDL;

    @Inject
    public AboutUsFragmentViewModel(@NonNull Application application) {
        super(application);
        aboutUsPageMDL = new MutableLiveData<>();
        responseStateMDL = new MutableLiveData<>();
    }

    public void getAboutUsPage() {
        aboutUsRepo.getAboutUsPageFromApi(new ResponsesCallBack<AboutUsResponse>() {
            @Override
            public void onSuccess(AboutUsResponse response) {
                responseStateMDL.setValue(ResponseState.successState());
                getAboutUsPageFromDB();
            }

            @Override
            public void onFailure(String state, String msg) {
                responseStateMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    private void getAboutUsPageFromDB() {
        AsyncTask.execute(() -> {
            Page page = aboutUsRepo.getAboutUsPageFromDB();
            AboutUsData aboutUsData = new Gson().fromJson(page.getJsonData(), AboutUsData.class);
            aboutUsPageMDL.postValue(aboutUsData);
        });
    }
}