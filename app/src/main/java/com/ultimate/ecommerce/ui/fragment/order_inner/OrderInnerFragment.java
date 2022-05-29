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
    boolean isViewCreated = false;
    String emptyMessage;

    public OrderInnerFragment(String emptyMessage,OrderViewListener listener) {
        adapter = new OrderAdapter(listener);
        this.emptyMessage = emptyMessage;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrdersInnerBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {

    }

    public void setEmptyMsgVisibility() {
        if (adapter.getItemCount() == 0)
            binding.noDataGroup.setVisibility(View.VISIBLE);
        else
            binding.noDataGroup.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        setEmptyMsgVisibility();
    }

    @Override
    public void initLoading() {
        LinearLayoutManager layout = new LinearLayoutManager(requireContext());
        binding.orderRV.setLayoutManager(layout);
        binding.orderRV.setAdapter(adapter);
        setEmptyMsgVisibility();

        binding.emptyMsgTV.setText(emptyMessage);
    }

    @Override
    public void initErrorObserver() {
    }

    public void setOrderList(List<Order> orderList) {
        adapter.setList(orderList);
        if (isViewCreated)
            setEmptyMsgVisibility();
    }
}


