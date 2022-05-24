package com.ultimate.ecommerce.ui.base;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

public abstract class BaseDialog extends AlertDialog.Builder {
    protected AlertDialog dialog;

    protected BaseDialog(Context context) {
        super(context);
        setCancelable(true);
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
