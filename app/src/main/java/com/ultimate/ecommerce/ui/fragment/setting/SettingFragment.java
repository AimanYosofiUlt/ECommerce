package com.ultimate.ecommerce.ui.fragment.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    FragmentSettingBinding bd;
    SettingViewAdapter adapter;
    boolean isNotScrolled = false;

    SettingViewListener listener;

    public SettingFragment(SettingViewListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentSettingBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }

    @Override
    public void initObservers() {
//        viewModel.userIsLoginMDL.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean isUserLogin) {
//                if (isUserLogin) {
//                    bd.userInfoGroup.setVisibility(View.VISIBLE);
//                    bd.loginBtn.btnBody.setVisibility(View.GONE);
//                } else {
//                    bd.userInfoGroup.setVisibility(View.INVISIBLE);
//                    bd.loginBtn.btnBody.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }


    @Override
    public void initLoading() {
        adapter = new SettingViewAdapter(requireContext(), listener);
        bd.settingRV.setAdapter(adapter);
        bd.topBack.setGradient(DynamicTheme.gradientStartColor, DynamicTheme.gradientEndColor);
        bd.loginBtn.btnTextTV.setText(getString(R.string.login_register));
    }

    @Override
    public void initErrorObserver() {

    }
    @Override
    public void initEvent() {
        bd.loginBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOpenReq(LOGIN_REGISTER);
            }
        });

        bd.userProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOpenReq(USER_PROFILE);
            }
        });
    }
}