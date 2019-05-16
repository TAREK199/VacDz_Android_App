package com.tarek.vaccins.model;



public class Programme {


    private String jour ,acte;

    public Programme(String jour, String acte) {
        this.jour = jour;
        this.acte = acte;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getActe() {
        return acte;
    }

    public void setActe(String acte) {
        this.acte = acte;
    }


}
