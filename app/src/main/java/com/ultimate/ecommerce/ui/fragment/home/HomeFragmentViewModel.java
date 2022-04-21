package com.ultimate.ecommerce.ui.fragment.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ultimate.ecommerce.repository.dataprovider.base.BaseDataProvider;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class HomeFragmentViewModel extends BaseViewModel {
    @Inject
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}
