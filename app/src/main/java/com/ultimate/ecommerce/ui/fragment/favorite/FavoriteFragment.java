package com.ultimate.ecommerce.ui.fragment.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ultimate.ecommerce.databinding.FragmentFavoriteBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.favorite.views.product.FavoriteProductAdapter;
import com.ultimate.ecommerce.ui.fragment.favorite.views.product.FavoriteProductViewListener;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteFragment extends BaseFragment<FavoriteFragmentViewModel> {
    FragmentFavoriteBinding binding;
    FavoriteProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.favoriteLiveData.observe(getViewLifecycleOwner(), favorites -> adapter.setList(favorites));
    }

    @Override
    public void initLoading() {
        adapter = new FavoriteProductAdapter(new FavoriteProductViewListener() {
            @Override
            public void onAddToCart(ProductCart productCart) {

            }

            @Override
            public void onClick(Favorite data) {

            }

            @Override
            public void removeFromFavorite(Favorite data) {

            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        binding.favoriteRV.setLayoutManager(layoutManager);
        binding.favoriteRV.setAdapter(adapter);
    }

    @Override
    public void initErrorObserver() {

    }
}


