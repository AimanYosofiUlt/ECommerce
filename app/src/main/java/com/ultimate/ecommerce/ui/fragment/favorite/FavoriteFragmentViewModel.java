package com.ultimate.ecommerce.ui.fragment.favorite;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.ui.base.BaseViewModel;

import javax.inject.Inject;

public class FavoriteFragmentViewModel extends BaseViewModel {
    @Inject
    public FavoriteFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}