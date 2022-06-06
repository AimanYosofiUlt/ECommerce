package com.ultimate.ecommerce.ui.fragment.main;

import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.ABOUT;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.ADDRESS;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.CONTACT_US;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.FAVORITE;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.HELP;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.LANG_CUR;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.LOGIN_REGISTER;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.LOGOUT;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.ORDERS;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.USER_PROFILE;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.app.GlobalVariable;
import com.ultimate.ecommerce.databinding.FragmentMainBinding;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.cart.CartFragment;
import com.ultimate.ecommerce.ui.fragment.cart.CartFragmentListener;
import com.ultimate.ecommerce.ui.fragment.category.CategoryFragment;
import com.ultimate.ecommerce.ui.fragment.home.HomeFragment;
import com.ultimate.ecommerce.ui.fragment.main.views.mainviewpager.MainPagerAdapter;
import com.ultimate.ecommerce.ui.fragment.setting.SettingFragment;
import com.ultimate.ecommerce.utilities.LayoutUtil;
import com.ultimate.ecommerce.utilities.ValidateSt;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends BaseFragment<MainFragmentViewModel> {
    private static final String TAG = "MainFragment";

    FragmentMainBinding binding;
    MainPagerAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());

        initBottomNavBar();

        return binding.getRoot();
    }

    private void initBottomNavBar() {
        float radius = getResources().getDimension(R.dimen.default_corner_radius);

        MaterialShapeDrawable bottomBarBackground = (MaterialShapeDrawable) binding.bottomAppBar.getBackground();
        bottomBarBackground.setShapeAppearanceModel(
                bottomBarBackground.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopRightCorner(CornerFamily.ROUNDED, radius)
                        .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                        .build());
    }

    @Override
    public void initObservers() {
        viewModel.logoutResponseMDL.observe(getViewLifecycleOwner(), responseState -> {
            hideProgress();
            if (!responseState.isSuccessful())
                handleResponseError(responseState.getMessage());
        });
    }

    private void handleResponseError(String message) {
        switch (message) {
            case ValidateSt.NO_INTERNET_CONNECTION:
                Toast.makeText(requireContext(), getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                break;

            default:
                Log.d(TAG, "handelResposeError: this error not handeled :" + message);
        }
    }

    @Override
    public void initLoading() {
        initFragmentAdapter();
        initBottomNavBarIconColors();
        binding.mainVP.setAdapter(adapter);
        binding.mainVP.setUserInputEnabled(false);
    }

    private void initBottomNavBarIconColors() {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked},
                new int[]{}
        };

        int[] colors = new int[]{
                DynamicTheme.gradientStartColor,
                Color.GRAY
        };

        ColorStateList myColorList = new ColorStateList(states, colors);
        binding.bottomNavigationView.setItemIconTintList(myColorList);
    }

    private void initFragmentAdapter() {
        HomeFragment homeFragment = new HomeFragment(searchText -> NavHostFragment.findNavController(requireParentFragment())
                .navigate(MainFragmentDirections.actionMainToProductSearch().setSearchText(searchText)));

        CategoryFragment categoryFragment = new CategoryFragment(category ->
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(MainFragmentDirections.actionMainToProductList().setCategory(category)));

        CartFragment cartFragment = new CartFragment(new CartFragmentListener() {
            @Override
            public void onOrderConfirmReq(UpdateCartData data) {
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(MainFragmentDirections.actionMainToOrderConfirm(data));
            }

            @Override
            public void onLoginReq() {
                Toast.makeText(requireContext(), getString(R.string.login_first), Toast.LENGTH_LONG).show();
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(MainFragmentDirections.actionMainToLogin());
            }
        });

        SettingFragment settingFragment = getSettingFragment();

        adapter = new MainPagerAdapter(requireParentFragment());
        adapter.addFragment(homeFragment);
        adapter.addFragment(categoryFragment);
        adapter.addFragment(cartFragment);
        adapter.addFragment(settingFragment);
    }

    private SettingFragment getSettingFragment() {
        return new SettingFragment(id -> {
            NavController navController = NavHostFragment.findNavController(requireParentFragment());

            switch (id) {
                case ORDERS:
                    navController.navigate(R.id.actionMainToOrder);
                    break;

                case ADDRESS:
                    navController.navigate(R.id.actionMainToAddress);
                    break;

                case FAVORITE:
                    navController.navigate(R.id.actionMainToFavorite);
                    break;

                case LANG_CUR:
                    navController.navigate(R.id.actionMainToLangCurrency);
                    break;

                case HELP:
                    navController.navigate(R.id.actionMainToHelp);
                    break;

                case ABOUT:
                    navController.navigate(R.id.actionMainToAboutUs);
                    break;

                case CONTACT_US:
                    navController.navigate(R.id.actionMainToContactUs);
                    break;

                case LOGOUT:
                    logoutUser();
                    break;

                case LOGIN_REGISTER:
                    navController.navigate(R.id.actionMainToLogin);
                    break;

                case USER_PROFILE:
                    navController.navigate(R.id.actionMainToProfile);
                    break;
            }
        });
    }

    private void logoutUser() {
        LayoutUtil.showOptionDialog(requireContext()
                , getString(R.string.logout)
                , getString(R.string.logout_msg)
                , () -> {
                    showProgress(requireContext(), getString(R.string.logout), getString(R.string.loading));
                    viewModel.validateLogout(requireContext());
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalVariable.bottomNavHeight = 0;
        binding.bottomNavigationView.getViewTreeObserver().addOnGlobalLayoutListener(() ->
                GlobalVariable.bottomNavHeight = binding.bottomNavigationView.getHeight() + binding.fab.getHeight() / 2);
    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initEvent() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                binding.mainVP.setCurrentItem(0);
            } else if (item.getItemId() == R.id.category) {
                binding.mainVP.setCurrentItem(1);
            } else if (item.getItemId() == R.id.cart) {
                binding.mainVP.setCurrentItem(2);
            } else if (item.getItemId() == R.id.setting) {
                binding.mainVP.setCurrentItem(3);
            }

            item.setChecked(true);
            return false;
        });
    }
}