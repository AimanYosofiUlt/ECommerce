package com.ultimate.ecommerce.ui.fragment.category;
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

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentCategoryBinding;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.category.views.CategoryAdapter;
import com.ultimate.ecommerce.ui.fragment.category.views.CategoryViewListener;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryFragment extends BaseFragment<CategoryFragmentViewModel> {
    FragmentCategoryBinding bd;

    private static final String TAG = "CategoryFragment";
    CategoryAdapter categoryViewAdapter;
    CategoryViewListener listener;

    public CategoryFragment(CategoryViewListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentCategoryBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.categoryMDL.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryViewAdapter.setList(categories);
            }
        });

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                bd.internetCheck.progressBar.setVisibility(View.GONE);
                Log.d("CategoryFragment", "onChanged: 29387428: " + responseState.getMessage());
            }
        });
    }

    @Override
    public void initLoading() {
        categoryViewAdapter = new CategoryAdapter(listener);
        bd.categoryRV.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        bd.categoryRV.setAdapter(categoryViewAdapter);
        bd.title.titleTV.setText(getString(R.string.category));

        bd.internetCheck.progressBar.setVisibility(View.VISIBLE);
        viewModel.validateGetCategory(requireContext());
    }

    @Override
    public void initErrorObserver() {

    }
}
