package com.ultimate.ecommerce.ui.fragment.rate_order_inner;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentRateOrderInnerBinding;
import com.ultimate.ecommerce.repository.server.response.get_order.Product;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.review_list.views.rate_star.RateStars;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RateOrderInnerFragment extends BaseFragment<RateOrderInnerFragmentViewModel> {
    FragmentRateOrderInnerBinding binding;
    Product product;
    RateOrder data;
    RateOrderListener listener;

    public RateOrderInnerFragment(Product product, RateOrderListener listener) {
        this.product = product;
        this.listener = listener;
    }

    public RateOrder getData() {
        return data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRateOrderInnerBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.commentED.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                data.setComment(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initObservers() {

    }

    @Override
    public void initLoading() {
        Glide.with(requireContext())
                .load(product.getImage())
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.firstImage);

        binding.nameTV.setText(product.getTitle());
        new RateStars(binding.rateStars, rate -> {
            data.setRate(rate);
            listener.onRateChang();
        });
    }

    @Override
    public void initErrorObserver() {

    }
}


