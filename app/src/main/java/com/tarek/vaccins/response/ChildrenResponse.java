package com.tarek.vaccins.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Children;

import java.util.List;

public class ChildrenResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getSuccess() {
        return success;
    }

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("enfants")
        @Expose
        private List<Children> enfants = null;

        public List<Children> getEnfants() {
            return enfants;
        }
        public void setEnfants(List<Children> enfants) {
            this.enfants = enfants;
        }

    }
}
