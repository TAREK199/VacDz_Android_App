package com.tarek.vaccins.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Father {

    @SerializedName("id")
    @Expose
    private int fatherId ;
/*
    @SerializedName("id")
    @Expose
    private String firstName ;
*/

    @SerializedName("prenom")
    @Expose
    private String lastName ;

    @SerializedName("email")
    @Expose
    private String email ;

    @SerializedName("password")
    @Expose
    private String password ;

    @SerializedName("document")
    @Expose
    private int documentType ;

    @SerializedName("tel1")
    @Expose
    private String phone_1 ;

    @SerializedName("adresse")
    @Expose
    private String wilaya ;

    @SerializedName("commune_id")
    @Expose
    private int commune ;

    @SerializedName("success")
    @Expose
    private String message;


    public Father(int fatherId,  String lastName, String email,String password, int documentType, String phone_1, String wilaya, int commune) {
        this.fatherId = fatherId;
     //   this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.documentType = documentType;
        this.phone_1 = phone_1;
        this.wilaya = wilaya;
        this.commune = commune;
    }


    public int getFatherId() {
        return fatherId;
    }
/*
    public String getFirstName() {
        return firstName;
    }
*/

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getDocumentType() {
        return documentType;
    }

    public String getPhone_1() {
        return phone_1;
    }

    public String getWilaya() {
        return wilaya;
    }

    public int getCommune() {
        return commune;
    }

    public String getMessage() {
        return message;
    }
}
