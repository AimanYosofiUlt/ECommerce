package com.ultimate.ecommerce.ui.base;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public abstract class BaseDialog extends AlertDialog.Builder {
    protected AlertDialog dialog;

    protected BaseDialog(Context context) {
        super(context);
        setCancelable(true);
    }


    @Override
    public AlertDialog create() {
        dialog = super.create();
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initLoading();
        initEvent();
        return dialog;
    }

    abstract protected void initLoading();

    abstract protected void initEvent();
}
