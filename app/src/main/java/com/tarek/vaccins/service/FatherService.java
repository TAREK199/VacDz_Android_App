package com.tarek.vaccins.service;


import com.tarek.vaccins.model.Rdv;
import com.tarek.vaccins.model.UserLogin;
import com.tarek.vaccins.response.ChildrenResponse;
import com.tarek.vaccins.model.Father;
import com.tarek.vaccins.response.CommuneResponse;
import com.tarek.vaccins.response.DeviceTokenResponse;
import com.tarek.vaccins.response.FatherProfileResponse;
import com.tarek.vaccins.response.FatherResponse;
import com.tarek.vaccins.response.LoginResponse;
import com.tarek.vaccins.response.RdvAddResponse;
import com.tarek.vaccins.response.RdvResponse;
import com.tarek.vaccins.response.RdvsChildrenResponse;
import com.tarek.vaccins.response.UserResponse;
import com.tarek.vaccins.response.WilayaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FatherService {

    @POST("register")
    Call<FatherResponse> register(@Body Father father);

    @POST("login")
    Call<LoginResponse> fatherLogin(@Body UserLogin userLogin);


    @Headers("Accept: application/json")
    @PUT("user/{id}")
    Call<FatherProfileResponse> editFatherProfile(@Header("Authorization") String token,@Path("id") int id,@Body Father father);

    @Headers("Accept: application/json")
    @GET("pere/{id}")
    Call<ChildrenResponse> getChildrensById(@Header("Authorization") String token, @Path("id") int id);

    @Headers("Accept: application/json")
    @GET("details")
    Call<FatherProfileResponse> getFatherProfile(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @POST("subscribe")
    Call<DeviceTokenResponse> storeFireBaseToken(@Header("Authorization") String token, @Query("device_token") String fireBaseToken);


    @POST("rdvs")
    Call<RdvAddResponse> prenRdv(@Header("Authorization") String token, @Body Rdv rdv);


    @Headers("Accept: application/json")
    @GET("rdvs")
    Call<RdvResponse> getRdvs(@Header("Authorization") String token, @Query("pere") int id);



    @Headers("Accept: application/json")
    @GET("rdvs/enfant/{id}")
    Call<RdvsChildrenResponse> getChildrenRdvs(@Header("Authorization") String token, @Path("id") int id);

    @Headers("Accept: application/json")
    @GET("wilayas")
    Call<WilayaResponse> getWilayas(@Header("Authorization") String token);


    @Headers("Accept: application/json")
    @GET("communes")
    Call<CommuneResponse> getCommunes(@Header("Authorization") String token, @Query("id") int id);


    @Headers("Accept: application/json")
    @GET("wilayasregister")
    Call<WilayaResponse> getWilayasRegister();


    @Headers("Accept: application/json")
    @GET("communesregister")
    Call<CommuneResponse> getCommunesRegister(@Query("id") int id);



    @Headers({"Accept: application/json",})
    @PUT("user/{user_id}/reset_email")
    Call<UserResponse> updateEmail(@Header("Authorization") String token,@Path("user_id") int id ,@Query("email") String  email);

    @Headers({"Accept: application/json",})
    @PUT("user/{user_id}/reset")
    Call<UserResponse> updatePassword(@Header("Authorization") String token,@Path("user_id") int id ,@Query("password") String  password);

}
