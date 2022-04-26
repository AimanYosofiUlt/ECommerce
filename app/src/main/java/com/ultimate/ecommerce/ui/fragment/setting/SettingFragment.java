package com.ultimate.ecommerce.ui.fragment.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentSettingBinding;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.setting.views.settingview.SettingViewAdapter;
import com.ultimate.ecommerce.ui.fragment.setting.views.settingview.SettingViewListener;
import com.ultimate.ecommerce.ui.fragment.train.TrainActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingFragment extends BaseFragment<SettingFragmentViewModel> {
    FragmentSettingBinding bd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentSettingBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }

    @Override
    public void initObservers() {
        viewModel.configLiveData.observe(getViewLifecycleOwner(), new Observer<Configuration>() {
            @Override
            public void onChanged(Configuration configuration) {

            }
        });
    }


    @Override
    public void initLoading() {
        SettingViewAdapter adapter = new SettingViewAdapter(requireContext(), new SettingViewListener() {
            @Override
            public void onOpenReq(int id) {

            }
        });
        bd.settingRV.setAdapter(adapter);

        bd.topBack.setGradient(DynamicTheme.gradientStartColor, DynamicTheme.gradientEndColor);
    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initEvent() {
    }
}