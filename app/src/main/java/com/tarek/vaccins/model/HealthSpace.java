package com.tarek.vaccins.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HealthSpace  {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("titre")
    @Expose
    private String titre;
    @SerializedName("txt")
    @Expose
    private String txt;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public HealthSpace(String titre, String txt) {
        this.titre = titre;
        this.txt = txt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
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
/*
    private String title ;
    private int image ;

    public HealthSpace(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
    */

}
