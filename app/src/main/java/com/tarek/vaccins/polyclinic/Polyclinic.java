package com.tarek.vaccins.polyclinic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polyclinic {

    @SerializedName("nom")
    @Expose
    private String polyName ;
    @SerializedName("cite")
    @Expose
    private String polyAdress ;

    public Polyclinic(String polyName, String polyAdress) {
        this.polyName = polyName;
        this.polyAdress = polyAdress;
    }

    public String getPolyName() {
        return polyName;
    }

    public String getPolyAdress() {
        return polyAdress;
    }


}
