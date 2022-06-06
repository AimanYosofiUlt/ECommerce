package com.ultimate.ecommerce.ui.fragment.rate_order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentRateOrderBinding;
import com.ultimate.ecommerce.repository.server.response.get_order.GetOrderData;
import com.ultimate.ecommerce.repository.server.response.get_order.Product;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.rate_order.views.mainviewpager.RateOrderPagerAdapter;
import com.ultimate.ecommerce.ui.fragment.rate_order_inner.RateOrderInnerFragment;
import com.ultimate.ecommerce.ui.fragment.rate_order_inner.RateOrderListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RateOrderFragment extends BaseFragment<RateOrderFragmentViewModel> {
    FragmentRateOrderBinding binding;
    GetOrderData data;
    RateOrderPagerAdapter adapter;
    int currentItem = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRateOrderBinding.inflate(getLayoutInflater());
        data = RateOrderFragmentArgs.fromBundle(getArguments()).getOrderDate();
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        binding.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment())
                .popBackStack());

        binding.nextBtn.btnBody.setOnClickListener(view -> {
            binding.productVP.setCurrentItem(++currentItem);
            binding.nextBtn.CL.setVisibility(View.INVISIBLE);
        });

        binding.saveBtn.btnBody.setOnClickListener(view -> {
            showProgress(requireContext(), getString(R.string.rate_products), getString(R.string.loading));
            viewModel.validateSaveRates(requireContext(), data.getOrderid(), adapter.getRateOrders());
        });
    }

    @Override
    public void initObservers() {
        viewModel.saveRatesResponseMDL.observe(getViewLifecycleOwner(), responseState -> {
            hideProgress();
            if (responseState.isSuccessful()) {
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack();
            } else
                LayoutUtil.showErrorDialog(requireContext(), responseState.getMessage());

        });
    }

    @Override
    public void initLoading() {
        binding.nextBtn.btnTextTV.setText(getString(R.string.next_rate));
        binding.saveBtn.btnTextTV.setText(getString(R.string.save_rate));
        binding.nextBtn.CL.setVisibility(View.INVISIBLE);
        binding.saveBtn.CL.setVisibility(View.GONE);
        adapter = new RateOrderPagerAdapter(requireParentFragment());
        initViewPager();
    }

    private void initViewPager() {
        for (Product product : data.getProducts()) {
            RateOrderInnerFragment innerFragment = new RateOrderInnerFragment(product, new RateOrderListener() {
                @Override
                public void onRateChang() {
                    boolean itsInLastProduct = currentItem == adapter.getItemCount() - 1;
                    if (itsInLastProduct)
                        binding.saveBtn.CL.setVisibility(View.VISIBLE);
                    else
                        binding.nextBtn.CL.setVisibility(View.VISIBLE);
                }
            });

            adapter.addFragment(innerFragment);
        }

        binding.productVP.setAdapter(adapter);
        binding.productVP.setUserInputEnabled(false);
        binding.dotsIndicator.attachTo(binding.productVP);
    }

    @Override
    public void initErrorObserver() {

    }
}


