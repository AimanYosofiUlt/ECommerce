package com.ultimate.ecommerce.ui.fragment.help.views.helpview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewHelpBinding;
import com.ultimate.ecommerce.repository.server.response.help.QuesData;

public class HelpViewHolder extends RecyclerView.ViewHolder {
    QuesData data;
    HelpViewListener listener;
    ViewHelpBinding bd;


    public HelpViewHolder(@NonNull View itemView, HelpViewListener listener) {
        super(itemView);
        bd = ViewHelpBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(QuesData data) {
        this.data = data;
        bd.title.setText(data.getQueTitle());
        bd.description.setText(data.getQueDes());
    }

    private void initEvent() {

    }
}