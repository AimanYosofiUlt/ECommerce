package com.ultimate.ecommerce.repository.server.response.add_user;

import com.google.gson.annotations.SerializedName;

public class Allcaps {
    @SerializedName("subscriber")
    private boolean subscriber;
    @SerializedName("level_0")
    private boolean level0;
    @SerializedName("read")
    private boolean read;

    public boolean getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }

    public boolean getLevel0() {
        return level0;
    }

    public void setLevel0(boolean level0) {
        this.level0 = level0;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
