package com.ultimate.ecommerce.ui.fragment.order;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.tabs.TabLayoutMediator;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentOrderBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.GetUserOrdersData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order.views.mainviewpager.OrderPagerAdapter;
import com.ultimate.ecommerce.ui.fragment.order_inner.OrderInnerFragment;
import com.ultimate.ecommerce.ui.fragment.order_inner.views.order.OrderViewListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderFragment extends BaseFragment<OrderFragmentViewModel> {
    FragmentOrderBinding binding;
    OrderPagerAdapter pageAdapter;
    OrderInnerFragment newOrdersFragment;
    OrderInnerFragment approvedOrdersFragment;
    OrderInnerFragment chargedOrdersFragment;
    OrderInnerFragment completedOrdersFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack();
            }
        });

        binding.reloadOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateReloadBtn();
                viewModel.validateGetOrders(requireContext());
            }
        });
    }

    @Override
    public void initObservers() {
        viewModel.ordersMDL.observe(getViewLifecycleOwner(), new Observer<GetUserOrdersData>() {
            @Override
            public void onChanged(GetUserOrdersData data) {
                newOrdersFragment.setOrderList(data.getOrdersNew());
                approvedOrdersFragment.setOrderList(data.getOrdersApproved());
                chargedOrdersFragment.setOrderList(data.getOrdersCharged());
                completedOrdersFragment.setOrderList(data.getOrdersCompleted());
            }
        });

        viewModel.getOrdersResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                binding.reloadOrdersBtn.clearAnimation();
                if (!responseState.isSuccessful())
                    LayoutUtil.showErrorDialog(requireContext(), responseState.getMessage());
            }
        });
    }

    @Override
    public void initLoading() {
        initFragmentsAdapter();
            initViewPager();

        animateReloadBtn();
        viewModel.validateGetOrders(requireContext());
    }

    private void animateReloadBtn() {
        Animation animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_clockwise);
        binding.reloadOrdersBtn.startAnimation(animation);
    }

    private void initFragmentsAdapter() {
        OrderViewListener orderViewListener = data -> NavHostFragment
                .findNavController(requireParentFragment())
                .navigate(OrderFragmentDirections.actionOrderToOrderDetail(data));

        newOrdersFragment = new OrderInnerFragment(getString(R.string.no_new_order_msg), orderViewListener);
        approvedOrdersFragment = new OrderInnerFragment(getString(R.string.no_approved_order_msg), orderViewListener);
        chargedOrdersFragment = new OrderInnerFragment(getString(R.string.no_charged_order_msg), orderViewListener);
        completedOrdersFragment = new OrderInnerFragment(getString(R.string.no_complated_order_msg), orderViewListener);

        pageAdapter = new OrderPagerAdapter(requireParentFragment());
        pageAdapter.addFragment(newOrdersFragment);
        pageAdapter.addFragment(approvedOrdersFragment);
        pageAdapter.addFragment(chargedOrdersFragment);
        pageAdapter.addFragment(completedOrdersFragment);
    }

    private void initViewPager() {
        binding.orderTabs.setTabTextColors(Color.LTGRAY, Color.BLACK);
        binding.orderTabs.setTabRippleColor(ColorStateList.valueOf(DynamicTheme.gradientStartColor));
        binding.orderTabs.setSelectedTabIndicatorColor(DynamicTheme.gradientStartColor);
        binding.orderStepsVP.setAdapter(pageAdapter);
        new TabLayoutMediator(binding.orderTabs, binding.orderStepsVP, (tab, position) -> {
            tab.setText(requireContext().getResources().getStringArray(R.array.order_title)[position]);
        }).attach();
    }

    @Override
    public void initErrorObserver() {

    }
}


