package com.tarek.vaccins.model;



public class RDV {

    private String rdvDate,childName,acte,polyName,polyAdr;


    public RDV(String rdvDate, String childName, String acte, String polyName, String polyAdr) {
        this.rdvDate = rdvDate;
        this.childName = childName;
        this.acte = acte;
        this.polyName = polyName;
        this.polyAdr = polyAdr;
    }

    public String getRdvDate() {
        return rdvDate;
    }

    public String getChildName() {
        return childName;
    }

    public String getActe() {
        return acte;
    }

    public String getPolyName() {
        return polyName;
    }

    public String getPolyAdr() {
        return polyAdr;
    }
}
