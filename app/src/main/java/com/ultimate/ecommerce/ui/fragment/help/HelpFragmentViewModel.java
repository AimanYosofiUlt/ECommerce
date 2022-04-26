package com.ultimate.ecommerce.ui.fragment.help;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.ultimate.ecommerce.repository.local.tables.page.Page;
import com.ultimate.ecommerce.repository.repos.help.HelpRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.help.HelpData;
import com.ultimate.ecommerce.repository.server.response.help.HelpResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class HelpFragmentViewModel extends BaseViewModel {
    @Inject
    HelpRepo helpRepo;

    MutableLiveData<ResponseState> responseMDL;
    MutableLiveData<HelpData> helpMDL;

    @Inject
    public HelpFragmentViewModel(@NonNull Application application) {
        super(application);
        responseMDL = new MutableLiveData<>();
        helpMDL = new MutableLiveData<>();
    }

    public void getHelpPage() {
        helpRepo.getHelpPageFromApi(new ResponsesCallBack<HelpResponse>() {
            @Override
            public void onSuccess(HelpResponse response) {
                responseMDL.setValue(ResponseState.successState());
                getHelpPageFromDB();
            }

            @Override
            public void onFailure(String state, String msg) {
                responseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    private void getHelpPageFromDB() {
        AsyncTask.execute(() -> {
            Page page = helpRepo.getHelpPageFromDB();
            HelpData helpData = new Gson().fromJson(page.getJsonData(), HelpData.class);
            helpMDL.postValue(helpData);
        });
    }
}