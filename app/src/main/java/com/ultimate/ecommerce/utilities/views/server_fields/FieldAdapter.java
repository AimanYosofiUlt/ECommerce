package com.ultimate.ecommerce.utilities.views.server_fields;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ultimate.ecommerce.utilities.views.server_fields.base.BaseFieldView;

import java.util.ArrayList;

public class FieldAdapter extends ArrayAdapter<BaseFieldView> {

    public FieldAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<BaseFieldView>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getItem(position).getRootView();
    }
}
