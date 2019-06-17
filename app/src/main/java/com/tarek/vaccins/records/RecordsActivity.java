package com.tarek.vaccins.records;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import  android.support.design.widget.BottomNavigationView ;
import com.tarek.vaccins.R;
import com.tarek.vaccins.home.HomeActivity;

public class RecordsActivity extends AppCompatActivity {


    private FrameLayout frameLayout ;
    private BottomNavigationView navigationMenu ;
    private CalendarVaccinationFragment calendarVaccinationFragment;
    private ChildProfileFragment childProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        frameLayout = findViewById(R.id.frame_childrens_main_nav);
        navigationMenu = findViewById(R.id.bottomnav_childrens_main_nav);

        calendarVaccinationFragment = new CalendarVaccinationFragment();
        childProfileFragment = new ChildProfileFragment();

        navigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_vaccination_child:
                        setFragment(calendarVaccinationFragment);
                        return true;

                    case R.id.nav_profile_child:
                        setFragment(childProfileFragment);
                        return true ;

                        default:
                            return false;
                }
            }

        });

        findViewById(R.id.img_arrow_from_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecordsActivity.this, HomeActivity.class));
            }
        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_childrens_main_nav,fragment);
        fragmentTransaction.commit();
    }

    }

