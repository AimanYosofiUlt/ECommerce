package com.ultimate.ecommerce.ui.fragment.splash;

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
import com.ultimate.ecommerce.databinding.FragmentSplashBinding;
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

    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initLoading() {
        // TODO
        //  THIS TO MAKE APP START AFTER INITIALIZE ANYTHING AND AFTER 1 SECOND AS A MINIMUM
        tryGoMain();
        startSplash();
    }

    private void startSplash() {
        new CountDownTimer(5000, 100) {
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