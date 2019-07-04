package com.tarek.vaccins.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Programme;

import java.util.List;

public class ProgrammeResponse {


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("plan")
        @Expose
        private List<Programme> plan = null;

        public List<Programme> getPlan() {
            return plan;
        }

        public void setPlan(List<Programme> plan) {
            this.plan = plan;
        }

    }

}