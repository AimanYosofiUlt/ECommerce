package com.ultimate.ecommerce.ui.fragment.product_list.views.product;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ViewProductBinding;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ProductData data;
    ProductViewListener listener;
    ViewProductBinding bd;


    public ProductViewHolder(@NonNull View itemView, ProductViewListener listener) {
        super(itemView);
        bd = ViewProductBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(ProductData data) {
        this.data = data;

        bd.productNameTV.setText(data.getTitle());

        Glide.with(itemView.getContext())
                .load(data.getImageUrl())
                .error(R.drawable.ic_baseline_error_24)
                .into(bd.productImage);

        bd.priceTV.setText(data.getPrice());

        
//        //todo here should calculate the discount and show the oldPrice or the price but the response don't give a percentage
//        String discountMsg = itemView.getContext().getString(R.string.discountBy) + " " + data.getDiscountPercentage() + "%";
//        bd.discountPercentageTV.setText(discountMsg);
//        int discountPrice = Integer.parseInt(data.getPrice()) * Integer.parseInt(data.getDiscountPercentage()) / 100;
//        bd.priceTV.setText(String.valueOf(discountPrice));
//        bd.oldPriceTV.setText(data.getPrice());
//
    }

    private void initEvent() {

    }
}