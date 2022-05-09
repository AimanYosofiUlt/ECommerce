package com.ultimate.ecommerce.repository.repos.contact_us;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ultimate.ecommerce.repository.local.tables.page.Page;
import com.ultimate.ecommerce.repository.local.tables.page.PageDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.contact_us.ContactUsData;
import com.ultimate.ecommerce.repository.server.response.contact_us.ContactUsResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class ContactUsRepo extends BaseRepo {
    @Inject
    PageDao pageDao;

    @Inject
    public ContactUsRepo() {
    }

    public void getContactUsFromApi(ResponsesCallBack<ContactUsResponse> responsesCallBack) {
        RequestBody request = BaseRequest.getBaseRequest();
        api.contactUs(request).enqueue(new ResponsesCallBack<ContactUsResponse>() {
            @Override
            public void onSuccess(ContactUsResponse response) {
                addContactUsPage(response.getData());
                responsesCallBack.onSuccess(response);
            }

            private void addContactUsPage(ContactUsData data) {
                AsyncTask.execute(() -> {
                    String jsonData = new Gson().toJson(data);
                    Page page = new Page(Page.CONTACT_US_ID, jsonData);
                    pageDao.insert(page);
                });
            }

            @Override
            public void onFailure(String state, String msg) {
                responsesCallBack.onFailure(state, msg);
            }
        });
    }

    public Page getAboutUsPageFromDB() {
        return pageDao.getContactUsPage();
    }
}
