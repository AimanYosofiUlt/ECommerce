package com.ultimate.ecommerce.ui.fragment.about_us;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentAboutUsBinding;
import com.ultimate.ecommerce.repository.server.response.about_us.AboutUsData;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AboutUsFragment extends BaseFragment<AboutUsFragmentViewModel> {
    FragmentAboutUsBinding binding;

    private static final String TAG = "AboutUsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAboutUsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.placeholder.backBtn.setOnClickListener(view ->
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack());
    }

    @Override
    public void initObservers() {
        viewModel.aboutUsPageMDL.observe(getViewLifecycleOwner(), new Observer<AboutUsData>() {
            @Override
            public void onChanged(AboutUsData aboutUsData) {
                binding.placeholder.pageTitleTV.setText(aboutUsData.getTitle());
                binding.placeholder.content.setText(aboutUsData.getContent());
                Glide.with(requireContext())
                        .load(aboutUsData.getLogo())
                        .error(R.drawable.ic_baseline_error_24)
                        .into(binding.placeholder.logoImg);

                LayoutUtil.hideShimmer(binding.placeholder.placeholderCL, binding.shimmer.shimmerL);
                // todo handle social media buttons here
            }
        });

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (!responseState.isSuccessful())
                    LayoutUtil.showErrorDialog(requireContext(), responseState.getMessage());
            }
        });
    }

    @Override
    public void initLoading() {
        LayoutUtil.showShimmer(binding.placeholder.placeholderCL, binding.shimmer.shimmerL);
        viewModel.getAboutUsPage();
    }

    @Override
    public void initErrorObserver() {

    }


}


