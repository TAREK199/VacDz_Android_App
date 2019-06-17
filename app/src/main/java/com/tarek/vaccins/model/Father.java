package com.tarek.vaccins.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Father {

    @SerializedName("document")
    @Expose
    private String document;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("prenom")
    @Expose
    private String prenom;
    @SerializedName("tel1")
    @Expose
    private String tel1;

    @SerializedName("tel2")
    @Expose
    private String tel2;

    @SerializedName("adresse")
    @Expose
    private String wilaya;

    @SerializedName("commune_id")
    @Expose
    private String communeId;


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;


    public Father(String document, Integer id, String email,String prenom, String password,String tel1,String wilaya, String communeId) {
        this.document = document;
        this.email = email;
        this.password = password;
        this.prenom = prenom;
        this.tel1 = tel1;
        this.wilaya = wilaya;
        this.communeId = communeId;
        this.id = id;
    }

    public Father(String document, String email, String password, String nom, String prenom, String tel1, String wilaya, String communeId, Integer id) {
        this.document = document;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.tel1 = tel1;
        this.wilaya = wilaya;
        this.communeId = communeId;
        this.id = id;
    }


    public Father(String firstNameChar, String lastnameChar, String adresseChar, String phoneNmbr1Char, String phoneNbr2Char, int identityNumberChar) {

    this.nom = firstNameChar ;
    this.prenom = lastnameChar ;
    this.wilaya = adresseChar ;
    this.tel1 = phoneNmbr1Char ;
    this.tel2 = phoneNbr2Char ;
    this.id = identityNumberChar ;
    }


    public Father(String firstNameChar, String lastnameChar, String phoneNmbr1Char, String phoneNbr2Char, String adresseChar, String communeId) {


        this.nom = firstNameChar ;
        this.prenom = lastnameChar ;
        this.wilaya = adresseChar ;
        this.tel1 = phoneNmbr1Char ;
        this.tel2 = phoneNbr2Char ;
        this.communeId = communeId;

    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getCommuneId() {
        return communeId;
    }

    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }

    public Integer getFatherId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}






