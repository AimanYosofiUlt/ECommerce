package com.ultimate.ecommerce.ui.fragment.product_list.bottomsheets.filter;

public class Filter {
    public static final int MAX = 10000;
    public static final int MIN = 0;
    int minimum = MIN;
    int maximum = MAX;

    public Filter() {
    }

    public Filter(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
