package com.ultimate.ecommerce.repository.server.response.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.yemensoft.yslibrary.Annotations.ErrorMessageMethod;
import com.yemensoft.yslibrary.Annotations.ErrorNoMethod;

import java.io.Serializable;

public class GenResponseObject<T> implements Serializable {
    @NonNull
    public Status status;

    @SerializedName("Data")
    T data;

    @SerializedName(value = "_Result", alternate = {"Result"})
    public Result result;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @ErrorNoMethod
    public int getErrorNumber() {


        return result.getErrorNumber();
    }


    @ErrorMessageMethod
    public String getErrorMessage() {

        return result.getErrorMessage();

    }

    private GenResponseObject(@NonNull Status status, @Nullable T data, @Nullable Result message) {
        this.status = status;
        this.data = data;
        this.result = message;
    }


    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public GenResponseObject() {
    }

    public static <T> GenResponseObject<T> success(@NonNull T data) {
        return new GenResponseObject<>(Status.SUCCESS, data, null);
    }

    public static <T> GenResponseObject<T> success(@NonNull T data, Result result) {
        return new GenResponseObject<>(Status.SUCCESS, data, result);
    }

    public static <T> GenResponseObject<T> error(Result msg, @Nullable T data) {
        return new GenResponseObject<>(Status.ERROR, data, msg);
    }

    public enum Status {SUCCESS, ERROR}
}


