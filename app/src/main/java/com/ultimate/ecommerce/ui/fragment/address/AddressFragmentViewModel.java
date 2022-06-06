package com.ultimate.ecommerce.ui.fragment.address;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.address.AddressRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldData;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldsResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.List;

import javax.inject.Inject;

public class AddressFragmentViewModel extends BaseViewModel {
    @Inject
    AddressRepo addressRepo;
    MutableLiveData<List<GetAddressFieldData>> addressMDL;
    MutableLiveData<ResponseState> getAddressFieldsResponseMDL;

    @Inject
    public AddressFragmentViewModel(@NonNull Application application) {
        super(application);
        addressMDL = new MutableLiveData<>();
        getAddressFieldsResponseMDL = new MutableLiveData<>();
    }

    public void validateGetAddress(Context requireContext) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(requireContext, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        getAddressFields();
                    }

                    @Override
                    public void onDisconnect() {
                        getAddressFieldsResponseMDL.setValue(ResponseState.failureState(ValidateSt.NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getAddressFields() {
        addressRepo.getAddressFields(new ResponsesCallBack<GetAddressFieldsResponse>() {
            @Override
            public void onSuccess(GetAddressFieldsResponse response) {
                List<GetAddressFieldData> data = response.getData();
                addressMDL.setValue(data);
                getAddressFieldsResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getAddressFieldsResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}