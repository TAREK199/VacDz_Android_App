package com.tarek.vaccins.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Plan;
import com.tarek.vaccins.model.Polyclinic;

import java.util.List;

public class PolyclinicResponse {

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

        @SerializedName("polyclinique")
        @Expose
        private Polyclinic polyclinique;

        @SerializedName("plan")
        @Expose
        private List<Plan> plan ;


        public Polyclinic getPolyclinique() {
            return polyclinique;
        }

        public void setPolyclinique(Polyclinic polyclinique) {
            this.polyclinique = polyclinique;
        }

        public List<Plan> getPlan() {
            return plan;
        }

        public void setPlan(List<Plan> plan) {
            this.plan = plan;
        }
    }


    }
