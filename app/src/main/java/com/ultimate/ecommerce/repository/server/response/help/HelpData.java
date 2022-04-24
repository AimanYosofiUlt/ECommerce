package com.ultimate.ecommerce.repository.server.response.help;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HelpData {
    @SerializedName("ques")
    private List<QuesData> ques;
    @SerializedName("title")
    private String title;

    public HelpData() {
    }

    public List<QuesData> getQues() {
        return ques;
    }

    public void setQues(List<QuesData> ques) {
        this.ques = ques;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
