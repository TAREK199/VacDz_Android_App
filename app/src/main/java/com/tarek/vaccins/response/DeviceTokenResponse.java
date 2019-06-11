package com.tarek.vaccins.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.DataFromDeviceToken;

public class DeviceTokenResponse {


    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private DataFromDeviceToken data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataFromDeviceToken getData() {
        return data;
    }

    public void setData(DataFromDeviceToken data) {
        this.data = data;
    }
}
