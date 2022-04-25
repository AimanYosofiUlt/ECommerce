package com.ultimate.ecommerce.ui.fragment.splash;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentSplashBinding;
import com.ultimate.ecommerce.repository.local.tables.configuration.Configuration;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashFragment extends BaseFragment<SplashFragmentViewModel> {
    FragmentSplashBinding bd;
    boolean isStartAllow = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentSplashBinding.inflate(getLayoutInflater());
        return bd.getRoot();
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
//        viewModel.configLiveData.observe(getViewLifecycleOwner(), new Observer<Configuration>() {
//            @Override
//            public void onChanged(Configuration configuration) {
//                setAppDynamicTheme(configuration);
//            }
//
//            private void setAppDynamicTheme(Configuration configuration) {
//                DynamicTheme.mainColor = Color.parseColor(configuration.getMainColor());
//                DynamicTheme.secondColor = Color.parseColor(configuration.getSecondColor());
//                DynamicTheme.gradientStartColor = Color.parseColor(configuration.getGradientStartColor());
//                DynamicTheme.gradientEndColor = Color.parseColor(configuration.getGradientEndColor());
//                DynamicTheme.imageBackground = Color.parseColor(configuration.getImageBackground());
//                DynamicTheme.reviewColor = Color.parseColor(configuration.getReviewColor());
//            }
//        });

        viewModel.responseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_LONG).show();
                tryGoMain();
            }
        });
    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initLoading() {
        // TODO
        //  THIS TO MAKE APP START AFTER INITIALIZE ANYTHING AND AFTER 1 SECOND AS A MINIMUM
        bd.back.setGradientDef();
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