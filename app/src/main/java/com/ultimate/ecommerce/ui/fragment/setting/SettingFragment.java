package com.ultimate.ecommerce.ui.fragment.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.databinding.FragmentSettingBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.setting.views.settingview.SettingViewAdapter;

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

    }

    @Override
    public void initLoading() {
        SettingViewAdapter adapter = new SettingViewAdapter(requireContext(), this::openFragment);
        bd.settingRV.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(requireContext());
        bd.settingRV.setLayoutManager(layout);
    }

    void openFragment(int id) {

    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initEvent() {

    }
}