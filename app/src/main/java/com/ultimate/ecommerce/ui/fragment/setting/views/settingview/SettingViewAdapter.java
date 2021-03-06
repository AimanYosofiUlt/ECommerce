package com.ultimate.ecommerce.ui.fragment.setting.views.settingview;

import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.ABOUT;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.ADDRESS;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.CONTACT_US;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.FAVORITE;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.HELP;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.LANG_CUR;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.LOGOUT;
import static com.ultimate.ecommerce.ui.fragment.setting.SettingSt.ORDERS;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

public class SettingViewAdapter extends RecyclerView.Adapter<SettingViewHolder> {
    List<SettingModel> list;
    SettingViewListener listener;

    public SettingViewAdapter(Context context, SettingViewListener listener) {
        this.listener = listener;
        list = new ArrayList<>();
    }

    public void addVisitorSettingItems(Context context) {
        list.clear();
        list.add(getSettingModel(context, FAVORITE, R.string.favorate, R.drawable.ic_favourite));
        list.add(getSettingModel(context, LANG_CUR, R.string.lang_cur, R.drawable.ic_language_currency));
        list.add(getSettingModel(context, HELP, R.string.help, R.drawable.ic_help));
        list.add(getSettingModel(context, ABOUT, R.string.about, R.drawable.ic_about));
        list.add(getSettingModel(context, CONTACT_US, R.string.contact_us, R.drawable.ic_contact));
        notifyDataSetChanged();
    }

    public void addUserSettingItems(Context context) {
        list.clear();
        list.add(getSettingModel(context, ORDERS, R.string.orders, R.drawable.ic_orders));
        list.add(getSettingModel(context, ADDRESS, R.string.addresses, R.drawable.ic_address_street));
        list.add(getSettingModel(context, FAVORITE, R.string.favorate, R.drawable.ic_favourite));
        list.add(getSettingModel(context, LANG_CUR, R.string.lang_cur, R.drawable.ic_language_currency));
        list.add(getSettingModel(context, HELP, R.string.help, R.drawable.ic_help));
        list.add(getSettingModel(context, ABOUT, R.string.about, R.drawable.ic_about));
        list.add(getSettingModel(context, CONTACT_US, R.string.contact_us, R.drawable.ic_contact));
        list.add(getSettingModel(context, LOGOUT, R.string.logout, R.drawable.ic_logout));
        notifyDataSetChanged();
    }

    private SettingModel getSettingModel(Context context, int id, int R_string, int R_drawable) {
        String name = context.getString(R_string);
        Drawable drawable = ContextCompat.getDrawable(context, R_drawable);

        SettingModel settingModel = new SettingModel(id, name, drawable);
        if (id == LOGOUT)
            settingModel.setHasNextIcon(false);
        return settingModel;
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_setting, parent, false);
        return new SettingViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}