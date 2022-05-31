package com.ultimate.ecommerce.repository.server.response.update_cart;

import com.google.gson.annotations.SerializedName;

public class Products {
    @SerializedName("userQuantity")
    private int userQuantity;
    @SerializedName("stockQuantity")
    private int stockQuantity;
    @SerializedName("stockStatus")
    private String stockStatus;
    @SerializedName("manageStock")
    private String manageStock;
    @SerializedName("availability")
    private boolean availability;
    @SerializedName("title")
    private String title;
    @SerializedName("ID")
    private int ID;

    public Products() {
    }


    public int getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(int userQuantity) {
        this.userQuantity = userQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getManageStock() {
        return manageStock;
    }

    public void setManageStock(String manageStock) {
        this.manageStock = manageStock;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
