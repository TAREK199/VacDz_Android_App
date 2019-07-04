package com.tarek.vaccins.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Programme {


    @SerializedName("jour")
    @Expose
    private Integer jour;
    @SerializedName("value")
    @Expose
    private List<String> value = null;

    public Integer getJour() {
        return jour;
    }

    public void setJour(Integer jour) {
        this.jour = jour;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
