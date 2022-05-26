package com.ultimate.ecommerce.ui.fragment.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.ultimate.ecommerce.databinding.FragmentProfileBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseFragment;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends BaseFragment<ProfileFragmentViewModel> {
    FragmentProfileBinding bd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = com.ultimate.ecommerce.databinding.FragmentProfileBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }


    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.userMDL.observe(getViewLifecycleOwner(), userData -> {
            bd.userNameED.setText( userData.getDisplayName());
            bd.phoneED.setText(userData.getUserEmail());
            bd.emailED.setText(userData.getUserLogin());
        });

        viewModel.responseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                Log.d("ProfileFragment", "onChanged: 35445: " + responseState.getMessage());
            }
        });
    }

    @Override
    public void initLoading() {
        viewModel.getUserProfile();
    }

    @Override
    public void initErrorObserver() {

    }
}


