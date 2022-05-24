package com.ultimate.ecommerce.ui.fragment.profile.dialogs.change_password;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.DialogEditPasswordBinding;
import com.ultimate.ecommerce.ui.base.BaseDialog;

public class ChangePasswordDialog extends BaseDialog {
    DialogEditPasswordBinding bd;

    public ChangePasswordDialog(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edit_password, null, false);
        bd = DialogEditPasswordBinding.bind(inflate);
        setView(bd.getRoot());
    }

    @Override
    protected void initLoading() {

    }

    @Override
    protected void initEvent() {
        bd.doneBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String currentPassword = bd.currentPasswordED.getText().toString();
//                String newPassword = bd.newPasswordED.getText().toString();
//                String confirmPassword = bd.confirmPasswordED.getText().toString();
//                viewModel.validateChangePassword(getContext(), currentPassword, newPassword, confirmPassword);
            }
        });
    }
}
