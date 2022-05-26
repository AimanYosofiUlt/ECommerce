package com.ultimate.ecommerce.repository.server.response.get_user_orders;

import com.google.gson.annotations.SerializedName;

public class OrderDate {
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("timezone_type")
    private int timezoneType;
    @SerializedName("date")
    private String date;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTimezoneType() {
        return timezoneType;
    }

    public void setTimezoneType(int timezoneType) {
        this.timezoneType = timezoneType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
