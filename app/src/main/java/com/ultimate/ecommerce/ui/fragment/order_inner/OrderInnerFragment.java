package com.ultimate.ecommerce.ui.fragment.order_inner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.databinding.FragmentOrdersInnerBinding;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order_inner.views.order.OrderAdapter;
import com.ultimate.ecommerce.ui.fragment.order_inner.views.order.OrderViewListener;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderInnerFragment extends BaseFragment<OrderInnerFragmentViewModel> {
    FragmentOrdersInnerBinding binding;
    OrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrdersInnerBinding.inflate(getLayoutInflater());
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
        adapter = new OrderAdapter(new OrderViewListener() {
        });
        LinearLayoutManager layout = new LinearLayoutManager(requireContext());
        binding.orderRV.setLayoutManager(layout);
        binding.orderRV.setAdapter(adapter);
    }

    @Override
    public void initErrorObserver() {

    }

    public void setOrderList(List<Order> orderList) {
        adapter.setList(orderList);
    }
}


