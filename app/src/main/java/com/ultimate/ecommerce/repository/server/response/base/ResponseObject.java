package com.ultimate.ecommerce.repository.server.response.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseObject<Data> implements Serializable {
    @SerializedName("status")
    String status;
    @SerializedName("msg")
    String msg;
    @SerializedName("data")
    Data data;

    public ResponseObject() {
    }

    public ResponseObject(String status, String msg, Data data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
