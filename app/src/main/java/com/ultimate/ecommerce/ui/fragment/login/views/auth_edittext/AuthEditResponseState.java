package com.ultimate.ecommerce.ui.fragment.login.views.auth_edittext;

import com.ultimate.ecommerce.repository.server.response.base.ResponseState;

public class AuthEditResponseState extends ResponseState {
    int indexInParent;
    public AuthEditResponseState(int indexInParent, String message) {
        super(message);
        this.indexInParent = indexInParent;
    }

    public static AuthEditResponseState failureState(int indexInParent, String message) {
        return new AuthEditResponseState(indexInParent, message);
    }

    public int getIndexInParent() {
        return indexInParent;
    }
}
