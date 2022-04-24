package com.ultimate.ecommerce.ui.fragment.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.dataprovider.homepage.HomePageRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageData;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class HomeFragmentViewModel extends BaseViewModel {
    @Inject
    HomePageRepo homePageRepo;

    MutableLiveData<HomePageData> homePageDataMDL;
    MutableLiveData<ResponseState> responseMDL;

    @Inject
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        homePageDataMDL = new MutableLiveData<>();
        responseMDL = new MutableLiveData<>();
    }

    public void getHomePageData() {
        homePageRepo.getHomePageData(new ResponsesCallBack<HomePageResponse>() {
            @Override
            public void onSuccess(HomePageResponse response) {
                HomePageData data = response.getData();
                homePageDataMDL.setValue(data);
            }

            @Override
            public void onFailure(String state, String msg) {
                responseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}
