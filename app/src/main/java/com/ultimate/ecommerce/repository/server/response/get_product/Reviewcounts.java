package com.ultimate.ecommerce.repository.server.response.get_product;

import com.google.gson.annotations.SerializedName;

public class Reviewcounts {
    @SerializedName("oneStar")
    private int oneStar;
    @SerializedName("twoStar")
    private int twoStar;
    @SerializedName("thereStar")
    private int thereStar;
    @SerializedName("fourStar")
    private int fourStar;
    @SerializedName("fiveStar")
    private int fiveStar;
    @SerializedName("all")
    private int all;

    public int getOneStar() {
        return oneStar;
    }

    public void setOneStar(int oneStar) {
        this.oneStar = oneStar;
    }

    public int getTwoStar() {
        return twoStar;
    }

    public void setTwoStar(int twoStar) {
        this.twoStar = twoStar;
    }

    public int getThereStar() {
        return thereStar;
    }

    public void setThereStar(int thereStar) {
        this.thereStar = thereStar;
    }

    public int getFourStar() {
        return fourStar;
    }

    public void setFourStar(int fourStar) {
        this.fourStar = fourStar;
    }

    public int getFiveStar() {
        return fiveStar;
    }

    public void setFiveStar(int fiveStar) {
        this.fiveStar = fiveStar;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
