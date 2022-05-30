package com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldsResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class OrderConfirmShipmentMethodFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> getUserAddressMDL;

    @Inject
    public OrderConfirmShipmentMethodFragmentViewModel(@NonNull Application application) {
        super(application);
        getUserAddressMDL = new MutableLiveData<>();
    }

    public void validateGetUserAddress(Context context) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        getUserAddress();
                    }

                    @Override
                    public void onDisconnect() {
                        getUserAddressMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getUserAddress() {
        userRepo.getUserAddress(new ResponsesCallBack<GetAddressFieldsResponse>() {
            @Override
            public void onSuccess(GetAddressFieldsResponse response) {
                getUserAddressMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getUserAddressMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}