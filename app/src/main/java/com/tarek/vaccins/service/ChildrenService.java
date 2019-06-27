package com.tarek.vaccins.service;


import com.tarek.vaccins.model.Children;
import com.tarek.vaccins.response.CalendarResponse;
import com.tarek.vaccins.response.ChildrenProfileResponse;
import com.tarek.vaccins.response.ChildrenResponse;
import com.tarek.vaccins.response.VaccinationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ChildrenService {


    @Headers("Accept: application/json")
    @GET("enfant/{id}/vaccination")
    Call<Children> getVaccination(@Header("Authorization") String token, @Path("id") int id);


    @Headers("Accept: application/json")
    @GET("details/{id}")
    Call<ChildrenProfileResponse> getChildrenEnfant(@Header("Authorization") String token, @Path("id") int id);


    @Headers("Accept: application/json")
    @GET("enfants/{id}/calendrier")
    Call<CalendarResponse> getCalendar(@Header("Authorization") String token, @Path("id") int id);

    @Headers("Accept: application/json")
    @GET("enfants/{id}/vaccinations/{age}")
    Call<VaccinationResponse> getChldrenVaccination(@Header("Authorization") String token, @Path("id") int id, @Path("age") int age);


    @Headers("Accept: application/json")
    @GET("enfants/{id}/vaccinations/{age}")
    Call<VaccinationResponse> getChildrenVaccinations(@Header("Authorization") String token, @Path("id") int id, @Path("age") int age);

}
