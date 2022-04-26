package com.ultimate.ecommerce.ui.fragment.contact_us.views.contact_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.contact_us.ContactUsContentData;

import java.util.ArrayList;
import java.util.List;

public class ContactViewAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    List<ContactUsContentData> list;

    public ContactViewAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contactus, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ContactUsContentData> contents) {
        list = contents;
        notifyDataSetChanged();
    }
}
