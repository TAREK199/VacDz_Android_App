package com.tarek.vaccins.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VaccinState {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date_vaccination")
    @Expose
    private String dateVaccination;
    @SerializedName("polyclinique_id")
    @Expose
    private Integer polycliniqueId;
    @SerializedName("vaccin_id")
    @Expose
    private Integer vaccinId;
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

    public Integer getPolycliniqueId() {
        return polycliniqueId;
    }

    public void setPolycliniqueId(Integer polycliniqueId) {
        this.polycliniqueId = polycliniqueId;
    }

    public Integer getVaccinId() {
        return vaccinId;
    }

    public void setVaccinId(Integer vaccinId) {
        this.vaccinId = vaccinId;
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
