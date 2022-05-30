package com.ultimate.ecommerce.ui.fragment.order_inner.views.order;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ViewOrderBinding;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Products;

import java.util.List;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    Order data;
    OrderViewListener listener;
    ViewOrderBinding binding;


    public OrderViewHolder(@NonNull View itemView, OrderViewListener listener) {
        super(itemView);
        binding = ViewOrderBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(Order data) {
        this.data = data;
        initProducts(data.getProducts());
        binding.totalPriceTV.setText(data.getOrderTotal());
    }

    private void initProducts(List<Products> products) {
        setProductByIndex(0, binding.firstImage, binding.firstNameTV, products);
        setProductByIndex(1, binding.secoundImage, binding.secoundNameTV, products);
        setProductByIndex(2, binding.thirdImage, binding.thirdNameTV, products);

        if (products.size() > 3) {
            binding.moreImageTV.setVisibility(View.VISIBLE);
            binding.moreTV.setVisibility(View.VISIBLE);
            String anotherCount = String.valueOf(products.size() - 3);
            String moreMessage = "+" + anotherCount + " " + binding.getRoot().getContext().getString(R.string.other_product);
            binding.moreImageTV.setText(moreMessage);
            binding.moreTV.setText(moreMessage);
        } else {
            binding.moreImageTV.setVisibility(View.GONE);
            binding.moreTV.setVisibility(View.GONE);
        }
    }

    private void setProductByIndex(int index, ImageView image, TextView textView, List<Products> products) {
        if (products.size() > index) {
            image.setVisibility(View.VISIBLE);
            Glide.with(binding.getRoot().getContext())
                    .load(products.get(0).getImage())
                    .into(image);
            textView.setText(products.get(index).getTitle());
        } else {
            textView.setVisibility(View.GONE);
            image.setVisibility(View.GONE);
        }
    }

    private void initEvent() {
        binding.cardBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(data);
            }
        });
    }
}