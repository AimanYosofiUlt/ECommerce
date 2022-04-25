package com.ultimate.ecommerce.repository.server.response.add_user;

import com.google.gson.annotations.SerializedName;

public class Caps {
    @SerializedName("subscriber")
    private boolean subscriber;

    public Caps() {
    }

    public boolean getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }
}
