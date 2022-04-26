package com.ultimate.ecommerce.ui.fragment.contact_us.views.contact_view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.ViewContactusBinding;
import com.ultimate.ecommerce.repository.server.response.contact_us.ContactUsContentData;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    ViewContactusBinding bd;
    ContactUsContentData content;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        bd = ViewContactusBinding.bind(itemView);
    }

    public void bind(ContactUsContentData content) {
        this.content = content;
        bd.title.setText(content.getTitle());
        bd.description.setText(content.getDes());

        Glide.with(itemView.getContext())
                .load(content.getImg())
                .error(R.drawable.ic_baseline_error_24)
                .into(bd.img);
        bd.img.setColorFilter(DynamicTheme.gradientStartColor);
    }
}
