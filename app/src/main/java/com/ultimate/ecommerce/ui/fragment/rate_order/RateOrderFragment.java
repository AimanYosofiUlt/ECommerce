package com.ultimate.ecommerce.ui.fragment.rate_order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        binding.nextBtn.btnBody.setOnClickListener(view -> binding.productVP.setCurrentItem(currentItem));
        binding.saveBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.validateSaveRates(requireContext(), data.getOrderid(), adapter.getRateOrders());
            }
        });
    }

    @Override
    public void initObservers() {

    }

    @Override
    public void initLoading() {
        binding.nextBtn.btnTextTV.setText(getString(R.string.next_rate));
        binding.saveBtn.btnTextTV.setText(getString(R.string.save_rate));
        binding.nextBtn.btnBody.setVisibility(View.INVISIBLE);
        binding.saveBtn.btnBody.setVisibility(View.GONE);

        adapter = new RateOrderPagerAdapter(requireParentFragment());
        initViewPager();
    }

    private void initViewPager() {
        Toast.makeText(requireContext(), "size:" + data.getProducts().size(), Toast.LENGTH_SHORT).show();
        for (Product product : data.getProducts()) {
            RateOrderInnerFragment innerFragment = new RateOrderInnerFragment(product, new RateOrderListener() {
                @Override
                public void onRateChang() {
                    binding.nextBtn.btnBody.setVisibility(View.VISIBLE);
                }
            });

            adapter.addFragment(innerFragment);
        }
    }

    @Override
    public void initErrorObserver() {

    }
}


