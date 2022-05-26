package com.ultimate.ecommerce.ui.fragment.review_list;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentReviewBinding;
import com.ultimate.ecommerce.databinding.ToolsAllStarsBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_all_reviews.GetAllReviewsData;
import com.ultimate.ecommerce.repository.server.response.get_all_reviews.ReviewCounts;
import com.ultimate.ecommerce.repository.server.response.get_all_reviews.ReviewsTab;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.product_detail.views.review.ReviewAdapter;
import com.ultimate.ecommerce.ui.fragment.product_detail.views.review.ReviewViewListener;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;
import com.ultimate.ecommerce.ui.fragment.review_list.dialogs.rate.RateDialog;
import com.ultimate.ecommerce.ui.fragment.review_list.dialogs.rate.RateDialogListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReviewFragment extends BaseFragment<ReviewFragmentViewModel> {
    ProductAdapterData product;
    FragmentReviewBinding binding;

    ReviewAdapter reviewAdapter;
    AlertDialog rateDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReviewBinding.inflate(getLayoutInflater());
        //todo check this comment
        //        product  ReviewFragmentArgs.fromBundle(getArguments()).getProduct();
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.placeholder.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment())
                .popBackStack());

        binding.placeholder.addBtn.setOnClickListener(view -> {
            rateDialog = new RateDialog(requireContext(), new RateDialogListener() {
                @Override
                public void onAddReviewReq(String review, int rate) {
                    viewModel.validateAddReview(getContext(), review, rate);
                }
            }).create();
            rateDialog.show();
        });
    }

    @Override
    public void initObservers() {
        viewModel.getReviewsResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                LayoutUtil.hideShimmer(binding.placeholder.placeholderCL, binding.shimmer.shimmerL);
                //todo handel error from saever
                Log.d("ReviewFragment", "onChanged: dq32323:" + responseState.getMessage());
            }
        });

        viewModel.reviewsMDL.observe(getViewLifecycleOwner(), new Observer<GetAllReviewsData>() {
            @Override
            public void onChanged(GetAllReviewsData data) {
                initRatingPanel(data.getReviewsTab());
                List<String> reviews = data.getReviewsTab().getReviews();
                reviewAdapter.setList(reviews);
            }

            private void initRatingPanel(ReviewsTab reviewstab) {
                ReviewCounts reviewCounts = reviewstab.getReviewCounts();
                String averageRating = reviewstab.getAverageRating();
                int allStars = reviewCounts.getAll();
                int oneStar = reviewCounts.getOneStar();
                int twoStar = reviewCounts.getTwoStar();
                int threeStar = reviewCounts.getThereStar();
                int fourStar = reviewCounts.getFourStar();
                int fiveStar = reviewCounts.getFiveStar();
                binding.placeholder.totalRatingTV.setText(String.valueOf(allStars));
                binding.placeholder.ratingCountTV.setText("(" + averageRating + ")");

                ToolsAllStarsBinding allRatingBinding = binding.placeholder.allRating;
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
                binding.placeholder.reviewRV.setLayoutManager(layoutManager);
                binding.placeholder.reviewRV.setAdapter(reviewAdapter);
            }
        });

        viewModel.addReviewResponseMDl.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (responseState.isSuccessful()) {
                    rateDialog.dismiss();
                } else {
                    handleErrorMessage(responseState.getMessage());
                }
            }

            private void handleErrorMessage(String message) {
                switch (message) {
                    case NO_INTERNET_CONNECTION:
                        Toast.makeText(requireContext(), getText(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        //todo handel server error
                        Log.d("ReviewFragment", "handleErrorMessage: 23o4982:" + message);
                }
            }
        });
    }

    @Override
    public void initLoading() {
        reviewAdapter = new ReviewAdapter(new ReviewViewListener() {
        });
        binding.placeholder.reviewRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.placeholder.reviewRV.setAdapter(reviewAdapter);

        //todo set real product name
        String productName = "Name";
        binding.placeholder.pageTitleTV.setText(productName);
        binding.shimmer.pageTitleTV.setText(productName);

        loadingReviews();
    }

    void loadingReviews() {
        LayoutUtil.showShimmer(binding.placeholder.placeholderCL, binding.shimmer.shimmerL);

        //todo use user product not static id
        //        viewModel.validateGetReviews(product.getData().getId(),requireContext());
        viewModel.validateGetReviews(15782, requireContext());
    }

    @Override
    public void initErrorObserver() {

    }
}


