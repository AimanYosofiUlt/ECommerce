package com.ultimate.ecommerce.ui.fragment.order_confirm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.databinding.FragmentOrderConfirmBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm.views.mainviewpager.OrderConfirmPagerAdapter;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.OrderConfirmAddressFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm_check.OrderConfirmCheckFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm_done.OrderConfirmDoneFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method.OrderConfirmPaymentMethodFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.OrderConfirmShipmentMethodFragment;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderConfirmFragment extends BaseFragment<OrderConfirmFragmentViewModel> {
    FragmentOrderConfirmBinding binding;
    OrderConfirmPagerAdapter pageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderConfirmBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {

    }

    @Override
    public void initLoading() {
        initFragmentsAdapter();
        initViewPager();
    }

    private void initFragmentsAdapter() {
        pageAdapter = new OrderConfirmPagerAdapter(requireParentFragment());
        pageAdapter.addFragment(new OrderConfirmAddressFragment());
        pageAdapter.addFragment(new OrderConfirmShipmentMethodFragment());
        pageAdapter.addFragment(new OrderConfirmCheckFragment());
        pageAdapter.addFragment(new OrderConfirmPaymentMethodFragment());
        pageAdapter.addFragment(new OrderConfirmDoneFragment());
    }

    private void initViewPager() {
        binding.stateVP.setAdapter(pageAdapter);
    }

    @Override
    public void initErrorObserver() {

    }
}


