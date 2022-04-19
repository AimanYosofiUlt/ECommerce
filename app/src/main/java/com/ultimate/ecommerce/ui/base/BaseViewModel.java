package com.ultimate.ecommerce.ui.base;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import javax.inject.Inject;

public class BaseViewModel extends AndroidViewModel {
    @Inject
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
