package com.tarek.vaccins.service;


import com.tarek.vaccins.model.DataFromDeviceToken;
import com.tarek.vaccins.model.UserLogin;
import com.tarek.vaccins.response.ChildrenResponse;
import com.tarek.vaccins.model.Father;
import com.tarek.vaccins.response.DeviceTokenResponse;
import com.tarek.vaccins.response.FatherProfileResponse;
import com.tarek.vaccins.response.FatherResponse;
import com.tarek.vaccins.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
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

}
