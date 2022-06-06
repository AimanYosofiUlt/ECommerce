package com.ultimate.ecommerce.ui.fragment.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.ultimate.ecommerce.databinding.FragmentAddressBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddressFragment extends BaseFragment<AddressFragmentViewModel> {
    FragmentAddressBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddressBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.backBtn.setOnClickListener(view ->
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack());
    }

    @Override
    public void initObservers() {
        viewModel.getAddressFieldsResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (!responseState.isSuccessful())
                    LayoutUtil.showErrorDialog(requireContext(), responseState.getMessage());
            }
        });
        viewModel.addressMDL.observe(getViewLifecycleOwner(), addressFieldData -> {

        });
    }

    @Override
    public void initLoading() {
        viewModel.validateGetAddress(requireContext());
    }

    @Override
    public void initErrorObserver() {

    }
}


