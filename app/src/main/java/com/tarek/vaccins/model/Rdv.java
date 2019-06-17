package com.tarek.vaccins.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rdv {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date_rdv")
    @Expose
    private String dateRdv;
    @SerializedName("enfant")
    @Expose
    private String enfant;
    @SerializedName("polyclinique")
    @Expose
    private String polyclinique;

    @SerializedName("enfant_id")
    @Expose
    private Integer enfantId;

    @SerializedName("polyclinique_id")
    @Expose
    private Integer polycliniqueId;


    public Rdv(int enfantId, int polycliniqueId, String rdvDate) {
        this.enfantId = enfantId;
        this.polycliniqueId = polycliniqueId;
        this.dateRdv = rdvDate;
    }

    public Rdv(String rdvDate, String child, String poly) {

        this.dateRdv = rdvDate ;
        this.enfant = child ;
        this.polyclinique = poly ;
    }

    public Rdv(Integer id, String dateRdv, String enfant,String poly ) {

        this.id = id;
        this.dateRdv  =dateRdv ;
        this.enfant = enfant;
        this.polyclinique = poly ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(String dateRdv) {
        this.dateRdv = dateRdv;
    }

    public String getEnfant() {
        return enfant;
    }

    public void setEnfant(String enfant) {
        this.enfant = enfant;
    }

    public String getPolyclinique() {
        return polyclinique;
    }

    public void setPolyclinique(String polyclinique) {
        this.polyclinique = polyclinique;
    }

}
