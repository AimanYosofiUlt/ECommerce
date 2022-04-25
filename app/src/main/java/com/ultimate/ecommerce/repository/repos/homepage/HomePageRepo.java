package com.ultimate.ecommerce.repository.repos.homepage;

import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class HomePageRepo extends BaseRepo {

    @Inject
    public HomePageRepo() {
    }


    public void getHomePageData(ResponsesCallBack<HomePageResponse> responsesCallBack) {
        RequestBody request = BaseRequest.getHomePageRequest("1");
        api.getHomePage(request).enqueue(responsesCallBack);
    }
}
