package com.ultimate.ecommerce.ui.fragment.address;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class AddressFragmentViewModel extends BaseViewModel {
    @Inject
    public AddressFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}