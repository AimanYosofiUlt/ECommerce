package com.ultimate.ecommerce.ui.fragment.order_detail;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.order.OrderRepo;
import com.ultimate.ecommerce.repository.repos.user.UserRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_order.GetOrderData;
import com.ultimate.ecommerce.repository.server.response.get_order.GetOrderResponse;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class OrderDetailFragmentViewModel extends BaseViewModel {
    @Inject
    OrderRepo orderRepo;
    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> getDetailResponseMDL;
    MutableLiveData<GetOrderData> detailMDL;

    @Inject
    public OrderDetailFragmentViewModel(@NonNull Application application) {
        super(application);
        getDetailResponseMDL = new MutableLiveData<>();
        detailMDL = new MutableLiveData<>();
    }

    public void validateGetOrderDetail(Context context, Order order) {
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
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                String userId = userRepo.getUser().getId();
                                getOrderDetail(userId, order.getOrderId());
                            }
                        });
                    }

                    @Override
                    public void onDisconnect() {
                        getDetailResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getOrderDetail(String userId, int orderId) {
        orderRepo.getOrderDetail(userId, orderId, new ResponsesCallBack<GetOrderResponse>() {
            @Override
            public void onSuccess(GetOrderResponse response) {
                GetOrderData data = response.getData();
                detailMDL.postValue(data);
            }

            @Override
            public void onFailure(String state, String msg) {
                getDetailResponseMDL.postValue(ResponseState.failureState(msg));
            }
        });
    }
}