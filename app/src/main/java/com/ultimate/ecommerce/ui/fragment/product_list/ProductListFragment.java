package com.ultimate.ecommerce.ui.fragment.product_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.databinding.FragmentProductListBinding;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_products.FiltersData;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.repository.server.response.get_products.SubCategoryData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapter;
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

    }

    @Override
    public void initObservers() {
        viewModel.subCategoriesMDL.observe(getViewLifecycleOwner(), new Observer<List<SubCategoryData>>() {
            @Override
            public void onChanged(List<SubCategoryData> subCategoriesDataList) {
                subCategoryAdapter.setList(subCategoriesDataList);
            }
        });

        viewModel.productsMDL.observe(getViewLifecycleOwner(), new Observer<List<ProductData>>() {
            @Override
            public void onChanged(List<ProductData> productDataList) {
                productAdapter.setList(productDataList);
            }
        });

        viewModel.filtersMDL.observe(getViewLifecycleOwner(), new Observer<List<FiltersData>>() {
            @Override
            public void onChanged(List<FiltersData> filtersData) {

            }
        });

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                Log.d("ProductListFragment", "onChanged: 39287 :"+responseState.getMessage());
            }
        });
    }

    @Override
    public void initLoading() {
        bd.pageTitleTV.setText(category.getTitle());
        viewModel.validateGetProducts(requireContext(), category);

        subCategoryAdapter = new SubCategoryAdapter(new SubCategoryViewListener() {
        });
        bd.subCategoryRV.setAdapter(subCategoryAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bd.productRV.setLayoutManager(linearLayoutManager);


        productAdapter = new ProductAdapter(new ProductViewListener() {
        });
        bd.productRV.setAdapter(productAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 3);
        bd.productRV.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void initErrorObserver() {

    }
}


