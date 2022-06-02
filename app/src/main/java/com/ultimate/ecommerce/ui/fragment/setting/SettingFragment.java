package com.ultimate.ecommerce.ui.fragment.setting;

import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.LOGIN_REGISTER;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.USER_PROFILE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentSettingBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.setting.views.settingview.SettingViewAdapter;
import com.ultimate.ecommerce.ui.fragment.setting.views.settingview.SettingViewListener;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingFragment extends BaseFragment<SettingFragmentViewModel> {
    FragmentSettingBinding binding;
    SettingViewAdapter adapter;

    SettingViewListener listener;

    public SettingFragment(SettingViewListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void initObservers() {
        viewModel.userIsLoginLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isUserLogin) {
                if (isUserLogin) {
                    viewModel.getCurrentUser();
                    showUserInfo();
                    adapter.addUserSettingItems(requireContext());
                } else {
                    showLoginButton();
                    adapter.addVisitorSettingItems(requireContext());
                }
            }

            private void showLoginButton() {
                binding.userInfoGroup.setVisibility(View.INVISIBLE);
                binding.loginBtn.CL.setVisibility(View.VISIBLE);
            }

            private void showUserInfo() {
                binding.userInfoGroup.setVisibility(View.VISIBLE);
                binding.loginBtn.CL.setVisibility(View.GONE);
            }
        });

        viewModel.userMDL.observe(getViewLifecycleOwner(), user -> {
            binding.userNameTV.setText(user.getUserName());
            String userPhone = "+" + user.getUserPhone();
            binding.userPhoneTV.setText(userPhone);
        });
    }


    @Override
    public void initLoading() {
        adapter = new SettingViewAdapter(requireContext(), listener);
        binding.settingRV.setAdapter(adapter);
        binding.topBack.setGradient(DynamicTheme.gradientStartColor, DynamicTheme.gradientEndColor);
        binding.loginBtn.btnTextTV.setText(getString(R.string.login_register));
    }

    @Override
    public void initErrorObserver() {

    }
    @Override
    public void initEvent() {
        binding.loginBtn.btnBody.setOnClickListener(view -> listener.onOpenReq(LOGIN_REGISTER));

        binding.userProfileBtn.setOnClickListener(view -> listener.onOpenReq(USER_PROFILE));

        binding.userNameTV.setOnClickListener(v -> listener.onOpenReq(USER_PROFILE));

        binding.userPhoneTV.setOnClickListener(v -> listener.onOpenReq(USER_PROFILE));
    }
}