package com.tarek.vaccins.records;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import  android.support.design.widget.BottomNavigationView ;
import com.tarek.vaccins.R;

public class RecordsActivity extends AppCompatActivity {


    private FrameLayout frameLayout ;
    private BottomNavigationView navigationMenu ;
    private CalendarVaccinationFragment calendarVaccinationFragment;
    private RecordFragment recordFragment ;
    private ChildProfileFragment childProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        frameLayout = findViewById(R.id.frame_childrens_main_nav);
        navigationMenu = findViewById(R.id.bottomnav_childrens_main_nav);

        calendarVaccinationFragment = new CalendarVaccinationFragment();
        recordFragment = new RecordFragment();
        childProfileFragment = new ChildProfileFragment();

        navigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_vaccination_child:
                        setFragment(calendarVaccinationFragment);
                        return true;

                    case R.id.nav_record_child:
                        setFragment(recordFragment);
                        return true;

                    case R.id.nav_profile_child:
                        setFragment(childProfileFragment);
                        return true ;

                        default:
                            return false;

                }
            }

        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_childrens_main_nav,fragment);
        fragmentTransaction.commit();
    }

    }

