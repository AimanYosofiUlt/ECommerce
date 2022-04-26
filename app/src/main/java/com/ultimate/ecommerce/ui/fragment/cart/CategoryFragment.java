package com.ultimate.ecommerce.ui.fragment.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentCartBinding;
import com.ultimate.ecommerce.databinding.FragmentCategoryBinding;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.category.views.CategoryViewAdapter;
import com.ultimate.ecommerce.ui.fragment.category.views.CategoryViewListener;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryFragment extends BaseFragment<CategoryFragmentViewModel> {
    FragmentCartBinding bd;

    private static final String TAG = "CategoryFragment";
    CategoryViewAdapter categoryViewAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentCartBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }


    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {

    }

    @Override
    public void initLoading() {

    }

    @Override
    public void initErrorObserver() {

    }
}
