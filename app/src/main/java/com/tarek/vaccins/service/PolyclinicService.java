package com.tarek.vaccins.service;


import com.tarek.vaccins.model.Polyclinic;
import com.tarek.vaccins.response.PolyclinicResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PolyclinicService {

    @Headers("Accept: application/json")
    @GET("polycliniques")
 //   Call<List<Polyclinic>> getPolycliniques(@Header("Authorization") String token, @Query("commune") int commune_id);
 //   Call<Polyclinic> getPolycliniques(@Header("Authorization") String token,@Body int commune_id);

    Call<List<Polyclinic>> getPolycliniques(@Header("Authorization") String token, @Query("commune") String nom);


    @Headers("Accept: application/json")
    @GET("polycliniques/{id}")

    Call<PolyclinicResponse> getPolycliniqueInfo(@Header("Authorization") String token, @Path("id") int id);

}
