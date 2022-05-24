package com.ultimate.ecommerce.ui.base;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;

import javax.inject.Inject;

public abstract class BaseDialog extends AlertDialog.Builder {
    protected AlertDialog dialog;

    protected BaseDialog(Context context) {
        super(context);
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        AlertDialog.Builder builder = super.setView(view);
        initLoading();
        initEvent();
        return builder;
    }

    abstract protected void initLoading();

    abstract protected void initEvent();
}
