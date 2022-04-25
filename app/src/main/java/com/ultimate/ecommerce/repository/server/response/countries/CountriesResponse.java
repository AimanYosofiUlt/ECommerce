package com.ultimate.ecommerce.repository.server.response.countries;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class CountriesResponse {
    @SerializedName("status")
    String status;
    @SerializedName("msg")
    String msg;
    @SerializedName("countries")
    private List<Countries> countries;

    public CountriesResponse() {
    }

    public List<Countries> getCountries() {
        return countries;
    }

    public void setCountries(List<Countries> countries) {
        this.countries = countries;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}