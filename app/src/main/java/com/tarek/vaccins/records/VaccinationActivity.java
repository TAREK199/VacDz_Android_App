package com.tarek.vaccins.records;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Vaccin;
import com.tarek.vaccins.model.Vaccination;
import com.tarek.vaccins.response.RdvResponse;
import com.tarek.vaccins.response.RdvsChildrenResponse;
import com.tarek.vaccins.response.VaccinationResponse;
import com.tarek.vaccins.service.ChildrenService;
import com.tarek.vaccins.service.FatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccinationActivity extends AppCompatActivity {


    private RecyclerView recyclerView ;
  //  private List<Vaccin> vaccins ;
    private List<Vaccination> vaccinations;
    private ImageView imgFromVaccin ;
    private Button btnAppointement;
    private Dialog dialog ;
    private TextView vaccinationVisit,vaccinationState ;
    private int age,state;
    SharedPrefManager sharedPrefManager ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccin);


        vaccinationVisit = findViewById(R.id.txt_vaccination_visit);
        btnAppointement = findViewById(R.id.btn_vaccin_appointement);
        vaccinationState = findViewById(R.id.txt_no_vaccination);

        sharedPrefManager =  new SharedPrefManager(VaccinationActivity.this);

        findViewById(R.id.img_arrow_from_vaccin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccinationActivity.this,RecordsActivity.class));
            }
        });

        btnAppointement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(VaccinationActivity.this, WilayaActivity.class));




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
       // getRdvs();

        getChildrenRdvs();
    }

    public void viewData(){

        recyclerView = findViewById(R.id.recycle_vaccin);

        VaccinationAdapter vaccinationAdapter = new VaccinationAdapter(VaccinationActivity.this,vaccinations);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(VaccinationActivity.this,1);
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

            //    vaccins = new ArrayList<>();
                vaccinations = new ArrayList<>();


            //    vaccins = response.body().getData().getVaccins();
               vaccinations = response.body().getData().getVaccinations();

               if (response.body().getData().getVaccinations()==null){
                   Toast.makeText(VaccinationActivity.this,"pas encore vacciné" ,Toast.LENGTH_LONG).show();
            //       vaccinationState.setVisibility(View.VISIBLE);

               }else {

//        Toast.makeText(VaccinationActivity.this,"result"+vaccinations.size(),Toast.LENGTH_LONG).show();



                   viewData();
               }
                }else
                Toast.makeText(VaccinationActivity.this,"no result" ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<VaccinationResponse> call, Throwable t) {
                Toast.makeText(VaccinationActivity.this,"problme : "+t.getMessage() ,Toast.LENGTH_LONG).show();

            }
        });



    }



    public void getChildrenRdvs(){

        String token = sharedPrefManager.getToken() ;
        int id = sharedPrefManager.getIdChild();

        FatherService  fatherService =RetrofitInstance.fatherInstance();

        fatherService.getChildrenRdvs("Bearer "+token,id).enqueue(new Callback<RdvsChildrenResponse>() {
            @Override
            public void onResponse(Call<RdvsChildrenResponse> call, Response<RdvsChildrenResponse> response) {

                Boolean success = response.body().getSuccess() ;

                if (response.body().getData().equals("false")){

                    Toast.makeText(VaccinationActivity.this,"t'as prie déja un rdv ",Toast.LENGTH_LONG).show();
                     btnAppointement.setVisibility(View.INVISIBLE);


                }
            }

            @Override
            public void onFailure(Call<RdvsChildrenResponse> call, Throwable t) {
                Toast.makeText(VaccinationActivity.this,"problem  "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }


}
