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
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsData;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.List;

import javax.inject.Inject;

public class OrderConfirmShipmentMethodFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> getShipmentMethodsResponseMDL;
    MutableLiveData<List<ShippingMethodsData>> shipmentMethodsMDL;

    @Inject
    public OrderConfirmShipmentMethodFragmentViewModel(@NonNull Application application) {
        super(application);
        getShipmentMethodsResponseMDL = new MutableLiveData<>();
        shipmentMethodsMDL = new MutableLiveData<>();
    }

    public void validateShipmentMethods(Context requireContext) {
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
                        getShipmentMethods();
                    }

                    @Override
                    public void onDisconnect() {
                        getShipmentMethodsResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getShipmentMethods() {
        userRepo.getShipmentMethods(new ResponsesCallBack<ShippingMethodsResponse>() {
            @Override
            public void onSuccess(ShippingMethodsResponse response) {
                List<ShippingMethodsData> data = response.getData();
                shipmentMethodsMDL.setValue(data);
                getShipmentMethodsResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getShipmentMethodsResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}