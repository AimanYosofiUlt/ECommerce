package com.ultimate.ecommerce.ui.fragment.order_confirm_address;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.user.UserRepo;
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

public class OrderConfirmAddressFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> getAddressFieldsResponseMDL;
    MutableLiveData<List<GetAddressFieldData>> addressFieldsMDL;

    @Inject
    public OrderConfirmAddressFragmentViewModel(@NonNull Application application) {
        super(application);
        getAddressFieldsResponseMDL = new MutableLiveData<>();
        addressFieldsMDL = new MutableLiveData<>();
    }

    public void validateGetAddressFields(Context requireContext) {
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
                        getAddressFieldsResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getAddressFields() {
        userRepo.getAddressFields(new ResponsesCallBack<GetAddressFieldsResponse>() {
            @Override
            public void onSuccess(GetAddressFieldsResponse response) {
                List<GetAddressFieldData> data = response.getData();
                addressFieldsMDL.setValue(data);
                getAddressFieldsResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getAddressFieldsResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}