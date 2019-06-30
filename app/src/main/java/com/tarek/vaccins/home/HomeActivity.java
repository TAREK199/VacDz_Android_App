package com.tarek.vaccins.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.response.DeviceTokenResponse;
import com.tarek.vaccins.response.RdvResponse;
import com.tarek.vaccins.service.FatherService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private FrameLayout frameLayout ;
    private BottomNavigationView navigationMenu ;
    private ChildrensFragment childrensFragment;
    private HealthSpaceFragment healthSpaceFragment;
    private FatherProfileFragment fatherProfileFragment ;
    private SharedPrefManager sharedPrefManager ;
    private TextView rdvCounter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameLayout = findViewById(R.id.frame_home);
        navigationMenu = findViewById(R.id.bottomnav_home_nav);

        rdvCounter = findViewById(R.id.txt_rdv_count_home) ;


        sharedPrefManager = new SharedPrefManager(HomeActivity.this);

        findViewById(R.id.img_to_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SettingActivity.class));
            }
        });

        childrensFragment = new ChildrensFragment();
        healthSpaceFragment = new HealthSpaceFragment();
        fatherProfileFragment = new FatherProfileFragment() ;

        getRdvs();

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame_home, new HealthSpaceFragment());
        tx.commit();

        navigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home_health_espace:
                        setFragment(healthSpaceFragment);
                        return true;

                    case R.id.nav_home_childrens:
                        setFragment(childrensFragment);
                        return true;

                    case R.id.nav_home_father_profile:
                        setFragment(fatherProfileFragment);
                        return true;
                    default:
                        return false;

                }
            }

        });

        findViewById(R.id.img_rdv_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RdvActivity.class));
            }
        });

              sendDeviceToken();
    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_home,fragment);
        fragmentTransaction.commit();
    }

    public void sendDeviceToken(){

        String deviceToken = sharedPrefManager.getFireBaseToken();
        String token = sharedPrefManager.getToken();


        FatherService fatherService = RetrofitInstance.fatherInstance();
        fatherService.storeFireBaseToken("Bearer "+token,deviceToken).enqueue(new Callback<DeviceTokenResponse>() {
            @Override
            public void onResponse(Call<DeviceTokenResponse> call, Response<DeviceTokenResponse> response) {


                Boolean success = response.body().getSuccess();

            }

            @Override
            public void onFailure(Call<DeviceTokenResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"problem : "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
    public void getRdvs(){

        String token = sharedPrefManager.getToken();
        int id = sharedPrefManager.getIdFather();

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.getRdvs("Bearer "+token,id).enqueue(new Callback<RdvResponse>() {
            @Override
            public void onResponse(Call<RdvResponse> call, Response<RdvResponse> response) {


                if(response.body().getData().size()!=0) {
                    rdvCounter.setText(String.valueOf(response.body().getData().size()));
                }else
                    rdvCounter.setText(" ");

            }

            @Override
            public void onFailure(Call<RdvResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"rdv problem home: "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }


}
