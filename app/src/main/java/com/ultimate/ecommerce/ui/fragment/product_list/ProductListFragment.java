package com.ultimate.ecommerce.ui.fragment.product_list;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentProductListBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_products.Categories;
import com.ultimate.ecommerce.repository.server.response.get_products.FiltersData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.product_list.bottomsheets.filter.FilterBottomSheet;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapter;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductViewListener;
import com.ultimate.ecommerce.ui.fragment.product_list.views.sub_category.SubCategoryAdapter;
import com.ultimate.ecommerce.ui.fragment.product_list.views.sub_category.SubCategoryViewListener;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListFragment extends BaseFragment<ProductListFragmentViewModel> {
    FragmentProductListBinding binding;
    Category category;

    SubCategoryAdapter subCategoryAdapter;
    ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(getLayoutInflater());
        category = ProductListFragmentArgs.fromBundle(getArguments()).getCategory();
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

        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterBottomSheet bottomSheet = new FilterBottomSheet();
                bottomSheet.show(requireActivity().getSupportFragmentManager(), "Filter");
            }
        });

        binding.searchBtn.setOnClickListener(v -> NavHostFragment.findNavController(requireParentFragment())
                .navigate(ProductListFragmentDirections.actionProductListToProductSearch()));
    }

    @Override
    public void initObservers() {
        viewModel.subCategoriesMDL.observe(getViewLifecycleOwner(), subCategoriesDataList -> {
            subCategoryAdapter.setList(subCategoriesDataList);
            hideNoInternetProgress();
        });

        viewModel.productsMDL.observe(getViewLifecycleOwner(), data -> {
            productAdapter.setList(data);
            hideNoInternetProgress();
        });

        viewModel.filtersMDL.observe(getViewLifecycleOwner(), filtersData -> {

        });

        viewModel.getProductResponseStateMDL.observe(getViewLifecycleOwner(), responseState -> {
            binding.internetCheck.progressBar.setVisibility(View.GONE);
        });

        viewModel.updateCartResStateMDL.observe(getViewLifecycleOwner(), responseState -> Log.d("ProductListFragment", "onChanged: 245353 :" + responseState.getMessage()));
    }

    public void hideNoInternetProgress() {
        binding.internetCheck.progressBar.setVisibility(View.GONE);
        binding.internetCheck.internetConnectionGroup.setVisibility(View.GONE);
    }

    @Override
    public void initLoading() {
        binding.pageTitleTV.setText(category.getTitle());

        subCategoryAdapter = new SubCategoryAdapter(new SubCategoryViewListener() {
        });
        binding.subCategoryRV.setAdapter(subCategoryAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.subCategoryRV.setLayoutManager(linearLayoutManager);


        productAdapter = new ProductAdapter(new ProductViewListener() {
            @Override
            public void onAddToCart(ProductCart productCart) {
                viewModel.addToCart(productCart);
            }

            @Override
            public void onClick(ProductAdapterData data) {
                int productId = data.getData().getId();
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(
                                ProductListFragmentDirections.actionProductListToProductDetail().setProductId(productId)
                        );
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
        binding.productRV.setAdapter(productAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        binding.productRV.setLayoutManager(gridLayoutManager);

        //todo finish paging
        viewModel.validateGetProducts(requireContext(), category, 1);
    }

    @Override
    public void initErrorObserver() {
        viewModel.validateGetProductsMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                handelValidateError(responseState.getMessage());
            }
        });
    }

    private void handelValidateError(String message) {
        switch (message) {
            case NO_INTERNET_CONNECTION:
                Toast.makeText(requireContext(), getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                binding.internetCheck.progressBar.setVisibility(View.GONE);
                binding.internetCheck.internetConnectionGroup.setVisibility(View.VISIBLE);
                break;
        }
    }
}


