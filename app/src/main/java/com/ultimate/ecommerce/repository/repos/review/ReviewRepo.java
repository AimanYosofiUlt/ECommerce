package com.ultimate.ecommerce.repository.repos.review;

import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.add_review.AddReviewResponse;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_all_reviews.GatAllReviewsResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class ReviewRepo extends BaseRepo {
    @Inject
    public ReviewRepo() {
    }

    public void getAllReviews(int id, ResponsesCallBack<GatAllReviewsResponse> callBack) {
        RequestBody request = BaseRequest.getGetAllReviewsRequest(id);
        api.getAllReviews(request).enqueue(callBack);
    }

    public void addReview(String review, ResponsesCallBack<AddReviewResponse> callBack) {
//        RequestBody request = BaseRequest.getAddReviewRequest(review);
//        api.addReview(request).enqueue(callBack);
    }
}
