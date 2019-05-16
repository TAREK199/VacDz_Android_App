package com.tarek.vaccins.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.home.HomeActivity;
import com.tarek.vaccins.model.Father;
import com.tarek.vaccins.service.FatherService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAdressActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerIdentityType;
    private Spinner spinnerWilaya ;
    private Spinner spinnerCommune ;


    private static final String[] identityType = {"Carte nationale", "Permis de conduite"};
    private static final String[] wilaya = {"setif", "alger","constantine"};
    private static final String[] commune = {"eulma", "djemila","ain arnat","ain abbassa"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_adress);

        spinnerIdentityType = findViewById(R.id.spinner_identity_type);
        spinnerWilaya = findViewById(R.id.spinner_wilaya);
        spinnerCommune = findViewById(R.id.spinner_commune);


        ArrayAdapter<String> adapterIdentityType = new ArrayAdapter(InfoAdressActivity.this,
                android.R.layout.simple_spinner_item, identityType);

        adapterIdentityType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdentityType.setAdapter(adapterIdentityType);
        spinnerIdentityType.setOnItemSelectedListener(InfoAdressActivity.this);


        ArrayAdapter<String> adapterWilaya = new ArrayAdapter(InfoAdressActivity.this,
                android.R.layout.simple_spinner_item, wilaya);

        adapterWilaya.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWilaya.setAdapter(adapterWilaya);
        spinnerWilaya.setOnItemSelectedListener(InfoAdressActivity.this);


        ArrayAdapter<String> adapterCommune = new ArrayAdapter(InfoAdressActivity.this,
                android.R.layout.simple_spinner_item, commune);

        adapterCommune.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommune.setAdapter(adapterCommune);
        spinnerCommune.setOnItemSelectedListener(InfoAdressActivity.this);



        findViewById(R.id.img_from_details_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoAdressActivity.this,RegisterActivity.class));
            }
        });


        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   fatherRegister();

                startActivity(new Intent(InfoAdressActivity.this,HomeActivity.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    public void fatherRegister(){

        FatherService fatherService = RetrofitInstance.getFatherService();

        fatherService.register(new Father(74125,"hako","hako@gmail.com","112233",0,"+2136475526976","setif",129)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               Toast.makeText(InfoAdressActivity.this,"owééé : "+response.message(),Toast.LENGTH_SHORT).show();


           //    List<String> father = response.body();

          //      Toast.makeText(InfoAdressActivity.this,response.message(),Toast.LENGTH_SHORT).show();


               // startActivity(new Intent(InfoAdressActivity.this, HomeActivity.class));

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(InfoAdressActivity.this,"there's an problem here : "+t.getMessage().toString(),Toast.LENGTH_SHORT).show();
             //   startActivity(new Intent(InfoAdressActivity.this, HomeActivity.class));

            }
        });





    }
}
