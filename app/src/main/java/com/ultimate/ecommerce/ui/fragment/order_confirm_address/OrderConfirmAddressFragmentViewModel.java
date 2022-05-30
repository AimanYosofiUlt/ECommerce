package com.ultimate.ecommerce.ui.fragment.order_confirm_address;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class OrderConfirmAddressFragmentViewModel extends BaseViewModel {
    @Inject
    public OrderConfirmAddressFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}