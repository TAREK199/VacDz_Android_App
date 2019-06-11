package com.tarek.vaccins.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vaccination {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date_vaccination")
    @Expose
    private String dateVaccination;
    @SerializedName("polyclinique_id")
    @Expose
    private String polycliniqueId;
    @SerializedName("vaccin")
    @Expose
    private String vaccin;
    @SerializedName("enfant_id")
    @Expose
    private Integer enfantId;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateVaccination() {
        return dateVaccination;
    }

    public void setDateVaccination(String dateVaccination) {
        this.dateVaccination = dateVaccination;
    }

    public String getPolycliniqueId() {
        return polycliniqueId;
    }

    public void setPolycliniqueId(String polycliniqueId) {
        this.polycliniqueId = polycliniqueId;
    }

    public String getVaccin() {
        return vaccin;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public Integer getEnfantId() {
        return enfantId;
    }

    public void setEnfantId(Integer enfantId) {
        this.enfantId = enfantId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }
}
