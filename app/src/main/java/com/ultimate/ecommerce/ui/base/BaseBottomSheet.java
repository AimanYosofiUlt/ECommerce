package com.ultimate.ecommerce.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public abstract class BaseBottomSheet extends BottomSheetDialogFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObservers();
        initErrorObserver();
        initLoading();
        initEvent();
    }

    protected abstract void initEvent();

    protected abstract void initLoading();

    protected abstract void initErrorObserver();

    protected abstract void initObservers();
}