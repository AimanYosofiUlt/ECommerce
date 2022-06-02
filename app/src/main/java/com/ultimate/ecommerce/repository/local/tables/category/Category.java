package com.ultimate.ecommerce.repository.local.tables.category;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Category implements Serializable {
    @PrimaryKey
    Integer id;
    String slug;
    String title;
    String description;
    String image;
    // todo what a parent
    Integer parent;
    Integer count;
    String gradientStartColor;
    String gradientEndColor;

    public Category() {
    }

    @Ignore
    public Category(Integer id, String slug, String title, String description
            , String image
            , Integer parent, Integer count
            , String gradientStartColor, String gradientEndColor) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.image = image;
        this.parent = parent;
        this.count = count;
        this.gradientStartColor = gradientStartColor;
        this.gradientEndColor = gradientEndColor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getGradientStartColor() {
        return gradientStartColor;
    }

    public void setGradientStartColor(String gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
    }

    public String getGradientEndColor() {
        return gradientEndColor;
    }

    public void setGradientEndColor(String gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
    }
}
