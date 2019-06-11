package com.tarek.vaccins.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Father;
import com.tarek.vaccins.model.User;

import java.util.List;

public class FatherResponse {


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private FatherData fatherData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public FatherData getData() {
        return fatherData;
    }

    public void setData(FatherData fatherData) {
        this.fatherData = fatherData;
    }

    public class FatherData {

        @SerializedName("token")
        @Expose
        private String token;

        @SerializedName("user")
        @Expose
        private User user;

        @SerializedName("pere")
        @Expose
        private Father father;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Father getFather() {
            return father;
        }

        public void setFather(Father father) {
            this.father = father;
        }
    }
}
