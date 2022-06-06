package com.ultimate.ecommerce.repository.server.response.get_user_profile;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.add_user.UserResponse;

public class GetUserProfileData {
    @SerializedName("user_id")
    UserResponse userResponse;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
