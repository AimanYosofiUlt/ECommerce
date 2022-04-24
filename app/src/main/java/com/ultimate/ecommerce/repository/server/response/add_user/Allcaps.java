package com.ultimate.ecommerce.repository.server.response.add_user;

import com.google.gson.annotations.SerializedName;

public class Allcaps {
    @SerializedName("subscriber")
    private boolean subscriber;
    @SerializedName("level_0")
    private boolean level_0;
    @SerializedName("read")
    private boolean read;

    public Allcaps() {
    }

    public boolean getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }

    public boolean getLevel_0() {
        return level_0;
    }

    public void setLevel_0(boolean level_0) {
        this.level_0 = level_0;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
