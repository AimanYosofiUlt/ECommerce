package com.ultimate.ecommerce.ui.fragment.review_list;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.repos.review.ReviewRepo;
import com.ultimate.ecommerce.repository.server.response.add_review.AddReviewResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_all_reviews.GatAllReviewsResponse;
import com.ultimate.ecommerce.repository.server.response.get_all_reviews.GetAllReviewsData;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.ValidateSt;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import javax.inject.Inject;

public class ReviewFragmentViewModel extends BaseViewModel {
    @Inject
    ReviewRepo reviewRepo;

    MutableLiveData<ResponseState> getReviewsResponseMDL;
    MutableLiveData<GetAllReviewsData> reviewsMDL;

    MutableLiveData<ResponseState> addReviewResponseMDl;

    @Inject
    public ReviewFragmentViewModel(@NonNull Application application) {
        super(application);
        getReviewsResponseMDL = new MutableLiveData<>();
        reviewsMDL = new MutableLiveData<>();
        addReviewResponseMDl = new MutableLiveData<>();
    }

    public void validateGetReviews(int id, Context context) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        getAllReviews(id);
                    }

                    @Override
                    public void onDisconnect() {
                        getReviewsResponseMDL.setValue(ResponseState.failureState(ValidateSt.NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void getAllReviews(int id) {
        reviewRepo.getAllReviews(id, new ResponsesCallBack<GatAllReviewsResponse>() {
            @Override
            public void onSuccess(GatAllReviewsResponse response) {
                GetAllReviewsData data = response.getData();
                reviewsMDL.setValue(data);
                getReviewsResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getReviewsResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }


    public void validateAddReview(Context context, String review, int rate) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        addReview(review,rate);
                    }

                    @Override
                    public void onDisconnect() {
                        addReviewResponseMDl.setValue(ResponseState.failureState(ValidateSt.NO_INTERNET_CONNECTION));
                    }
                });
    }

    private void addReview(String review, int rate) {
        reviewRepo.addReview(review,rate, new ResponsesCallBack<AddReviewResponse>() {
            @Override
            public void onSuccess(AddReviewResponse response) {
                addReviewResponseMDl.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                addReviewResponseMDl.setValue(ResponseState.failureState(msg));
            }
        });
    }
}