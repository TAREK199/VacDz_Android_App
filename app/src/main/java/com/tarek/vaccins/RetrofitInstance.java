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
  //  public static String BASE_URL = "http://192.168.1.13:9090/pfe_bo_api/public/api/";
      public static String BASE_URL = "http://192.168.1.4:8000/api/";
      //   public static String BASE_URL = "http://127.0.0.1:8081/api/";


    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    public static ChildrenService getEnfantService(){

        if (retrofit==null){

            retrofit =  new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(ChildrenService.class);
    }


    public static PolyclinicService getPolycliniqueService(){

        if (retrofit==null){

            retrofit =  new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(PolyclinicService.class);
    }

    public static FatherService getFatherService() {

        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(FatherService.class);

    }


}


