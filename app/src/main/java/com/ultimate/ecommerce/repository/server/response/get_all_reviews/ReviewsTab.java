package com.ultimate.ecommerce.repository.server.response.get_all_reviews;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsTab {
    @SerializedName("reviews")
    private List<String> reviews;
    @SerializedName("reviewCounts")
    private ReviewCounts reviewCounts;
    @SerializedName("averageRating")
    private String averageRating;
    @SerializedName("allowed")
    private boolean allowed;
    @SerializedName("content")
    private String content;
    @SerializedName("title")
    private String title;
    @SerializedName("hidden")
    private boolean hidden;
    @SerializedName("name")
    private String name;

    public ReviewsTab() {
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public ReviewCounts getReviewCounts() {
        return reviewCounts;
    }

    public void setReviewCounts(ReviewCounts reviewCounts) {
        this.reviewCounts = reviewCounts;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
