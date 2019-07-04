package com.tarek.vaccins.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.HealthSpace;

import java.util.List;

public class HealthSpaceResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<HealthSpace> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<HealthSpace> getData() {
        return data;
    }

    public void setData(List<HealthSpace> data) {
        this.data = data;
    }
}