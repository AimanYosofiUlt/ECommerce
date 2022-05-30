package com.ultimate.ecommerce.ui.fragment.address_edit;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class AddressEditFragmentViewModel extends BaseViewModel {
    @Inject
    public AddressEditFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}