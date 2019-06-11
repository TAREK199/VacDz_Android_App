package com.tarek.vaccins.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vaccin {

    @SerializedName("vaccin")
    @Expose
    private String vaccin;
    @SerializedName("value")
    @Expose
    private Integer value;

    public String getVaccin() {
        return vaccin;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }



}

