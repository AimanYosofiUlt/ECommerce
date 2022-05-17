package com.ultimate.ecommerce.ui.fragment.profile.dialogs.change_password;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.DialogEditPasswordBinding;

import javax.inject.Inject;

public class ChangePasswordDialog extends AlertDialog.Builder {
    DialogEditPasswordBinding bd;
    @Inject
    ChangePasswordDialogViewModel viewModel;

    public ChangePasswordDialog(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edit_password, null, false);
        bd = DialogEditPasswordBinding.bind(inflate);
        setView(bd.getRoot());
        initObservers();
        initErrorObserver();
        initLoading();
        initEvent();
    }

    private void initObservers() {

    }

    private void initErrorObserver() {

    }

    private void initLoading() {

    }

    private void initEvent() {
        bd.doneBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPassword = bd.currentPasswordED.getText().toString();
                String newPassword = bd.newPasswordED.getText().toString();
                String confirmPassword = bd.confirmPasswordED.getText().toString();
                viewModel.validateChangePassword(getContext(),currentPassword,newPassword,confirmPassword);
            }
        });
    }
}
