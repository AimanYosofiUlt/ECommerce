package com.ultimate.ecommerce.repository.server.response.get_order;

import com.google.gson.annotations.SerializedName;

public class Cost {
    @SerializedName("totalAfterDiscount")
    private double totalafterdiscount;
    @SerializedName("total")
    private double total;
    @SerializedName("discountTotal")
    private double discounttotal;
    @SerializedName("vat")
    private double vat;

    public double getTotalafterdiscount() {
        return totalafterdiscount;
    }

    public void setTotalafterdiscount(double totalafterdiscount) {
        this.totalafterdiscount = totalafterdiscount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscountTotal() {
        return discounttotal;
    }

    public void setDiscounttotal(double discounttotal) {
        this.discounttotal = discounttotal;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }
}
