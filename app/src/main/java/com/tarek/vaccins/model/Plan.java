package com.tarek.vaccins.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plan {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jour")
    @Expose
    private Integer jour;
    @SerializedName("vaccin")
    @Expose
    private String vaccin;

    @SerializedName("polyclinique")
    @Expose
    private String polyclinique;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJour() {
        return jour;
    }

    public void setJour(Integer jour) {
        this.jour = jour;
    }

    public String getVaccin() {
        return vaccin;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public String getPolyclinique() {
        return polyclinique;
    }

    public void setPolyclinique(String polyclinique) {
        this.polyclinique = polyclinique;
    }
}
