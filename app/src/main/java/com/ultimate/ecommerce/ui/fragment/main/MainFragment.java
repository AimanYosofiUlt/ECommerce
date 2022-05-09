package com.ultimate.ecommerce.ui.fragment.main;

import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.ABOUT;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.ADDRESS;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.CONTACT_US;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.FAVORITE;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.HELP;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.LANG_CUR;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.LOGIN_REGISTER;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.LOGOUT;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingFragment.USER_PROFILE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.GlobalVariable;
import com.ultimate.ecommerce.databinding.FragmentMainBinding;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.category.CategoryFragment;
import com.ultimate.ecommerce.ui.fragment.category.views.CategoryViewListener;
import com.ultimate.ecommerce.ui.fragment.home.HomeFragment;
import com.ultimate.ecommerce.ui.fragment.main.views.mainviewpager.MainViewPagerAdapter;
import com.ultimate.ecommerce.ui.fragment.setting.SettingFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends BaseFragment {
    FragmentMainBinding bd;
    MainViewPagerAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentMainBinding.inflate(getLayoutInflater());

        initBottomNavBar();

        return bd.getRoot();
    }

    private void initBottomNavBar() {
        float radius = getResources().getDimension(R.dimen.default_corner_radius);

        MaterialShapeDrawable bottomBarBackground = (MaterialShapeDrawable) bd.bottomAppBar.getBackground();
        bottomBarBackground.setShapeAppearanceModel(
                bottomBarBackground.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopRightCorner(CornerFamily.ROUNDED,radius)
                        .setTopLeftCorner(CornerFamily.ROUNDED,radius)
                        .build());
    }

    @Override
    public void initObservers() {

    }

    @Override
    public void initLoading() {
        initFragmentAdapter();

        bd.mainVP.setAdapter(adapter);
        bd.mainVP.setUserInputEnabled(false);
    }

    private void initFragmentAdapter() {
        adapter = new MainViewPagerAdapter(requireParentFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new CategoryFragment(new CategoryViewListener() {
            @Override
            public void onOpenReq(Category category) {
                MainFragmentDirections.ActionMainToProductList action =
                        MainFragmentDirections.actionMainToProductList().setCategory(category);
                NavHostFragment.findNavController(requireParentFragment()).navigate(action);
                Log.d("MainFragment", "onOpenReq: 0349: ");
            }
        }));

        SettingFragment settingFragment = getSettingFragment();
        adapter.addFragment(settingFragment);
    }

    private SettingFragment getSettingFragment() {
        return new SettingFragment(id -> {
            NavController navController = NavHostFragment.findNavController(requireParentFragment());

            switch (id) {
//                    case ORDERS:
//                        navController.navigate(R.id.actionMainTo);
//                        break;

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

    @Override
    public void onStart() {
        super.onStart();
        GlobalVariable.bottomNavHeight = 0;
        bd.bottomNavigationView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                GlobalVariable.bottomNavHeight = bd.bottomNavigationView.getHeight() + bd.fab.getHeight() / 2;
                Log.d("MainFragment", "initLoading: 9438753:" + bd.bottomNavigationView.getHeight() + "  " + bd.fab.getHeight() / 2);
            }
        });
    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initEvent() {
        bd.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    bd.mainVP.setCurrentItem(0);
                } else if (item.getItemId() == R.id.category) {
                    bd.mainVP.setCurrentItem(1);
                } else if (item.getItemId() == R.id.cart) {
                    bd.mainVP.setCurrentItem(2);
                } else if (item.getItemId() == R.id.setting) {
                    bd.mainVP.setCurrentItem(3);
                }
                return false;
            }
        });
    }
}