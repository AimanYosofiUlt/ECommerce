package com.ultimate.ecommerce.ui.fragment.order_confirm;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.order.OrderRepo;
import com.ultimate.ecommerce.repository.server.request.create_order.Args;
import com.ultimate.ecommerce.repository.server.request.create_order.OrderProducts;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.create_order.CreateOrderResponse;
import com.ultimate.ecommerce.repository.server.response.update_cart.Products;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartData;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OrderConfirmFragmentViewModel extends BaseViewModel {
    @Inject
    OrderRepo orderRepo;

    MutableLiveData<ResponseState> payCartResponseMDL;

    @Inject
    public OrderConfirmFragmentViewModel(@NonNull Application application) {
        super(application);
        payCartResponseMDL = new MutableLiveData<>();
    }

    public void validatePayCart(Context requireContext, UpdateCartData cartData) {
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
                        payCart(cartData);
                    }

                    @Override
                    public void onDisconnect() {
                        payCartResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void payCart(UpdateCartData cartData) {
        List<OrderProducts> orderProducts = convertCartToOrderProducts(cartData);
        orderRepo.createOrder(orderProducts, new ResponsesCallBack<CreateOrderResponse>() {
            @Override
            public void onSuccess(CreateOrderResponse response) {
                payCartResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                payCartResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    private List<OrderProducts> convertCartToOrderProducts(UpdateCartData cartData) {
        List<OrderProducts> orderProducts = new ArrayList<>();
        for (Products product : cartData.getProducts()) {
            Args args = new Args(product.getID());
            OrderProducts temp = new OrderProducts(args, product.getStockQuantity());
            orderProducts.add(temp);
        }
        return orderProducts;
    }
}