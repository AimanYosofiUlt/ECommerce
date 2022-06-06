package com.ultimate.ecommerce.ui.fragment.profile.dialogs.profile_edit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.DialogEditProfileBinding;
import com.ultimate.ecommerce.ui.base.BaseDialog;

public class ProfileEditDialog extends BaseDialog {
    DialogEditProfileBinding binding;
    ProfileEditDialogListener listener;
    Profile currentProfile;

    public ProfileEditDialog(Context context, Profile profile, ProfileEditDialogListener listener) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edit_profile, null, false);
        binding = DialogEditProfileBinding.bind(inflate);
        setView(binding.getRoot());
        this.listener = listener;
        this.currentProfile = profile;
    }

    @Override
    protected void initLoading() {
        binding.doneBtn.btnTextTV.setText(getContext().getString(R.string.save_data));
        binding.userNameED.setText(currentProfile.getName());
        binding.emailED.setText(currentProfile.getEmail());
        binding.phoneED.setText(currentProfile.getPhone());
    }

    @Override
    protected void initEvent() {
        binding.doneBtn.btnBody.setOnClickListener(v -> {
            String name = binding.userNameED.getText().toString().trim();
            String email = binding.emailED.getText().toString().trim();
            String phone = binding.phoneED.getText().toString().trim();
            Profile profile = new Profile(name, email, phone);
            listener.onSave(profile);
        });
    }
}
