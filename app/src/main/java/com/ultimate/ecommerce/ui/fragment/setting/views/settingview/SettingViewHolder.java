package com.ultimate.ecommerce.ui.fragment.setting.views.settingview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewSettingBinding;

public class SettingViewHolder extends RecyclerView.ViewHolder {
    ViewSettingBinding bd;
    SettingViewListener listener;
    SettingModel settingModel;

    public SettingViewHolder(@NonNull View itemView, SettingViewListener listener) {
        super(itemView);
        bd = ViewSettingBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(SettingModel settingModel) {
        this.settingModel = settingModel;
        bd.img.setImageDrawable(settingModel.drawable);
        bd.titleTV.setText(settingModel.title);
        if (settingModel.hasNextIcon)
            bd.nextImg.setVisibility(View.VISIBLE);
        else
            bd.nextImg.setVisibility(View.GONE);
    }

    private void initEvent() {
        bd.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOpenReq(settingModel.getId());
            }
        });
    }
}
