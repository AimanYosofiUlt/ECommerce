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
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
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
    FragmentProductListBinding bd;
    Category category;

    SubCategoryAdapter subCategoryAdapter;
    ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentProductListBinding.inflate(getLayoutInflater());
        category = ProductListFragmentArgs.fromBundle(getArguments()).getCategory();
        return bd.getRoot();
    }


    @Override
    public void initEvent() {
        bd.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack();
            }
        });

        bd.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterBottomSheet bottomSheet = new FilterBottomSheet();
                bottomSheet.show(requireActivity().getSupportFragmentManager(), "Filter");
            }
        });
    }

    @Override
    public void initObservers() {
        viewModel.subCategoriesMDL.observe(getViewLifecycleOwner(), new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> subCategoriesDataList) {
                Log.d("ProductListFragment", "onChanged: 43524 listSize:" + subCategoriesDataList.size());
                subCategoryAdapter.setList(subCategoriesDataList);
                hideNoInternetProgress();
            }
        });

        viewModel.productsMDL.observe(getViewLifecycleOwner(), new Observer<List<ProductAdapterData>>() {
            @Override
            public void onChanged(List<ProductAdapterData> data) {
                productAdapter.setList(data);
                hideNoInternetProgress();
            }
        });

        viewModel.filtersMDL.observe(getViewLifecycleOwner(), new Observer<List<FiltersData>>() {
            @Override
            public void onChanged(List<FiltersData> filtersData) {

            }
        });

        viewModel.getProductResponseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                bd.internetCheck.progressBar.setVisibility(View.GONE);
                Log.d("ProductListFragment", "onChanged: 39287 :" + responseState.getMessage());
            }
        });

        viewModel.updateCartResStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                Log.d("ProductListFragment", "onChanged: 245353 :" + responseState.getMessage());
            }
        });
    }

    public void hideNoInternetProgress() {
        bd.internetCheck.progressBar.setVisibility(View.GONE);
        bd.internetCheck.internetConnectionGroup.setVisibility(View.GONE);
    }

    @Override
    public void initLoading() {
        bd.pageTitleTV.setText(category.getTitle());

        subCategoryAdapter = new SubCategoryAdapter(new SubCategoryViewListener() {
        });
        bd.subCategoryRV.setAdapter(subCategoryAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bd.subCategoryRV.setLayoutManager(linearLayoutManager);


        productAdapter = new ProductAdapter(new ProductViewListener() {
            @Override
            public void onAddToCart(ProductCart productCart) {
                viewModel.addToCart(productCart);
            }

            @Override
            public void onClick(ProductAdapterData data) {
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(
                                ProductListFragmentDirections.actionProductListToProductDetail(data)
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
        bd.productRV.setAdapter(productAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        bd.productRV.setLayoutManager(gridLayoutManager);

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
                bd.internetCheck.progressBar.setVisibility(View.GONE);
                bd.internetCheck.internetConnectionGroup.setVisibility(View.VISIBLE);
                break;
        }
    }
}


