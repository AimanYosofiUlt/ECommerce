package com.ultimate.ecommerce.repository.repos.about_us;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ultimate.ecommerce.repository.local.tables.page.Page;
import com.ultimate.ecommerce.repository.local.tables.page.PageDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.about_us.AboutUsData;
import com.ultimate.ecommerce.repository.server.response.about_us.AboutUsResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class AboutUsRepo extends BaseRepo {
    @Inject
    PageDao pageDao;

    @Inject
    public AboutUsRepo() {

    }

    public void getAboutUsPageFromApi(ResponsesCallBack<AboutUsResponse> callBack) {
        RequestBody request = BaseRequest.getBaseRequest();
        api.aboutUs(request).enqueue(new ResponsesCallBack<AboutUsResponse>() {
            @Override
            public void onSuccess(AboutUsResponse response) {
                addAboutUsPage(response.getData());
                callBack.onSuccess(response);
            }

            private void addAboutUsPage(AboutUsData data) {
                AsyncTask.execute(() -> {
                    String jsonData = new Gson().toJson(data);
                    Page page = new Page(Page.ABOUT_US_ID, jsonData);
                    pageDao.insert(page);
                });
            }

            @Override
            public void onFailure(String state, String msg) {
                callBack.onFailure(state, msg);
            }
        });
    }


    public Page getAboutUsPageFromDB() {
        return pageDao.getAboutUsPage();
    }
}
