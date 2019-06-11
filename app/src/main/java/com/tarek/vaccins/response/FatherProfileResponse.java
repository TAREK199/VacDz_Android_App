package com.tarek.vaccins.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Father;
import com.tarek.vaccins.model.User;

public class FatherProfileResponse {


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

        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("pere")
        @Expose
        private Father pere;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Father getPere() {
            return pere;
        }

        public void setPere(Father pere) {
            this.pere = pere;
        }

    }
}
