package com.tarek.vaccins.model;



public class CalendarVaccination {

    private String VaccinationTime,VaciinationDate;
    private int vaccinationState ;


    public CalendarVaccination(String vaccinationTime, String vaciinationDate, int vaccinationState) {
        VaccinationTime = vaccinationTime;
        VaciinationDate = vaciinationDate;
        this.vaccinationState = vaccinationState;
    }

    public String getVaccinationTime() {
        return VaccinationTime;
    }


    public String getVaciinationDate() {
        return VaciinationDate;
    }

    public int getVaccinationState() {
        return vaccinationState;
    }

}
