package com.tarek.vaccins;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tarek.vaccins.service.ChildrenService;
import com.tarek.vaccins.service.FatherService;
import com.tarek.vaccins.service.PolyclinicService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit retrofit = null;

    //public static  String BASE_URL = "http://127.0.0.10:8000/api/";
    //  public static String BASE_URL = "http://192.168.1.4:8000/api/";
    //    public static String BASE_URL = "http://192.168.1.9:9090/pfe_bo_api/public/api/";
    public static String BASE_URL = "http://192.168.1.9:9090/pfe_bo_api/public/api/";


    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    public static PolyclinicService polyInstance(){

        if (retrofit==null){

            retrofit =  new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(PolyclinicService.class);
    }

    public static FatherService fatherInstance() {

        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(FatherService.class);

    }

    public static ChildrenService childrenInstance() {

        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(ChildrenService.class);
    }


}



