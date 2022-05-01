package com.ultimate.ecommerce.ui.fragment.confirm_order;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class ConfirmOrderFragmentViewModel extends BaseViewModel {
    @Inject
    public ConfirmOrderFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}