package com.ultimate.ecommerce.ui.fragment.order_inner;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class OrderInnerFragmentViewModel extends BaseViewModel {
    @Inject
    public OrderInnerFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}