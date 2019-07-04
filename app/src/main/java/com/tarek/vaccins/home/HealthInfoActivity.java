package com.tarek.vaccins.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.response.HealthInformationResponse;
import com.tarek.vaccins.service.FatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthInfoActivity extends AppCompatActivity {


    private TextView title , description ;

    SharedPrefManager sharedPrefManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccin_infos);


        title = findViewById(R.id.txt_health_name);
        description = findViewById(R.id.txt_health_description) ;


        sharedPrefManager = new SharedPrefManager(HealthInfoActivity.this);

        getHealthInfo();

        findViewById(R.id.img_from_vaccin_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthInfoActivity.this,HomeActivity.class));
            }
        });
    }


    public void getHealthInfo(){

        String token = sharedPrefManager.getToken();
        Intent intent = getIntent();

        int id  = intent.getExtras().getInt("health_id");

        FatherService fatherService = RetrofitInstance.fatherInstance() ;

        fatherService.getHealthInformation("Bearer "+token,id).enqueue(new Callback<HealthInformationResponse>() {
            @Override
            public void onResponse(Call<HealthInformationResponse> call, Response<HealthInformationResponse> response) {

                  Boolean success = response.body().getSuccess();

                   title.setText(response.body().getData().getTitre());
                    description.setText(response.body().getData().getTxt());

            }
            @Override
            public void onFailure(Call<HealthInformationResponse> call, Throwable t) {
                Toast.makeText(HealthInfoActivity.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}
