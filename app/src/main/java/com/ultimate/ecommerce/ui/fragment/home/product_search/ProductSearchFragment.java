package com.ultimate.ecommerce.ui.fragment.home.product_search;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;
import static com.ultimate.ecommerce.utilities.ValidateSt.SEARCH_EMPTY_ERROR;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentProductSearchBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapter;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductViewListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductSearchFragment extends BaseFragment<ProductSearchFragmentViewModel> {
    FragmentProductSearchBinding binding;
    ProductAdapter adapter;
    String temporarySearchText = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductSearchBinding.inflate(getLayoutInflater());
        temporarySearchText = ProductSearchFragmentArgs.fromBundle(getArguments()).getSearchText();
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.searchImg.setOnClickListener(v -> {
            String searchText = binding.searchEd.getText().toString();
            search(searchText);
        });
    }

    private void search(String searchText) {
        showProgress(requireContext(), getString(R.string.search), getString(R.string.loading));
        viewModel.validateSearch(requireContext(), searchText);
    }

    @Override
    public void initObservers() {
        viewModel.validateSearchMDL.observe(getViewLifecycleOwner(), responseState -> {
            hideProgress();
            handleValidationError(responseState.getMessage());
        });

        viewModel.searchResponseMDL.observe(getViewLifecycleOwner(), responseState -> {
            hideProgress();
            if (responseState.isSuccessful())
                return;

            LayoutUtil.showMassageDialog(requireContext()
                    , getString(R.string.server_error)
                    , responseState.getMessage());
        });

        viewModel.productsMDL.observe(getViewLifecycleOwner(), data -> adapter.setList(data));
    }

    private void handleValidationError(String message) {
        switch (message) {
            case NO_INTERNET_CONNECTION:
                Toast.makeText(requireContext(), getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                break;

            case SEARCH_EMPTY_ERROR:
                binding.searchEd.setError(getString(R.string.search_empty_error));
                break;

            default:
                Log.d("TAG", "handleValidationError: 9080: You dont handel this validation:");
        }
    }

    @Override
    public void initLoading() {
        initProductRecyclerView();
        binding.searchEd.setText(temporarySearchText.trim());

        if (!temporarySearchText.trim().isEmpty())
            search(temporarySearchText.trim());
    }

    private void initProductRecyclerView() {
        adapter = new ProductAdapter(new ProductViewListener() {
            @Override
            public void onAddToCart(ProductCart productCart) {
                viewModel.addToCart(productCart);
            }

            @Override
            public void onClick(ProductAdapterData data) {
                int productId = data.getData().getId();
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(ProductSearchFragmentDirections.actionProductSearchToProductDetail().setProductId(productId));
            }

            @Override
            public void addToFavorite(ProductAdapterData data) {
                viewModel.addToFavorite(data);
            }

            @Override
            public void removeFromFavorite(ProductAdapterData data) {
                viewModel.removeFromFavorite(data);
            }
        });

        GridLayoutManager layout = new GridLayoutManager(requireContext(), 2);
        binding.productRV.setLayoutManager(layout);
        binding.productRV.setAdapter(adapter);
    }

    @Override
    public void initErrorObserver() {

    }
}