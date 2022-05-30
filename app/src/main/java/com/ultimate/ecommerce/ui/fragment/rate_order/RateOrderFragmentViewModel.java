package com.ultimate.ecommerce.ui.fragment.rate_order;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.product.ProductRepo;
import com.ultimate.ecommerce.repository.server.response.add_review.AddReviewResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.ui.fragment.rate_order_inner.RateOrder;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.List;

import javax.inject.Inject;

public class RateOrderFragmentViewModel extends BaseViewModel {
    @Inject
    ProductRepo productRepo;


    MutableLiveData<ResponseState> saveRatesResponseMDL;

    @Inject
    public RateOrderFragmentViewModel(@NonNull Application application) {
        super(application);
        saveRatesResponseMDL = new MutableLiveData<>();
    }

    public void validateSaveRates(Context requireContext, int orderId, List<RateOrder> rateOrders) {
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
                        saveRates(orderId, rateOrders);
                    }

                    @Override
                    public void onDisconnect() {
                        saveRatesResponseMDL.setValue(ResponseState.failureState(NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void saveRates(int orderId, List<RateOrder> rateOrders) {
        for (RateOrder rateOrder : rateOrders) {
            productRepo.addReview(orderId, rateOrder, new ResponsesCallBack<AddReviewResponse>() {
                @Override
                public void onSuccess(AddReviewResponse response) {
                    saveRatesResponseMDL.setValue(ResponseState.successState());
                }

                @Override
                public void onFailure(String state, String msg) {
                    saveRatesResponseMDL.setValue(ResponseState.failureState(msg));
                }
            });
        }
    }
}