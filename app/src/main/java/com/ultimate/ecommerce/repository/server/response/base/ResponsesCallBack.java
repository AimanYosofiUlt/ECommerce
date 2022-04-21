package com.ultimate.ecommerce.repository.server.response.base;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponsesCallBack<T extends ResponseObject> implements Callback<T> {
    public abstract void onSuccess(T response);

    public abstract void onFailure(String state,String msg);

    @Override
    public final void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            ResponseObject responseObject = response.body();
            if (responseObject == null) {
                onFailure("0","null response from the api");
            } else {
                if (responseObject.getStatus() != null) {
                    if (responseObject.getStatus().equals("1")) {
                        onSuccess(response.body());
                    } else {
                        onFailure("0",responseObject.getMsg());
                    }
                } else {
                    onFailure("0","null response from the api");
                }
            }
        } else {
            onFailure("0",response.message());
        }
    }

    @Override
    public  final void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        onFailure("0",t.getMessage());
    }
}