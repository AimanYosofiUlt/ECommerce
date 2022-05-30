package com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class OrderConfirmPaymentMethodFragmentViewModel extends BaseViewModel {
    @Inject
    public OrderConfirmPaymentMethodFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}