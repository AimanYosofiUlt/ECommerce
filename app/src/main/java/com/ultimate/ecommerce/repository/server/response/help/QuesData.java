package com.ultimate.ecommerce.repository.server.response.help;

import com.google.gson.annotations.SerializedName;

public class QuesData {
    @SerializedName("queDes")
    private String queDes;
    @SerializedName("queTitle")
    private String queTitle;

    public QuesData() {
    }

    public String getQueDes() {
        return queDes;
    }

    public void setQueDes(String queDes) {
        this.queDes = queDes;
    }

    public String getQueTitle() {
        return queTitle;
    }

    public void setQueTitle(String queTitle) {
        this.queTitle = queTitle;
    }
}
