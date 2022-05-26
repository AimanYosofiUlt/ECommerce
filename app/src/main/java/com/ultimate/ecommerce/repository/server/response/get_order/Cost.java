package com.ultimate.ecommerce.repository.server.response.get_order;

import com.google.gson.annotations.SerializedName;

public class Cost {
    @SerializedName("totalAfterDiscount")
    private double totalafterdiscount;
    @SerializedName("total")
    private double total;
    @SerializedName("discountTotal")
    private int discounttotal;
    @SerializedName("vat")
    private int vat;

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

    public int getDiscounttotal() {
        return discounttotal;
    }

    public void setDiscounttotal(int discounttotal) {
        this.discounttotal = discounttotal;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }
}
