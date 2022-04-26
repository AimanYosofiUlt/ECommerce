package com.ultimate.ecommerce.repository.repos.help;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ultimate.ecommerce.repository.local.tables.page.Page;
import com.ultimate.ecommerce.repository.local.tables.page.PageDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.help.HelpData;
import com.ultimate.ecommerce.repository.server.response.help.HelpResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class HelpRepo extends BaseRepo {
    @Inject
    PageDao pageDao;

    @Inject
    public HelpRepo() {
    }

    public void getHelpPageFromApi(ResponsesCallBack<HelpResponse> callBack) {
        RequestBody request = BaseRequest.getBaseRequest();
        api.help(request).enqueue(new ResponsesCallBack<HelpResponse>() {
            @Override
            public void onSuccess(HelpResponse response) {
                addHelpPage(response.getData());
                callBack.onSuccess(response);
            }

            private void addHelpPage(HelpData data) {
                AsyncTask.execute(() -> {
                    String jsonData = new Gson().toJson(data);
                    Page page = new Page(Page.HELP_US_ID, jsonData);
                    pageDao.insert(page);
                });
            }

            @Override
            public void onFailure(String state, String msg) {
                callBack.onFailure(state, msg);
            }
        });
    }

    public Page getHelpPageFromDB() {
        return pageDao.getHelpPage();
    }
}
