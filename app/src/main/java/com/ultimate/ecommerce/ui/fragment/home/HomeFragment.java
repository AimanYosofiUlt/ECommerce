package com.ultimate.ecommerce.ui.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentHomeBinding;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.category.views.CategoryViewAdapter;
import com.ultimate.ecommerce.ui.fragment.category.views.CategoryViewListener;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<HomeFragmentViewModel> {
    FragmentHomeBinding bd;

    private static final String TAG = "HomeFragment";

    // todo where is the check version to update data and what the data should updated and what don't

    CategoryViewAdapter categoryViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentHomeBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.homepageResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (!responseState.isSuccessful()) {
                    Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.homePageDataMDL.observe(getViewLifecycleOwner(), new Observer<HomePageData>() {
            @Override
            public void onChanged(HomePageData homePageData) {

            }
        });

        viewModel.getCategoriesResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (!responseState.isSuccessful()) {
                    Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Log.d(TAG, "onChanged 87326: "+responseState.getMessage());
            }
        });

//        viewModel.categoriesLiveData.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
//            @Override
//            public void onChanged(List<Category> categories) {
//                Log.d(TAG, "onChanged: "+categories.size());
//                categoryViewAdapter.setList(categories);
//            }
//        });
    }

    @Override
    public void initLoading() {
        bd.searchImg.setColorFilter(DynamicTheme.gradientStartColor);
//        viewModel.getHomePageData();
        viewModel.getCategory();

        categoryViewAdapter = new CategoryViewAdapter(new CategoryViewListener() {
            @Override
            public void onOpenReq(Category category) {

            }
        });

        bd.categoryRV.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        bd.categoryRV.setAdapter(categoryViewAdapter);
    }

    @Override
    public void initErrorObserver() {

    }
}