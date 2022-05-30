package com.ultimate.ecommerce.ui.fragment.order_confirm_done;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class OrderConfirmDoneFragmentViewModel extends BaseViewModel {
    @Inject
    public OrderConfirmDoneFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}