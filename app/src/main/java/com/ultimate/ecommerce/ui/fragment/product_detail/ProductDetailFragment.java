package com.ultimate.ecommerce.ui.fragment.product_detail;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentProductDetailBinding;
import com.ultimate.ecommerce.databinding.FragmentProductDetailBodyBinding;
import com.ultimate.ecommerce.databinding.ToolsAllStarsBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_product.GetProductData;
import com.ultimate.ecommerce.repository.server.response.get_product.Image;
import com.ultimate.ecommerce.repository.server.response.get_product.RelatedProducts;
import com.ultimate.ecommerce.repository.server.response.get_product.Reviewcounts;
import com.ultimate.ecommerce.repository.server.response.get_product.ReviewsTab;
import com.ultimate.ecommerce.repository.server.response.get_product.ShortDescription;
import com.ultimate.ecommerce.repository.server.response.get_product.Tabs;
import com.ultimate.ecommerce.repository.server.response.get_products.Categories;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.product_detail.views.image.ImageAdapter;
import com.ultimate.ecommerce.ui.fragment.product_detail.views.image.ImageViewListener;
import com.ultimate.ecommerce.ui.fragment.product_detail.views.product_feature.ProductFeatureAdapter;
import com.ultimate.ecommerce.ui.fragment.product_detail.views.product_feature.ProductFeatureViewListener;
import com.ultimate.ecommerce.ui.fragment.product_detail.views.review.ReviewAdapter;
import com.ultimate.ecommerce.ui.fragment.product_detail.views.review.ReviewViewListener;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapter;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductViewListener;
import com.ultimate.ecommerce.ui.fragment.product_list.views.sub_category.SubCategoryAdapter;
import com.ultimate.ecommerce.ui.fragment.product_list.views.sub_category.SubCategoryViewListener;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductDetailFragment extends BaseFragment<ProductDetailFragmentViewModel> {
    FragmentProductDetailBinding binding;
    ProductAdapterData product;
    int productId;
    ImageAdapter imageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(getLayoutInflater());
        productId = ProductDetailFragmentArgs.fromBundle(getArguments()).getProductId();
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.body.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.body.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.body.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void initObservers() {
        viewModel.getDetailResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                Log.d("ProductDetailFragment", "onChanged: 9238742:" + responseState.getMessage());
                // todo handle responseState
            }
        });

        viewModel.productResponseMDL.observe(getViewLifecycleOwner(), this::initScreenDetail);
    }

    private void initScreenDetail(GetProductData detail) {
        initViewPager(detail.getImages());
        initCategories(detail.getCategories());
        initProductInfoPanel(detail);
        initProductDescriptionPanel(detail.getShortDescription(), detail.getTabs());
        initRatingPanel(detail.getReviewstab());
        initSimilarProducts(detail.getRelatedProducts());
    }

    private void initCategories(List<Categories> categories) {
        SubCategoryAdapter categoryAdapter = new SubCategoryAdapter(new SubCategoryViewListener() {
        });
        categoryAdapter.setList(categories);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true);
        binding.body.categoriesRV.setLayoutManager(layoutManager);
        binding.body.categoriesRV.setAdapter(categoryAdapter);
    }

    private void initViewPager(List<Image> images) {
        imageAdapter.setList(images);
        binding.imageVP.setAdapter(imageAdapter);
        binding.imageVP.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);
        setViewPagerMotion();
    }

    private void setViewPagerMotion() {
        final Handler sliderHandler = new Handler();
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        binding.imageVP.setPageTransformer(compositePageTransformer);

        binding.imageVP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });
    }

    int d = 1;
    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (binding.imageVP.getCurrentItem() == imageAdapter.getItemCount() - 1) {
                d = -1;
            } else if (binding.imageVP.getCurrentItem() == 0) {
                d = 1;
            }

            binding.imageVP.setCurrentItem(binding.imageVP.getCurrentItem() + d);
        }
    };

    private void initSimilarProducts(RelatedProducts relatedProducts) {
        boolean hasRelatedProducts = !relatedProducts.isHidden() && relatedProducts.getProducts().size() > 0;
        if (!hasRelatedProducts) {
            binding.similarGroup.setVisibility(View.GONE);
            return;
        }
        ProductAdapter productAdapter = new ProductAdapter(true, new ProductViewListener() {
            @Override
            public void onAddToCart(ProductCart productCart) {
                viewModel.addToCart(productCart);
            }

            @Override
            public void onClick(ProductAdapterData data) {
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(
                                ProductDetailFragmentDirections.actionToSelf().setProductId(data.getData().getId())
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

//        productAdapter.setList(relatedProducts.getProducts());
        binding.similarRV.setAdapter(productAdapter);
    }

    private void initProductInfoPanel(GetProductData detail) {
        FragmentProductDetailBodyBinding bodyBinding = binding.body;
        bodyBinding.productNameTV.setText(detail.getTitle());
        if (detail.getSalePrice().equals("")) {
            bodyBinding.priceTV.setText(detail.getRegularPrice());
            bodyBinding.beforeDiscountTV.setVisibility(View.GONE);
        } else {
            bodyBinding.priceTV.setText(detail.getSalePrice());
            bodyBinding.beforeDiscountTV.setText(detail.getRegularPrice());
        }
//todo change product count in cart
//        viewModel.getProductCountFromCart();
    }

    private void initProductDescriptionPanel(ShortDescription description, List<Tabs> tabs) {
        if (description.isHidden() && description.getContent().isEmpty()) {
            binding.body.shortDescTV.setVisibility(View.GONE);
            initFeatureTabs(false, tabs);
            return;
        }

        binding.body.shortDescTV.setText(description.getContent());
        initFeatureTabs(true, tabs);
    }

    private void initRatingPanel(ReviewsTab reviewstab) {
        Reviewcounts reviewCounts = reviewstab.getReviewCounts();
        String averageRating = reviewstab.getAverageRating();
        int allStars = reviewCounts.getAll();
        int oneStar = reviewCounts.getOneStar();
        int twoStar = reviewCounts.getTwoStar();
        int threeStar = reviewCounts.getThereStar();
        int fourStar = reviewCounts.getFourStar();
        int fiveStar = reviewCounts.getFiveStar();

        binding.rating.totalRatingTV.setText(String.valueOf(allStars));
        binding.rating.ratingCountTV.setText("(" + averageRating + ")");

        ToolsAllStarsBinding allRatingBinding = binding.rating.allRating;
        allRatingBinding.oneCountTV.setText(String.valueOf(oneStar));
        allRatingBinding.towCountTV.setText(String.valueOf(twoStar));
        allRatingBinding.threeCountTV.setText(String.valueOf(threeStar));
        allRatingBinding.fourCountTV.setText(String.valueOf(fourStar));
        allRatingBinding.fiveCountTV.setText(String.valueOf(fiveStar));
        allRatingBinding.oneStarPB.setProgress(oneStar);
        allRatingBinding.towStarPB.setProgress(twoStar);
        allRatingBinding.threeStarPB.setProgress(threeStar);
        allRatingBinding.fourStarPB.setProgress(fourStar);
        allRatingBinding.fiveStarPB.setProgress(fiveStar);

        setRatingReviews(reviewstab.getReviews());
    }

    private void setRatingReviews(List<String> reviews) {
        ReviewAdapter reviewAdapter = new ReviewAdapter(new ReviewViewListener() {
        });
        reviewAdapter.setList(reviews);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.rating.reviewRV.setLayoutManager(layoutManager);
        binding.rating.reviewRV.setAdapter(reviewAdapter);
    }

    private void initFeatureTabs(boolean hasDescription, List<Tabs> tabs) {
        List<Tabs> visibleTabs = getVisibleTabs(tabs);
        boolean isVisible = visibleTabs.size() > 0;

        if (!isVisible) {
            if (hasDescription)
                binding.body.descTitle.CL.setVisibility(View.GONE);
            binding.body.featureRV.setVisibility(View.GONE);
            return;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.body.featureRV.setLayoutManager(layoutManager);
        ProductFeatureAdapter featureAdapter = new ProductFeatureAdapter(new ProductFeatureViewListener() {
        });
        binding.body.featureRV.setAdapter(featureAdapter);
        featureAdapter.setList(visibleTabs);
    }

    private List<Tabs> getVisibleTabs(List<Tabs> tabs) {
        List<Tabs> list = new ArrayList<>();
        for (Tabs tab : tabs) {
            boolean isVisiable = !tab.isHidden() && !tab.getContent().equals("");
            if (isVisiable) {
                list.add(tab);
            }
        }
        return list;
    }

    @Override
    public void initLoading() {
        binding.body.descTitle.startTitle.setText(getString(R.string.detail));
        binding.rating.title.startTitle.setText(getString(R.string.ratings));
        binding.rating.seeAllBtn.btnTextTV.setText(getString(R.string.see_all));
        binding.similarTitle.startTitle.setText(getString(R.string.similar_product));
        binding.body.categoriesTitle.startTitle.setText(getString(R.string.category));

        imageAdapter = new ImageAdapter(new ImageViewListener() {
        });
        binding.imageVP.setAdapter(imageAdapter);
        binding.dotsIndicaotr.attachTo(binding.imageVP);
        
        viewModel.validateGetProductDetail(requireContext(), product.getData().getId());
    }

    @Override
    public void initErrorObserver() {

    }
}


