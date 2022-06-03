package com.ultimate.ecommerce.ui.fragment.lang_currency;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentLangCurrencyBinding;
import com.ultimate.ecommerce.ui.activity.main.MainActivity;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.utilities.SharedPreferenceHelper;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LangCurrencyFragment extends BaseFragment<LangCurrencyFragmentViewModel> {
    FragmentLangCurrencyBinding binding;
    boolean isSaveAble = true;
    String currentLanguage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLangCurrencyBinding.inflate(getLayoutInflater());
        requireActivity().getWindow().setStatusBarColor(Color.WHITE);
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.langRG.setOnCheckedChangeListener((group, checkedId) -> {
            binding.saveBtn.btnBody.setGradient(DynamicTheme.gradientStartColor, DynamicTheme.gradientEndColor);
            binding.saveBtn.btnTextTV.setTextColor(Color.BLACK);
        });

        binding.saveBtn.btnBody.setOnClickListener(v -> {
            if (binding.arabicRB.isChecked())
                viewModel.changeLanguage(SharedPreferenceHelper.Keys.AR.getValue());
            else
                viewModel.changeLanguage(SharedPreferenceHelper.Keys.EN.getValue());
        });
    }

    @Override
    public void initObservers() {
        viewModel.languageChangeMDL.observe(getViewLifecycleOwner(), language -> {
            SharedPreferenceHelper.setSharedPreferenceString(requireContext(), language, SharedPreferenceHelper.Keys.EN.getValue());
            getActivity().finish();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.rotate_in, R.anim.rotate_out);
        });
    }

    @Override
    public void initLoading() {
        binding.saveBtn.btnBody.setGradient(Color.LTGRAY, Color.LTGRAY);
        binding.saveBtn.btnTextTV.setTextColor(Color.GRAY);
        binding.langTitle.startTitle.setText(getString(R.string.lang));
        binding.currTitle.startTitle.setText(getString(R.string.cur));
    }

    @Override
    public void initErrorObserver() {

    }
}


