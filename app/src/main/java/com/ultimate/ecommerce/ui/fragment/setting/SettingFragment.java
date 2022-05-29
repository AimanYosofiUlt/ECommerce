package com.ultimate.ecommerce.ui.fragment.setting;

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
    public static final int ORDERS = 0;
    public static final int ADDRESS = 1;
    public static final int FAVORITE = 2;
    public static final int LANG_CUR = 3;
    public static final int HELP = 4;
    public static final int ABOUT = 5;
    public static final int CONTACT_US = 6;
    public static final int LOGOUT = 7;
    public static final int LOGIN_REGISTER = 8;
    public static final int USER_PROFILE = 9;

    FragmentSettingBinding binding;
    SettingViewAdapter adapter;
    boolean isNotScrolled = false;

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
                    binding.userInfoGroup.setVisibility(View.VISIBLE);
                    binding.loginBtn.CL.setVisibility(View.GONE);
                    viewModel.getCurrentUser();
                } else {
                    binding.userInfoGroup.setVisibility(View.INVISIBLE);
                    binding.loginBtn.CL.setVisibility(View.VISIBLE);
                }
                adapter.addSettingItems(requireContext(), isUserLogin);
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
        binding.loginBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOpenReq(LOGIN_REGISTER);
            }
        });

        binding.userProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOpenReq(USER_PROFILE);
            }
        });
    }
}