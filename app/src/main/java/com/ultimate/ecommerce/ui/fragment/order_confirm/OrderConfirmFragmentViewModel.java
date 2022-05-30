package com.ultimate.ecommerce.ui.fragment.order_confirm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class OrderConfirmFragmentViewModel extends BaseViewModel {
    @Inject
    public OrderConfirmFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}