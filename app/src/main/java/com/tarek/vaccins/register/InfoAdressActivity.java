package com.tarek.vaccins.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.home.HomeActivity;
import com.tarek.vaccins.model.Father;
import com.tarek.vaccins.model.Wilaya;
import com.tarek.vaccins.response.CommuneResponse;
import com.tarek.vaccins.response.FatherResponse;
import com.tarek.vaccins.response.WilayaResponse;
import com.tarek.vaccins.service.FatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAdressActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerIdentityType;
    private RecyclerView recyclerViewWilaya ;
    List<Wilaya> wilayaList ;
    SharedPrefManager sharedPrefManager ;


    private static final String[] identityType = {"Carte nationale"};
    private static final String[] wilaya = {"setif", "alger","constantine"};
    private static final String[] commune = {"eulma", "djemila","ain arnat","ain abbassa"};

    private EditText identityNumber;
     String firsrName,lastName,phoneNumber, email, password,identityTypeChar,identityNumberChar,wilayaChar,communeChar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_adress);

        identityNumber = findViewById(R.id.edt_identity_number);

        spinnerIdentityType = findViewById(R.id.spinner_identity_type);

        recyclerViewWilaya = findViewById(R.id.recycle_wilaya_register) ;

        sharedPrefManager = new SharedPrefManager(InfoAdressActivity.this);

        ArrayAdapter<String> adapterIdentityType = new ArrayAdapter(InfoAdressActivity.this,
                android.R.layout.simple_spinner_item, identityType);

        adapterIdentityType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdentityType.setAdapter(adapterIdentityType);
        spinnerIdentityType.setOnItemSelectedListener(InfoAdressActivity.this);

        findViewById(R.id.img_from_details_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoAdressActivity.this,RegisterActivity.class));
            }
        });

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fatherRegister();
            }
        });

        getWilayas();
        getCommune();
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
        Intent intent = getIntent();
           firsrName = intent.getStringExtra("firstName");
           lastName = intent.getStringExtra("lastName");
           phoneNumber= intent.getStringExtra("phoneNumber");
           email = intent.getStringExtra("email");
           password = intent.getStringExtra("password");


        identityNumberChar = identityNumber.getText().toString();
        int in = Integer.valueOf(identityNumber.getText().toString());




        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.register(new Father("1",email,"123",firsrName,lastName,phoneNumber,"setif","12455",in)).enqueue(new Callback<FatherResponse>() {

                @Override
            public void onResponse(Call<FatherResponse> call, Response<FatherResponse> response) {


                    Boolean succes = response.body().getSuccess();



                    if (succes) {
                        Toast.makeText(InfoAdressActivity.this, "success : " + succes, Toast.LENGTH_SHORT).show();

                            String token = response.body().getData().getToken();
                            int id = response.body().getData().getFather().getFatherId();
                            int userId = response.body().getData().getUser().getId();
                            String email = response.body().getData().getUser().getEmail();

                            sharedPrefManager.fatherRegister(id,userId,email,token);

                            startActivity(new Intent(InfoAdressActivity.this, HomeActivity.class));

                    }else {
                        Toast.makeText(InfoAdressActivity.this, "compte existe déja", Toast.LENGTH_SHORT).show();

                    }

            }

            @Override
            public void onFailure(Call<FatherResponse> call, Throwable t) {
                Toast.makeText(InfoAdressActivity.this,"problem : "+t.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void viewData(){
        recyclerViewWilaya = findViewById(R.id.recycle_wilaya_register);
        WilayaAdapterRegister wilayaAdapter = new WilayaAdapterRegister(InfoAdressActivity.this,wilayaList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(InfoAdressActivity.this,1);
        recyclerViewWilaya.setLayoutManager(layoutManager);
        recyclerViewWilaya.setAdapter(wilayaAdapter);
    }

    public void getWilayas(){

        String token = sharedPrefManager.getToken();

        FatherService fatherService = RetrofitInstance.fatherInstance();
        fatherService.getWilayas("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImUxYmViYWY2NjU2MDg3ZDZhMDYzYjUzYjhiY2JiZDIxMDUxNzAzMGY1Njg4NjhlMzZiODVhYjYyOGE2MjU5ZTRlYmNiZjgxMmFmMGY5NDNiIn0.eyJhdWQiOiIxIiwianRpIjoiZTFiZWJhZjY2NTYwODdkNmEwNjNiNTNiOGJjYmJkMjEwNTE3MDMwZjU2ODg2OGUzNmI4NWFiNjI4YTYyNTllNGViY2JmODEyYWYwZjk0M2IiLCJpYXQiOjE1NjA1NTUxODQsIm5iZiI6MTU2MDU1NTE4NCwiZXhwIjoxNTkyMTc3NTg0LCJzdWIiOiIxNyIsInNjb3BlcyI6W119.moqYF6-XE0Wz5CXyXakvIPvQ2nRoiuVz3z72FlPLJr2gLmdOUd3leCwPZdHRmvlQrqRViC3tKnOR3bTfZRuBUYekLwd9q-XRxqB8d6S-GPJpoWhEAlpPMvCbOjpnU5hvX1CtOSOUpG9Qz_FW6r9NZNp0f8sxX0PUZ8nMX8vYHXDCcIeiMMV1IHX0B7TEYnab2piNP6iWi99k0xV8uf0qo4u8d5mK32zZmP6MCmYPKZtMG45vXtJXNqSqVwfksNM883Z1wmpGbP0hjktM8-BIJ4hMuLL5cdyKxBwJiLlsi12IOR3Fq4HCyTalYxXeAMc8zZcER2eJmXXVPQNnp7e0yfV4O7xzy1lhNiYCuUAfOti-PIHYTrY0VufMwbtCdbgJHDlyXqx0qBgNAGL8BShWGM-8C1Zq8wcMoJkHgjGmOHiB9JatOieb_kEViCb_FJy4YYK6GpZm6F-dNHN_GJCtcjuYgTQOMCF-v0TJp05PrCfRQA5jwmXLYVFRHO0EXqhn6ficRHaFwPtbVoq3qTCgMJ80BH2ZdWs1rtv8o9WykVjPJ2PVdtTpIaU7W9FjGalwlzhvh6Qjr7o9WoDNvAkhFE79GL1luaIE82UV9x-or5w-4guTSVYInIf8Y7Jza_AZyWUVmMmu73-jkcJdl9cVGaeqvHn8QmFqf4ZA6OtEcus").enqueue(new Callback<WilayaResponse>() {
            @Override
            public void onResponse(Call<WilayaResponse> call, Response<WilayaResponse> response) {

                Boolean success = response.body().getSuccess();

                Toast.makeText(InfoAdressActivity.this,"no wialaya to display : "+success,Toast.LENGTH_LONG).show();

                if (success){
                    wilayaList = new ArrayList<>();
                    wilayaList = response.body().getData();
                    viewData();
                }
            }

            @Override
            public void onFailure(Call<WilayaResponse> call, Throwable t) {

                Toast.makeText(InfoAdressActivity.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    public void getCommune(){
        String token = sharedPrefManager.getToken();
        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.getCommunes("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImUxYmViYWY2NjU2MDg3ZDZhMDYzYjUzYjhiY2JiZDIxMDUxNzAzMGY1Njg4NjhlMzZiODVhYjYyOGE2MjU5ZTRlYmNiZjgxMmFmMGY5NDNiIn0.eyJhdWQiOiIxIiwianRpIjoiZTFiZWJhZjY2NTYwODdkNmEwNjNiNTNiOGJjYmJkMjEwNTE3MDMwZjU2ODg2OGUzNmI4NWFiNjI4YTYyNTllNGViY2JmODEyYWYwZjk0M2IiLCJpYXQiOjE1NjA1NTUxODQsIm5iZiI6MTU2MDU1NTE4NCwiZXhwIjoxNTkyMTc3NTg0LCJzdWIiOiIxNyIsInNjb3BlcyI6W119.moqYF6-XE0Wz5CXyXakvIPvQ2nRoiuVz3z72FlPLJr2gLmdOUd3leCwPZdHRmvlQrqRViC3tKnOR3bTfZRuBUYekLwd9q-XRxqB8d6S-GPJpoWhEAlpPMvCbOjpnU5hvX1CtOSOUpG9Qz_FW6r9NZNp0f8sxX0PUZ8nMX8vYHXDCcIeiMMV1IHX0B7TEYnab2piNP6iWi99k0xV8uf0qo4u8d5mK32zZmP6MCmYPKZtMG45vXtJXNqSqVwfksNM883Z1wmpGbP0hjktM8-BIJ4hMuLL5cdyKxBwJiLlsi12IOR3Fq4HCyTalYxXeAMc8zZcER2eJmXXVPQNnp7e0yfV4O7xzy1lhNiYCuUAfOti-PIHYTrY0VufMwbtCdbgJHDlyXqx0qBgNAGL8BShWGM-8C1Zq8wcMoJkHgjGmOHiB9JatOieb_kEViCb_FJy4YYK6GpZm6F-dNHN_GJCtcjuYgTQOMCF-v0TJp05PrCfRQA5jwmXLYVFRHO0EXqhn6ficRHaFwPtbVoq3qTCgMJ80BH2ZdWs1rtv8o9WykVjPJ2PVdtTpIaU7W9FjGalwlzhvh6Qjr7o9WoDNvAkhFE79GL1luaIE82UV9x-or5w-4guTSVYInIf8Y7Jza_AZyWUVmMmu73-jkcJdl9cVGaeqvHn8QmFqf4ZA6OtEcus",19).enqueue(new Callback<CommuneResponse>() {
            @Override
            public void onResponse(Call<CommuneResponse> call, Response<CommuneResponse> response) {

                Boolean success = response.body().getSuccess();

                if(!success)
                    Toast.makeText(InfoAdressActivity.this,"no commune to display : "+success,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<CommuneResponse> call, Throwable t) {
                Toast.makeText(InfoAdressActivity.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

}
