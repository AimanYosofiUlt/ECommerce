package com.ultimate.ecommerce.ui.fragment.address.views.addressview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewAddressBinding;

public class AddressViewHolder extends RecyclerView.ViewHolder {
    String data;
    AddressViewListener listener;
    ViewAddressBinding bd;


    public AddressViewHolder(@NonNull View itemView, AddressViewListener listener) {
        super(itemView);
        bd = ViewAddressBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(String data) {
        this.data = data;

    }

    private void initEvent() {

    }
}