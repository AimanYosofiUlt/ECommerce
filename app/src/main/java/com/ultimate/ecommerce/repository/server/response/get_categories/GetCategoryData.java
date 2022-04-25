package com.ultimate.ecommerce.repository.server.response.get_categories;

import com.google.gson.annotations.SerializedName;

public class GetCategoryData {
    @SerializedName("gradientEndColor")
    private String gradientEndColor;
    @SerializedName("gradientStartColor")
    private String gradientStartColor;
    @SerializedName("count")
    private int count;
    @SerializedName("parent")
    private int parent;
    @SerializedName("image")
    private String image;
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("slug")
    private String slug;
    @SerializedName("id")
    private int id;

    public GetCategoryData() {
    }

    public String getGradientEndColor() {
        return gradientEndColor;
    }

    public void setGradientEndColor(String gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
    }

    public String getGradientStartColor() {
        return gradientStartColor;
    }

    public void setGradientStartColor(String gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
