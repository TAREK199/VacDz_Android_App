package com.tarek.vaccins.records;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Wilaya;
import com.tarek.vaccins.response.CommuneResponse;
import com.tarek.vaccins.response.WilayaResponse;
import com.tarek.vaccins.service.FatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WilayaActivity extends AppCompatActivity {


    private  RecyclerView recyclerView ;
    SharedPrefManager sharedPrefManager ;
    List<Wilaya> wilayaList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilaya);
        sharedPrefManager = new SharedPrefManager(WilayaActivity.this);
        getWilayas();
        getCommune();

        findViewById(R.id.img_arrow_from_wilaya).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WilayaActivity.this,VaccinActivity.class));
            }
        });
    }


    public void viewData(){
        recyclerView = findViewById(R.id.recycle_wilaya);
        WilayaAdapter wilayaAdapter = new WilayaAdapter(WilayaActivity.this,wilayaList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(WilayaActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wilayaAdapter);
    }

    public void getWilayas(){

        String token = sharedPrefManager.getToken();

        FatherService fatherService = RetrofitInstance.fatherInstance();


        fatherService.getWilayas("Bearer "+token).enqueue(new Callback<WilayaResponse>() {
            @Override
            public void onResponse(Call<WilayaResponse> call, Response<WilayaResponse> response) {

                Boolean success = response.body().getSuccess();

                if (success){
                    wilayaList = new ArrayList<>();
                    wilayaList = response.body().getData();
                    viewData();
                }
            }

            @Override
            public void onFailure(Call<WilayaResponse> call, Throwable t) {

                Toast.makeText(WilayaActivity.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }
    public void getCommune(){
        String token = sharedPrefManager.getToken();
        FatherService fatherService = RetrofitInstance.fatherInstance();


        fatherService.getCommunes("Bearer "+token,19).enqueue(new Callback<CommuneResponse>() {
            @Override
            public void onResponse(Call<CommuneResponse> call, Response<CommuneResponse> response) {

                    Boolean success = response.body().getSuccess();

                    if(!success)
                        Toast.makeText(WilayaActivity.this,"no data to display",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<CommuneResponse> call, Throwable t) {

                Toast.makeText(WilayaActivity.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

}