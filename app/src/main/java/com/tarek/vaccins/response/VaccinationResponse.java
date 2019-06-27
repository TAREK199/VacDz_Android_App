package com.tarek.vaccins.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Vaccin;
import com.tarek.vaccins.model.Vaccination;

import java.util.List;

public class VaccinationResponse {


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

        @SerializedName("vaccins")
        @Expose
        private List<Vaccin> vaccins = null;

        @SerializedName("vaccinations")
        @Expose
        private List<Vaccination> vaccinations = null;


        public List<Vaccin> getVaccins() {
            return vaccins;
        }
        public void setVaccins(List<Vaccin> vaccins) {
            this.vaccins = vaccins;
        }

        public List<Vaccination> getVaccinations() {
            return vaccinations;
        }

        public void setVaccinations(List<Vaccination> vaccinations) {
            this.vaccinations = vaccinations;
        }

    }

}
