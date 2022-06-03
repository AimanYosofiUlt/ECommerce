package com.ultimate.ecommerce.ui.fragment.favorite;

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

import com.google.gson.Gson;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentFavoriteBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.favorite.views.product.FavoriteAdapterData;
import com.ultimate.ecommerce.ui.fragment.favorite.views.product.FavoriteProductAdapter;
import com.ultimate.ecommerce.ui.fragment.favorite.views.product.FavoriteProductViewListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import java.util.List;

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
        viewModel.favoriteLiveData.observe(getViewLifecycleOwner(), new Observer<List<FavoriteAdapterData>>() {
            @Override
            public void onChanged(List<FavoriteAdapterData> data) {
                Toast.makeText(requireContext(),new Gson().toJson(data),Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onChanged: 238:"+new Gson().toJson(data));
                adapter.setList(data);
            }
        });
    }

    @Override
    public void initLoading() {
        adapter = new FavoriteProductAdapter(new FavoriteProductViewListener() {
            @Override
            public void onAddToCart(ProductCart productCart) {
                viewModel.addToCart(productCart);
            }

            @Override
            public void onClick(Favorite data) {
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(
                                FavoriteFragmentDirections.actionFavoriteToProductDetail().setProductId(data.getId())
                        );
            }

            @Override
            public void removeFromFavorite(Favorite data) {
                LayoutUtil.showOptionDialog(requireContext()
                        , getString(R.string.favorite_delete_from)
                        , getString(R.string.favorite_delete_from_msg)
                        , () -> viewModel.removeFromFavorite(data));
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


