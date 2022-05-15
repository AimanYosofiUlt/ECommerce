package com.ultimate.ecommerce.ui.fragment.contact_us;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.ultimate.ecommerce.repository.local.tables.page.Page;
import com.ultimate.ecommerce.repository.repos.contact_us.ContactUsRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.contact_us.ContactUsData;
import com.ultimate.ecommerce.repository.server.response.contact_us.ContactUsResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class ContactUsFragmentViewModel extends BaseViewModel {
    @Inject
    ContactUsRepo contactUsRepo;
    MutableLiveData<ContactUsData> contactUsDataMDL;

    MutableLiveData<ResponseState> responseMDL;

    @Inject
    public ContactUsFragmentViewModel(@NonNull Application application) {
        super(application);
        contactUsDataMDL = new MutableLiveData<>();
        responseMDL = new MutableLiveData<>();
    }

    public void getContactUs() {
        contactUsRepo.getContactUsFromApi(new ResponsesCallBack<ContactUsResponse>() {
            @Override
            public void onSuccess(ContactUsResponse response) {
                responseMDL.setValue(ResponseState.successState());
                getContactUsPageFromDB();
            }

            @Override
            public void onFailure(String state, String msg) {
                responseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    private void getContactUsPageFromDB() {
        AsyncTask.execute(() -> {
            Page page = contactUsRepo.getAboutUsPageFromDB();
            ContactUsData contactUsData = new Gson().fromJson(page.getJsonData(), ContactUsData.class);
            contactUsDataMDL.postValue(contactUsData);
        });
    }
}
