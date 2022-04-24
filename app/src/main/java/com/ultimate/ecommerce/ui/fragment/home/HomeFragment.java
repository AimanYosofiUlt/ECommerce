package com.ultimate.ecommerce.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentHomeBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageData;
import com.ultimate.ecommerce.ui.base.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<HomeFragmentViewModel> {
    FragmentHomeBinding bd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentHomeBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.responseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (!responseState.isSuccessful()) {
                    Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.homePageDataMDL.observe(getViewLifecycleOwner(), new Observer<HomePageData>() {
            @Override
            public void onChanged(HomePageData homePageData) {

            }
        });
    }

    @Override
    public void initLoading() {
        bd.topBack.setGradientDef();
        bd.searchImg.setColorFilter(DynamicTheme.gradientStartColor);
        viewModel.getHomePageData();
    }

    @Override
    public void initErrorObserver() {

    }
}