package com.ultimate.ecommerce.ui.fragment.splash;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentSplashBinding;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.utilities.CustomDialogListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashFragment extends BaseFragment<SplashFragmentViewModel> {
    FragmentSplashBinding binding;
    boolean isStartAllow = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void tryGoMain() {
        if (isStartAllow) {
            NavHostFragment.findNavController(SplashFragment.this)
                    .navigate(R.id.actionSplashToMain);
        }
        isStartAllow = true;
    }

    @Override
    public void initObservers() {
        viewModel.configurationLiveData.observe(getViewLifecycleOwner(), new Observer<Configuration>() {
            @Override
            public void onChanged(Configuration configuration) {
                setAppDynamicTheme(configuration);
            }

            private void setAppDynamicTheme(Configuration configuration) {
                DynamicTheme.mainColor = Color.parseColor(configuration.getMainColor());
                DynamicTheme.secondColor = Color.parseColor(configuration.getSecondColor());
                DynamicTheme.gradientStartColor = Color.parseColor(configuration.getGradientStartColor());
                DynamicTheme.gradientEndColor = Color.parseColor(configuration.getGradientEndColor());
                DynamicTheme.imageBackground = Color.parseColor(configuration.getImageBackground());
                DynamicTheme.reviewColor = Color.parseColor(configuration.getReviewColor());

                binding.back.setGradientDef();
            }
        });

        viewModel.responseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (responseState.isSuccessful())
                    tryGoMain();
                else
                    showErrorDialog();
            }
        });
    }

    private void showErrorDialog() {
        LayoutUtil.showOptionDialog(requireContext()
                , getString(R.string.start_error)
                , getString(R.string.start_error_msg)
                , new CustomDialogListener() {
                    @Override
                    public void onClick() {
                        isStartAllow = false;
                        viewModel.getConfiguration();
                    }

                    @Override
                    public void onCancel() {
                        NavHostFragment.findNavController(requireParentFragment())
                                .popBackStack();
                        requireActivity().finishAffinity();
                    }
                });
    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initLoading() {
        viewModel.getConfiguration();
        startSplash();
    }

    private void startSplash() {
        new CountDownTimer(100, 100) {
            @Override
            public void onFinish() {
                tryGoMain();
            }

            @Override
            public void onTick(long l) {
            }
        }.start();
    }

    @Override
    public void initEvent() {

    }
}