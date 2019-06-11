package com.tarek.vaccins.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tarek.vaccins.model.Father;
import com.tarek.vaccins.model.User;

public class LoginResponse {


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

        @SerializedName("token")
        @Expose
        private String token;

        @SerializedName("pere")
        @Expose
        private Father pere;

        @SerializedName("user")
        @Expose
        private User user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Father getPere() {
            return pere;
        }

        public void setPere(Father pere) {
            this.pere = pere;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }



}
