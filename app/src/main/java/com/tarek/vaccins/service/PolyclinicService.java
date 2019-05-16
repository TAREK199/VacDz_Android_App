package com.tarek.vaccins.service;


import com.tarek.vaccins.model.Children;
import com.tarek.vaccins.polyclinic.Polyclinic;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PolyclinicService {

    @GET("polycliniques")
    Call<Polyclinic> getPolycliniques();
}
