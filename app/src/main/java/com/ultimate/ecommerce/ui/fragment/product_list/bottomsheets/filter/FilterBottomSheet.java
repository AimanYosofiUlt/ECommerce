package com.ultimate.ecommerce.ui.fragment.product_list.bottomsheets.filter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.BottomSheeteFilterBinding;
import com.ultimate.ecommerce.ui.base.BaseBottomSheet;
import com.ultimate.ecommerce.ui.fragment.product_list.views.filter.FilterAdapter;
import com.ultimate.ecommerce.ui.fragment.product_list.views.filter.FilterViewListener;

import javax.annotation.Nullable;

public class FilterBottomSheet extends BaseBottomSheet {
    BottomSheeteFilterBinding binding;
    FilterAdapter ordersAdapter;
    FilterAdapter colorsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheeteFilterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initLoading() {
        binding.orderTitle.startTitle.setText(getString(R.string.order));
        binding.colorTitle.startTitle.setText(getString(R.string.color));
        binding.priceTitle.startTitle.setText(getString(R.string.price));

        ordersAdapter = new FilterAdapter(new FilterViewListener() {
            @Override
            public void onItemCheck(String data) {

            }
        });
        binding.orderByRV.setAdapter(ordersAdapter);

        colorsAdapter = new FilterAdapter(new FilterViewListener() {
            @Override
            public void onItemCheck(String data) {

            }
        });
        binding.byColorRV.setAdapter(ordersAdapter);
    }

    @Override
    protected void initErrorObserver() {

    }

    @Override
    protected void initObservers() {

    }
}
