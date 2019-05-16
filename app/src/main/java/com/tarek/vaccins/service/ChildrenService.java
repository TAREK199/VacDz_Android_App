package com.tarek.vaccins.service;


import com.tarek.vaccins.model.Children;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChildrenService {

    @GET("enfant/{id}")
    Call<Children> getChildrensById(@Path("id") int id);
}
