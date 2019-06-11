package com.tarek.vaccins.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Children;

public class ChildrenProfileResponse  {



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

        @SerializedName("enfant")
        @Expose
        private Children enfant;

        public Children getEnfant() {
            return enfant;
        }

        public void setEnfant(Children enfant) {
            this.enfant = enfant;
        }

    }


}
