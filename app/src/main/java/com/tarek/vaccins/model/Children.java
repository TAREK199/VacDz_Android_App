package com.tarek.vaccins.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Children {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("prenom")
    @Expose
    private String prenom;
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("observation")
    @Expose
    private String observation;
    @SerializedName("pere_id")
    @Expose
    private Integer pereId;
    @SerializedName("date_naissance")
    @Expose
    private String dateNaissance;
    @SerializedName("lieu_naissance")
    @Expose
    private String lieuNaissance;
    @SerializedName("commune_id")
    @Expose
    private Integer communeId;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;


    public Children(String prenom) {
        this.prenom = prenom;
    }

    public Children(Integer id, String nom, String prenom, Integer sex, String observation, Integer pereId, String dateNaissance, String lieuNaissance, Integer communeId, Object createdAt, Object updatedAt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sex = sex;
        this.observation = observation;
        this.pereId = pereId;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.communeId = communeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Integer getPereId() {
        return pereId;
    }

    public void setPereId(Integer pereId) {
        this.pereId = pereId;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Integer getCommuneId() {
        return communeId;
    }

    public void setCommuneId(Integer communeId) {
        this.communeId = communeId;
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
