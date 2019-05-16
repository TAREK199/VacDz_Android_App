package com.tarek.vaccins.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Children {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nom")
    @Expose
    private String firstName ;

    @SerializedName("prenom")
    @Expose
    private String lastName;

    @SerializedName("sex")
    @Expose
    private int sex ;

    @SerializedName("observation")
    @Expose
    private String observation;

    @SerializedName("pere_id")
    @Expose
    private int pere_id;

    @SerializedName("date_naissance")
    @Expose
    private String birthday;

    @SerializedName("lieu_naissance")
    @Expose
    private String birthplace ;

    @SerializedName("commune_id")
    @Expose
    private int communeId ;


    public Children(String lastName) {
        this.lastName = lastName;
    }

    public Children(int id, String firstName, String lastName, int sex, String observation, int pere_id, String birthday, String birthplace, int communeId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.observation = observation;
        this.pere_id = pere_id;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.communeId = communeId;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSex() {
        return sex;
    }

    public String getObservation() {
        return observation;
    }

    public int getPere_id() {
        return pere_id;
    }
    public String getBirthday() {
        return birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public int getCommuneId() {
        return communeId;
    }
}
