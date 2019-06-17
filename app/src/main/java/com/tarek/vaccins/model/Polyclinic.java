package com.tarek.vaccins.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polyclinic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String polyName;
    @SerializedName("adresse")
    @Expose
    private String polyAdress;
    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("epsp_id")
    @Expose
    private Integer epspId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longtitude")
    @Expose
    private String longtitude;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;


    public Polyclinic(String polyName) {
        this.polyName = polyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolyName() {
        return polyName;
    }

    public void setNom(String nom) {
        this.polyName = nom;
    }

    public String getPolyAdress() {
        return polyAdress;
    }

    public void setPolyAdress(String polyAdress) {
        this.polyAdress = polyAdress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getEpspId() {
        return epspId;
    }

    public void setEpspId(Integer epspId) {
        this.epspId = epspId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
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
