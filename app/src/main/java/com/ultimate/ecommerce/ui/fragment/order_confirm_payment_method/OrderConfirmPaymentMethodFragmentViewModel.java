package com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.payment_methods.PaymentMethodsData;
import com.ultimate.ecommerce.repository.server.response.payment_methods.PaymentMethodsResponse;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsData;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.List;

import javax.inject.Inject;

public class OrderConfirmPaymentMethodFragmentViewModel extends BaseViewModel {
    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> getPaymentMethodsResponseMDL;
    MutableLiveData<List<PaymentMethodsData>> paymentMethodsMDL;

    @Inject
    public OrderConfirmPaymentMethodFragmentViewModel(@NonNull Application application) {
        super(application);
        getPaymentMethodsResponseMDL = new MutableLiveData<>();
        paymentMethodsMDL = new MutableLiveData<>();
    }

    public void validatePaymentMethods(Context requireContext) {
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
                        getPaymentMethods();
                    }

                    @Override
                    public void onDisconnect() {
                        getPaymentMethodsResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getPaymentMethods() {
        userRepo.getPaymentMethods(new ResponsesCallBack<PaymentMethodsResponse>() {
            @Override
            public void onSuccess(PaymentMethodsResponse response) {
                List<PaymentMethodsData> data = response.getData();
                paymentMethodsMDL.setValue(data);
                getPaymentMethodsResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getPaymentMethodsResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}