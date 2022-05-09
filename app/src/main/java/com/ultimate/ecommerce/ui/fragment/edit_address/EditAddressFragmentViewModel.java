package com.ultimate.ecommerce.ui.fragment.edit_address;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class EditAddressFragmentViewModel extends BaseViewModel {
    @Inject
    public EditAddressFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}