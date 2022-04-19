package com.ultimate.ecommerce.ui.fragment.setting.views.settingview;

import android.graphics.drawable.Drawable;

public class SettingModel {
    int id;
    String title;
    Drawable drawable;
    boolean hasNextIcon = true;

    public SettingModel(int id, String name, Drawable drawable) {
        this.id = id;
        this.title = name;
        this.drawable = drawable;
    }

    public void setHasNextIcon(boolean hasNextIcon) {
        this.hasNextIcon = hasNextIcon;
    }

    public int getId() {
        return id;
    }
}
