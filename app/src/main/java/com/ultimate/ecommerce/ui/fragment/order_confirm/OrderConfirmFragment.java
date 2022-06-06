package com.ultimate.ecommerce.ui.fragment.order_confirm;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentOrderConfirmBinding;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm.views.mainviewpager.OrderConfirmPagerAdapter;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.OrderConfirmAddressFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm_check.OrderConfirmCheckFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm_done.OrderConfirmDoneFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method.OrderConfirmPaymentMethodFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.OrderConfirmShipmentMethodFragment;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderConfirmFragment extends BaseFragment<OrderConfirmFragmentViewModel> {
    FragmentOrderConfirmBinding binding;
    OrderConfirmPagerAdapter pageAdapter;
    UpdateCartData cartData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderConfirmBinding.inflate(getLayoutInflater());
        cartData = OrderConfirmFragmentArgs.fromBundle(getArguments()).getCartData();
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack();
            }
        });
    }

    @Override
    public void initObservers() {
        viewModel.payCartResponseMDL.observe(getViewLifecycleOwner(), responseState -> {
            hideProgress();
            if (responseState.isSuccessful()) {
                binding.stateVP.setCurrentItem(4);
                binding.title.CL.setVisibility(View.GONE);
            } else
                LayoutUtil.showErrorDialog(requireContext(), responseState.getMessage());
        });
    }

    @Override
    public void initLoading() {
        binding.title.startTitle.setText(R.string.address);
        initFragmentsAdapter();
        initViewPager();
    }

    private void initFragmentsAdapter() {
        OrderConfirmAddressFragment addressFragment = new OrderConfirmAddressFragment(
                () -> {
                    binding.stateVP.setCurrentItem(1);
                    binding.steps.step2Back.fill();
                    binding.steps.step2TV.setTextColor(Color.WHITE);
                    binding.title.startTitle.setText(getString(R.string.ship_title));
                });

        OrderConfirmShipmentMethodFragment shipmentMethodFragment = new OrderConfirmShipmentMethodFragment(
                () -> {
                    binding.stateVP.setCurrentItem(2);
                    binding.steps.step3Back.fill();
                    binding.steps.step3TV.setTextColor(Color.WHITE);
                    binding.title.startTitle.setText(getString(R.string.products_title));
                });

        OrderConfirmCheckFragment checkFragment = new OrderConfirmCheckFragment(cartData,
                () -> {
                    binding.stateVP.setCurrentItem(3);
                    binding.steps.step4Back.fill();
                    binding.steps.step4TV.setTextColor(Color.WHITE);
                    binding.title.startTitle.setText(getString(R.string.deliver_title));
                });

        OrderConfirmPaymentMethodFragment paymentMethodFragment = new OrderConfirmPaymentMethodFragment(
                () -> {
                    showProgress(requireContext(), getString(R.string.pay), getString(R.string.loading));
                    viewModel.validatePayCart(requireContext(), cartData);
                });

        OrderConfirmDoneFragment doneFragment = new OrderConfirmDoneFragment(
                () -> {
                    NavHostFragment.findNavController(requireParentFragment())
                            .popBackStack();
                });

        pageAdapter = new OrderConfirmPagerAdapter(requireParentFragment());
        pageAdapter.addFragment(addressFragment);
        pageAdapter.addFragment(shipmentMethodFragment);
        pageAdapter.addFragment(checkFragment);
        pageAdapter.addFragment(paymentMethodFragment);
        pageAdapter.addFragment(doneFragment);
    }

    private void initViewPager() {
        binding.stateVP.setAdapter(pageAdapter);
        binding.stateVP.setUserInputEnabled(false);
    }

    @Override
    public void initErrorObserver() {

    }
}


