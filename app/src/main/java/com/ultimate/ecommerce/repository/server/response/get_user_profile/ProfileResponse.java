package com.ultimate.ecommerce.repository.server.response.get_user_profile;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.add_user.UserData;
import com.ultimate.ecommerce.repository.server.response.add_user.UserResponse;

public class ProfileResponse {
    @SerializedName("user_id")
    private UserResponse userData;

    public UserResponse getUserData() {
        return userData;
    }

    public void setUserData(UserResponse userData) {
        this.userData = userData;
    }
}
