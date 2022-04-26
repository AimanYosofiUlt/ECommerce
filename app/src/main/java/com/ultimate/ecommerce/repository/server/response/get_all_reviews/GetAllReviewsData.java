package com.ultimate.ecommerce.repository.server.response.get_all_reviews;

import com.google.gson.annotations.SerializedName;

public class GetAllReviewsData {
    @SerializedName("reviewsTab")
    private ReviewsTab reviewsTab;
    @SerializedName("title")
    private String title;
    @SerializedName("slug")
    private String slug;
    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private int id;

    public GetAllReviewsData() {
    }

    public ReviewsTab getReviewsTab() {
        return reviewsTab;
    }

    public void setReviewsTab(ReviewsTab reviewsTab) {
        this.reviewsTab = reviewsTab;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
