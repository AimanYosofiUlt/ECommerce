package com.ultimate.ecommerce.ui.fragment.home;

import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.BANNER_FOUR;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.BANNER_ONE;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.BANNER_THREE;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.BANNER_TOW;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.CATEGORIES;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.MIN_SLIDER_Four;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.MIN_SLIDER_THREE;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.MIN_SLIDER_TOW;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.PRODUCTS;
import static com.ultimate.ecommerce.ui.fragment.home.HomeSt.SEARCH_SLIDER_ONE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentHomeBinding;
import com.ultimate.ecommerce.databinding.ToolsTitleBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;
import com.ultimate.ecommerce.repository.server.response.homepage.base.BaseSection;
import com.ultimate.ecommerce.repository.server.response.homepage.base.CategorySection;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.category.views.CategoryAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.banner_four.BannerFourAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.banner_four.BannerFourViewListener;
import com.ultimate.ecommerce.ui.fragment.home.views.banner_one.BannerOneAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.banner_one.BannerOneViewListener;
import com.ultimate.ecommerce.ui.fragment.home.views.banner_three.BannerThreeAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.banner_three.BannerThreeViewListener;
import com.ultimate.ecommerce.ui.fragment.home.views.banner_two.BannerTowAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.banner_two.BannerTowViewListener;
import com.ultimate.ecommerce.ui.fragment.home.views.mainslider_four.MinSliderFourAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.mainslider_four.MinSliderFourViewListener;
import com.ultimate.ecommerce.ui.fragment.home.views.mainslider_three.MinSliderThreeAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.mainslider_three.MinSliderThreeViewListener;
import com.ultimate.ecommerce.ui.fragment.home.views.mainslider_two.MinSliderTowAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.mainslider_two.MinSliderTowViewListener;
import com.ultimate.ecommerce.ui.fragment.home.views.search_slider_one.SearchSliderOneAdapter;
import com.ultimate.ecommerce.ui.fragment.home.views.search_slider_one.SearchSliderOneViewListener;
import com.ultimate.ecommerce.ui.fragment.product_detail.ProductDetailFragmentDirections;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapter;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductViewListener;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<HomeFragmentViewModel> {
    FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.homepageResponseMDL.observe(getViewLifecycleOwner(), responseState -> {
            if (!responseState.isSuccessful()) {
                Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.homePageDataMDL.observe(getViewLifecycleOwner(), homePageData -> {
            for (BaseSection homeSection : homePageData.getHomeSections()) {
                Log.d("TAG", "initObservers: 98230:" + homePageData.toString());
                initHomePage(homeSection);
            }
        });
    }

    private void initHomePage(BaseSection homeSection) {
        switch (homeSection.getLayout()) {
            case SEARCH_SLIDER_ONE: {
                SearchSliderOneAdapter adapter = new SearchSliderOneAdapter(new SearchSliderOneViewListener() {
                });
                addSearchSliderOne(adapter);
                ArrayList<Banner> bannerDate = getBannerDate(homeSection);
                adapter.setList(bannerDate);
            }
            break;

            case MIN_SLIDER_TOW: {
                addTitle(homeSection.getTitle());
                MinSliderTowAdapter adapter = new MinSliderTowAdapter(new MinSliderTowViewListener() {
                });
                addSearchSliderOne(adapter);
                ArrayList<Banner> bannerDate = getBannerDate(homeSection);
                adapter.setList(bannerDate);
            }
            break;
            case MIN_SLIDER_THREE: {
                addTitle(homeSection.getTitle());
                MinSliderThreeAdapter adapter = new MinSliderThreeAdapter(new MinSliderThreeViewListener() {
                });
                addSearchSliderOne(adapter);
                ArrayList<Banner> bannerDate = getBannerDate(homeSection);
                adapter.setList(bannerDate);
            }
            break;
            case MIN_SLIDER_Four: {
                addTitle(homeSection.getTitle());
                MinSliderFourAdapter adapter = new MinSliderFourAdapter(new MinSliderFourViewListener() {
                });
                addSearchSliderOne(adapter);
                ArrayList<Banner> bannerDate = getBannerDate(homeSection);
                adapter.setList(bannerDate);
            }
            break;


            case BANNER_ONE: {
                addTitle(homeSection.getTitle());
                BannerOneAdapter adapter = new BannerOneAdapter(new BannerOneViewListener() {
                });
                addBanner(adapter);
                ArrayList<Banner> bannerDate = getBannerDate(homeSection);
                adapter.setList(bannerDate);
            }
            break;

            case BANNER_TOW: {
                addTitle(homeSection.getTitle());
                BannerTowAdapter adapter = new BannerTowAdapter(new BannerTowViewListener() {
                });
                addBanner(adapter);
                ArrayList<Banner> bannerDate = getBannerDate(homeSection);
                adapter.setList(bannerDate);
            }
            break;
            case BANNER_THREE: {
                addTitle(homeSection.getTitle());
                BannerThreeAdapter adapter = new BannerThreeAdapter(new BannerThreeViewListener() {
                });
                addBanner(adapter);
                ArrayList<Banner> bannerDate = getBannerDate(homeSection);
                adapter.setList(bannerDate);
            }
            break;
            case BANNER_FOUR: {
                addTitle(homeSection.getTitle());
                BannerFourAdapter adapter = new BannerFourAdapter(new BannerFourViewListener() {
                });
                addBanner(adapter);
                ArrayList<Banner> bannerDate = getBannerDate(homeSection);
                adapter.setList(bannerDate);
            }
            break;


            case CATEGORIES: {
                addTitle(homeSection.getTitle());
                addCategoriesSection(homeSection);
            }
            break;

            case PRODUCTS: {
                addProductsSection(homeSection);
            }
            break;

            default:
                Log.d("TAG", "initHomePage: 293170: TYPE OF OBJECT NOT HANDELED");
                break;
        }
    }

    private void addProductsSection(BaseSection homeSection) {
        RecyclerView recyclerView = getCustomRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true));

        ProductAdapter productAdapter = new ProductAdapter(true, new ProductViewListener() {
            @Override
            public void onAddToCart(ProductCart productCart) {
                viewModel.addToCart(productCart);
            }

            @Override
            public void onClick(ProductAdapterData data) {
                int productId = data.getData().getId();
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(
                                ProductDetailFragmentDirections.actionToSelf().setProductId(productId)
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
        recyclerView.setAdapter(productAdapter);

        ArrayList<ProductAdapterData> list = new ArrayList<>();
        for (Object sectionDatum : homeSection.getData()) {
            String json = new Gson().toJson(sectionDatum);
            ProductData categorySection = new Gson().fromJson(json, ProductData.class);
            list.add(new ProductAdapterData(categorySection));
        }

        productAdapter.setList(list);
    }

    private void addBanner(RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = getCustomRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);
    }

    private void addSearchSliderOne(RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = getCustomRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);
    }


    private ArrayList<Banner> getBannerDate(BaseSection homeSection) {
        ArrayList<Banner> list = new ArrayList<>();
        Log.d("TAG", "getBannerDate: 8327d:" + homeSection.getData());
        try {
            for (Object sectionDatum : homeSection.getData()) {
                String json = new Gson().toJson(sectionDatum);
                Banner data = new Gson().fromJson(json, Banner.class);
                list.add(data);
            }
        } catch (Exception NullPointerException) {
            Toast.makeText(requireContext(), "THERE IS EMPTY DATA", Toast.LENGTH_SHORT).show();
        }

        return list;
    }


    private void addTitle(String title) {
        View titleView = LayoutInflater.from(requireContext()).inflate(R.layout.tools_title, null, false);
        ToolsTitleBinding titleBinding = ToolsTitleBinding.bind(titleView);
        titleBinding.titleTV.setText(title);
        binding.homeLL.addView(titleView);
    }

    private void addCategoriesSection(BaseSection homeSection) {
        RecyclerView recyclerView = getCustomRecyclerView();
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));

        CategoryAdapter categoryAdapter = new CategoryAdapter(category -> {

        });

        recyclerView.setAdapter(categoryAdapter);

        ArrayList<Category> categories = new ArrayList<>();
        for (Object sectionDatum : homeSection.getData()) {
            String json = new Gson().toJson(sectionDatum);
            CategorySection categorySection = new Gson().fromJson(json, CategorySection.class);
            Category category = new Category(categorySection.getId(),
                    categorySection.getImagetitle(),
                    categorySection.getImagetitle(),
                    categorySection.getImagetitle(),
                    categorySection.getUrl(),
                    0,
                    0,
                    categorySection.getColorone(),
                    categorySection.getColortwo());

            categories.add(category);
        }

        categoryAdapter.setList(categories);
    }

    private RecyclerView getCustomRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(requireContext());
        int dp16 = getResources().getDimensionPixelOffset(R.dimen.dp_16);
        recyclerView.setPadding(dp16, dp16, dp16, dp16);
        recyclerView.setClipToPadding(false);
        binding.homeLL.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public void initLoading() {
        binding.searchImg.setColorFilter(DynamicTheme.gradientStartColor);
        viewModel.validateGetHomePageData(requireContext());
    }

    @Override
    public void initErrorObserver() {

    }
}