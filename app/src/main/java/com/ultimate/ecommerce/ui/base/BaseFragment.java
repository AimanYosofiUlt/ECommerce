package com.ultimate.ecommerce.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

public abstract class BaseFragment<ViewModel extends BaseViewModel> extends Fragment {
    @Inject
    public ViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObservers();
        initErrorObserver();
        initLoading();
        initEvent();
    }

    public abstract void initEvent();

    public abstract void initObservers();

    public abstract void initLoading();

    public abstract void initErrorObserver();

    public static ProgressDialog progressDialog;

    public static void showProgress(Context context, String Title, String Message) {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog = ProgressDialog.show(context, Title,
                        Message, true);
            }
        } else {
            progressDialog = ProgressDialog.show(context, Title,
                    Message, true);
        }
    }

    public static void hideProgress() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
