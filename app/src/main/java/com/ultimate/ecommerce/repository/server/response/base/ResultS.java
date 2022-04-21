package com.ultimate.ecommerce.repository.server.response.base;

public class ResultS {
    String status;
    String msg;

    public ResultS() {
    }

    public ResultS(String status, String msg) {
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
