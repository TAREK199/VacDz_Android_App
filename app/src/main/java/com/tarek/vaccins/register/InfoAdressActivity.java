package com.tarek.vaccins.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.tarek.vaccins.response.FatherResponse;
import com.tarek.vaccins.service.FatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAdressActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerIdentityType;
    private Spinner spinnerWilaya ;
    private Spinner spinnerCommune ;

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
        spinnerWilaya = findViewById(R.id.spinner_wilaya);
        spinnerCommune = findViewById(R.id.spinner_commune);


        sharedPrefManager = new SharedPrefManager(InfoAdressActivity.this);
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
                fatherRegister();

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
                            String email = response.body().getData().getFather().getEmail();

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
}
