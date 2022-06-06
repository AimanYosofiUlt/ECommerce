package com.ultimate.ecommerce.ui.fragment.profile.dialogs.change_password;

import static com.ultimate.ecommerce.utilities.ValidateSt.CONFIRM_NEW_PASSWORD_NO_SAME_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.CONFIRM_PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NEW_PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.SMALL_NEW_PASSWORD_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.WRONG_PASSWORD_ERROR;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.DialogEditPasswordBinding;
import com.ultimate.ecommerce.ui.base.BaseDialog;

public class ChangePasswordDialog extends BaseDialog {
    DialogEditPasswordBinding binding;
    ChangePasswordDialogListener listener;

    public ChangePasswordDialog(Context context, ChangePasswordDialogListener listener) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edit_password, null, false);
        binding = DialogEditPasswordBinding.bind(inflate);
        setView(binding.getRoot());
        this.listener = listener;
    }

    @Override
    protected void initLoading() {

    }

    @Override
    protected void initEvent() {
        binding.doneBtn.btnBody.setOnClickListener(view -> {
            String currentPassword = binding.currentPasswordED.getText().toString();
            String newPassword = binding.newPasswordED.getText().toString();
            String confirmPassword = binding.confirmPasswordED.getText().toString();
            EPassword ePassword = new EPassword(currentPassword, newPassword, confirmPassword);
            listener.onApply(currentPassword, ePassword);
        });
    }

    public void handleValidateError(String message) {
        switch (message) {
            case PASSWORD_EMPTY_FILED_ERROR:
                binding.currentPasswordED.setError(getContext().getString(R.string.empty_password_error));
                break;

            case WRONG_PASSWORD_ERROR:
                binding.currentPasswordED.setError(getContext().getString(R.string.wron_password_error));
                break;

            case NEW_PASSWORD_EMPTY_FILED_ERROR:
                binding.newPasswordED.setError(getContext().getString(R.string.empty_new_phone_error));
                break;

            case SMALL_NEW_PASSWORD_FILED_ERROR:
                binding.newPasswordED.setError(getContext().getString(R.string.small_password_error));
                break;

            case CONFIRM_PASSWORD_EMPTY_FILED_ERROR:
                binding.confirmPasswordED.setError(getContext().getString(R.string.empty_confirm_phone_error));
                break;

            case CONFIRM_NEW_PASSWORD_NO_SAME_ERROR:
                binding.confirmPasswordED.setError(getContext().getString(R.string.new_and_confrim_password_no_same));
                break;

            default:
                Log.d("RegisterFragment", "HandleValidateError: You forget to handle this error :" + message);
        }
    }
}
