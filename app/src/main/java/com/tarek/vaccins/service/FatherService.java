package com.tarek.vaccins.service;


import com.tarek.vaccins.model.Children;
import com.tarek.vaccins.model.Father;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FatherService {


    @POST("register")
    Call<String> register(@Body Father father);

    @GET("peres/{id}")
    Call<List<Children>> getChildrensById(@Path("id") int id);

    @GET("peres/{id}")
    Call<String> getChildrensFatherId(@Path("id") int id);
}
