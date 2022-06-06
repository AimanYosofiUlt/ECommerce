package com.ultimate.ecommerce.ui.fragment.product_list.bottomsheets.filter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mohammedalaa.seekbar.DoubleValueSeekBarView;
import com.mohammedalaa.seekbar.OnDoubleValueSeekBarChangeListener;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.BottomSheeteFilterBinding;
import com.ultimate.ecommerce.ui.base.BaseBottomSheet;
import com.ultimate.ecommerce.ui.fragment.product_list.views.filter.FilterAdapter;

import javax.annotation.Nullable;

public class FilterBottomSheet extends BaseBottomSheet {
    BottomSheeteFilterBinding binding;
    FilterAdapter ordersAdapter;
    FilterAdapter colorsAdapter;
    FilterBottomSheetListener listener;
    Filter filter;

    public FilterBottomSheet(FilterBottomSheetListener listener) {
        this.listener = listener;
        filter = new Filter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheeteFilterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initEvent() {
        binding.doneBtn.btnBody.setOnClickListener(v -> {
            listener.onFilterReq(filter);
            dismiss();
        });

        binding.rangeSeekbar.setOnRangeSeekBarViewChangeListener(new OnDoubleValueSeekBarChangeListener() {
            @Override
            public void onValueChanged(@androidx.annotation.Nullable DoubleValueSeekBarView doubleValueSeekBarView
                    , int min, int max, boolean fromUser) {
                setMinMax(min, max);
            }

            @Override
            public void onStopTrackingTouch(@androidx.annotation.Nullable DoubleValueSeekBarView doubleValueSeekBarView, int min, int max) {

            }

            @Override
            public void onStartTrackingTouch(@androidx.annotation.Nullable DoubleValueSeekBarView doubleValueSeekBarView, int min, int max) {

            }
        });

        binding.restBtn.setOnClickListener(v -> setMinMax(Filter.MIN, Filter.MAX));
    }

    private void setMinMax(int min, int max) {
        binding.minED.setText(String.valueOf(min));
        if (max != Filter.MAX)
            binding.maxED.setText(String.valueOf(max));
        else
            binding.maxED.setText(getString(R.string.max));
        filter.setMaximum(max);
        filter.setMinimum(min);
    }

    @Override
    protected void initLoading() {
        binding.orderTitle.startTitle.setText(getString(R.string.order));
        binding.colorTitle.startTitle.setText(getString(R.string.color));
        binding.priceTitle.startTitle.setText(getString(R.string.price));

        ordersAdapter = new FilterAdapter(data -> {

        });
        binding.orderByRV.setAdapter(ordersAdapter);

        colorsAdapter = new FilterAdapter(data -> {

        });
        binding.byColorRV.setAdapter(ordersAdapter);

        binding.rangeSeekbar.setFillColor(DynamicTheme.gradientStartColor);
        binding.rangeSeekbar.setCircleFillColor(DynamicTheme.gradientStartColor);
    }
}
