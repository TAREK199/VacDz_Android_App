package com.tarek.vaccins.records;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Vaccin;
import com.tarek.vaccins.model.Vaccination;
import com.tarek.vaccins.polyclinic.PolyclinicActivity;
import com.tarek.vaccins.response.VaccinationResponse;
import com.tarek.vaccins.service.ChildrenService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccinActivity extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private List<Vaccin> vaccins ;
    private List<Vaccination> vaccinations;
    private ImageView imgFromVaccin ;
    private Button btnAppointement ;
    private Dialog dialog ;
    private TextView vaccinationVisit ;
    private int age,state;
    SharedPrefManager sharedPrefManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccin);


        vaccinationVisit = findViewById(R.id.txt_vaccination_visit);
        btnAppointement = findViewById(R.id.btn_vaccin_appointement);


        sharedPrefManager =  new SharedPrefManager(VaccinActivity.this);


        findViewById(R.id.img_arrow_from_vaccin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccinActivity.this,RecordsActivity.class));
            }
        });

        btnAppointement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccinActivity.this, PolyclinicActivity.class));
            }
        });


        Intent intent = getIntent();
        if (intent != null) {
              age = intent.getIntExtra("age",0);
        }

        if (age==0){
            vaccinationVisit.setText("Visite de naissance");

        }else {
            vaccinationVisit.setText("Visite de " + age + " mois");
        }

        state = intent.getIntExtra("state",0);

        if (state==1){
            btnAppointement.setVisibility(View.GONE) ;
        }


        getVaccinations();
    }

    public void viewData(){

        recyclerView = findViewById(R.id.recycle_vaccin);

        VaccinationAdapter vaccinationAdapter = new VaccinationAdapter(VaccinActivity.this,vaccins,vaccinations);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(VaccinActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(vaccinationAdapter);
    }


    public void getVaccinations(){

        String token = sharedPrefManager.getToken();
        int id = sharedPrefManager.getIdChild();

        ChildrenService childrenService = RetrofitInstance.childrenInstance();

        childrenService.getChldrenVaccination("Bearer "+token,id,age).enqueue(new Callback<VaccinationResponse>() {
            @Override
            public void onResponse(Call<VaccinationResponse> call, Response<VaccinationResponse> response) {

                Boolean success = response.body().getSuccess();
                if (success){

                vaccins = new ArrayList<>();
                vaccinations = new ArrayList<>();

                vaccins = response.body().getData().getVaccins();
                vaccinations = response.body().getData().getVaccinations();
                viewData();

                }else
                Toast.makeText(VaccinActivity.this,"no result" ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<VaccinationResponse> call, Throwable t) {
                Toast.makeText(VaccinActivity.this,"problme : "+t.getMessage() ,Toast.LENGTH_LONG).show();

            }
        });



    }



}
