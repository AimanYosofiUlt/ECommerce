package com.ultimate.ecommerce.repository.server.response.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseObject implements Serializable {
    @SerializedName("status")
    String status;
    @SerializedName("msg")
    String msg;

    public ResponseObject() {
    }

    public ResponseObject(String status, String msg) {
        this.status = status;
        this.msg = msg;
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