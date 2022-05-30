package com.ultimate.ecommerce.ui.fragment.order_confirm_check;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class OrderConfirmCheckFragmentViewModel extends BaseViewModel {
    @Inject
    public OrderConfirmCheckFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}