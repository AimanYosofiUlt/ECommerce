package com.ultimate.ecommerce.repository.dataprovider.homepage;

import android.content.Context;

import com.ultimate.ecommerce.repository.dataprovider.base.BaseDataProvider;
import com.ultimate.ecommerce.repository.server.remote.UltimateApi;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class HomePageRepo extends BaseDataProvider {
    @Inject
    UltimateApi api;

    @Inject
    public HomePageRepo(Context context) {
        super(context);
    }


    public void getHomePageData(ResponsesCallBack<HomePageResponse> responsesCallBack) {
        RequestBody request = BaseRequest.getHomePageRequest("1");
        api.getHomePage(request).enqueue(responsesCallBack);
    }
}
