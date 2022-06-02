package com.ultimate.ecommerce.ui.fragment.profile;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;
import static com.ultimate.ecommerce.utilities.ValidateSt.PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.PHONE_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.SMALL_PASSWORD_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.WRONG_PASSWORD_ERROR;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentProfileBinding;
import com.ultimate.ecommerce.repository.local.user.User;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.change_password.ChangePasswordDialog;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.change_password.ChangePasswordDialogListener;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.profile_edit.Profile;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.profile_edit.ProfileEditDialog;
import com.ultimate.ecommerce.ui.fragment.profile.dialogs.profile_edit.ProfileEditDialogListener;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends BaseFragment<ProfileFragmentViewModel> {
    FragmentProfileBinding binding;
    AlertDialog changePasswordDialog;
    ChangePasswordDialog passwordDialogBuilder;

    AlertDialog profileDialog;
    ProfileEditDialog profileEditDialogBuilder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = com.ultimate.ecommerce.databinding.FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.editBtn.btnBody.setOnClickListener(v -> {
            String name = binding.userNameED.getText().toString();
            String email = binding.emailED.getText().toString();
            String phone = binding.phoneED.getText().toString();
            Profile profile = new Profile(name, email, phone);
            profileEditDialogBuilder = new ProfileEditDialog(requireContext(), profile, profile1 -> {
                showProgress(requireContext(), getString(R.string.profile_edit), getString(R.string.loading));
                viewModel.validateUpdateProfile(requireContext(), profile1);
            });

            profileDialog = profileEditDialogBuilder.create();
            profileDialog.show();
        });

        binding.changePasswordBtn.setOnClickListener(v -> {
            passwordDialogBuilder = new ChangePasswordDialog(requireContext(), (currentPassword, newPassword, confirmPassword) -> {
                showProgress(requireContext(), getString(R.string.change_password), getString(R.string.loading));
                viewModel.validateChangePassword(requireContext(), currentPassword, newPassword, confirmPassword);
            });

            changePasswordDialog = passwordDialogBuilder.create();
            changePasswordDialog.show();
        });
    }

    @Override
    public void initObservers() {
        viewModel.profileLiveData.observe(getViewLifecycleOwner(), user -> {
            binding.userNameED.setText(user.getUserName());
            String phone = "+" + user.getUserPhone();
            binding.phoneED.setText(phone);
            binding.emailED.setText(user.getUserEmail());
        });

        viewModel.userMDL.observe(getViewLifecycleOwner(), userData -> {
            binding.userNameED.setText(userData.getDisplayName());
            binding.phoneED.setText(userData.getUserEmail());
            binding.emailED.setText(userData.getUserLogin());
        });

        viewModel.getUserProfileResponseMDL.observe(getViewLifecycleOwner(), responseState -> Log.d("ProfileFragment", "onChanged: 35445: " + responseState.getMessage()));

        viewModel.changePasswordResponseMDL.observe(getViewLifecycleOwner(), responseState -> {
            hideProgress();
            if (responseState.isSuccessful()) {
                if (changePasswordDialog != null)
                    changePasswordDialog.cancel();
            }
        });
    }

    private void handleValidateError(String message) {
        if (NO_INTERNET_CONNECTION.equals(message)) {
            Toast.makeText(requireContext(), getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        } else {
            passwordDialogBuilder.handleValidateError(message);
        }
    }

    @Override
    public void initLoading() {
        binding.editBtn.btnTextTV.setText(getString(R.string.profile_edit));
    }

    @Override
    public void initErrorObserver() {
        viewModel.validateChangeResponseMDL.observe(getViewLifecycleOwner()
                , responseState -> {
                    hideProgress();
                    handleValidateError(responseState.getMessage());
                });
    }
}


