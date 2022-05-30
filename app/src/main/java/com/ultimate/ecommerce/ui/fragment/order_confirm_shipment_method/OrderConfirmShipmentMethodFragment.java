package com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.databinding.FragmentOrderConfirmShipmentMethodBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderConfirmShipmentMethodFragment extends BaseFragment<OrderConfirmShipmentMethodFragmentViewModel> {
    FragmentOrderConfirmShipmentMethodBinding bd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentOrderConfirmShipmentMethodBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {

    }

    @Override
    public void initLoading() {
        viewModel.validateGetUserAddress(requireContext());
    }

    @Override
    public void initErrorObserver() {

    }
}


