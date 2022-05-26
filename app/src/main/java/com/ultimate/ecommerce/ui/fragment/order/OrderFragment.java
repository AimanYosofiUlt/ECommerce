package com.ultimate.ecommerce.ui.fragment.order;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.google.android.material.tabs.TabLayoutMediator;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentOrderBinding;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.GetUserOrdersData;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order.views.mainviewpager.OrderPagerAdapter;
import com.ultimate.ecommerce.ui.fragment.order_inner.OrderInnerFragment;

import java.util.List;

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
    }

    @Override
    public void initLoading() {
        initFragmentsAdapter();
        initViewPager();
        viewModel.validateGetOrders(requireContext());
    }

    private void initFragmentsAdapter() {
        newOrdersFragment = new OrderInnerFragment();
        approvedOrdersFragment = new OrderInnerFragment();
        chargedOrdersFragment = new OrderInnerFragment();
        completedOrdersFragment = new OrderInnerFragment();

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


