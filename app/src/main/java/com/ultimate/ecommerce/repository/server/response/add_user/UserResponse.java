package com.ultimate.ecommerce.repository.server.response.add_user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("allcaps")
    private Allcaps allcaps;
    @SerializedName("roles")
    private List<String> roles;
    @SerializedName("cap_key")
    private String capKey;
    @SerializedName("caps")
    private Caps caps;
    @SerializedName("ID")
    private int id;
    @SerializedName("data")
    private UserData data;

    public Allcaps getAllcaps() {
        return allcaps;
    }

    public void setAllcaps(Allcaps allcaps) {
        this.allcaps = allcaps;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getCapKey() {
        return capKey;
    }

    public void setCapKey(String capKey) {
        this.capKey = capKey;
    }

    public Caps getCaps() {
        return caps;
    }

    public void setCaps(Caps caps) {
        this.caps = caps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserData getUserData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
