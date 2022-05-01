package com.ultimate.ecommerce.ui.fragment.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.databinding.FragmentHelpBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.help.views.helpview.HelpAdapter;
import com.ultimate.ecommerce.ui.fragment.help.views.helpview.HelpViewListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HelpFragment extends BaseFragment<HelpFragmentViewModel> {
    FragmentHelpBinding bd;
    HelpAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentHelpBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }


    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.helpMDL.observe(getViewLifecycleOwner(), helpData -> {
            bd.placeholder.pageTitleTV.setText(helpData.getTitle());
            adapter.setList(helpData.getQues());
            LayoutUtil.hideShimmer(bd.placeholder.placeholderCL, bd.shimmer.shimmerL);
        });
    }

    @Override
    public void initLoading() {
        adapter = new HelpAdapter(new HelpViewListener() {
        });
        bd.placeholder.helpRV.setAdapter(adapter);
        LayoutUtil.showShimmer(bd.placeholder.placeholderCL, bd.shimmer.shimmerL);

        viewModel.getHelpPage();
    }


    @Override
    public void initErrorObserver() {

    }
}


