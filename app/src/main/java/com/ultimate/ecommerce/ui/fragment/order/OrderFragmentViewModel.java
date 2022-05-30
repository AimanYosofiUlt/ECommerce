package com.ultimate.ecommerce.ui.fragment.order;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.order.OrderRepo;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.GetUserOrdersData;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.GetUserOrdersResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class OrderFragmentViewModel extends BaseViewModel {
    @Inject
    OrderRepo orderRepo;
    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> getOrdersResponseMDL;
    MutableLiveData<GetUserOrdersData> ordersMDL;

    @Inject
    public OrderFragmentViewModel(@NonNull Application application) {
        super(application);
        getOrdersResponseMDL = new MutableLiveData<>();
        ordersMDL = new MutableLiveData<>();
    }

    public void validateGetOrders(Context requireContext) {
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
                        getOrders();
                    }

                    @Override
                    public void onDisconnect() {
                        getOrdersResponseMDL.setValue(ResponseState.failureState(ValidateSt.NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getOrders() {
        orderRepo.getOrders(new ResponsesCallBack<GetUserOrdersResponse>() {
            @Override
            public void onSuccess(GetUserOrdersResponse response) {
                getOrdersResponseMDL.setValue(ResponseState.successState());
                ordersMDL.setValue(response.getData());
            }

            @Override
            public void onFailure(String state, String msg) {
                getOrdersResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}