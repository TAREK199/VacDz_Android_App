package com.tarek.vaccins.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.synnapps.carouselview.CarouselView;
import com.tarek.vaccins.R;
import com.tarek.vaccins.model.HealthSpace;
import com.tarek.vaccins.notification.NotificationActivity;

public class HomeActivity extends AppCompatActivity {

    private FrameLayout frameLayout ;
    private BottomNavigationView navigationMenu ;
    private ChildrensFragment childrensFragment;
    private HealthSpaceFragment healthSpaceFragment;
    private RDVFragment rdvFragment ;
    private FatherProfileFragment fatherProfileFragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameLayout = findViewById(R.id.frame_home);
        navigationMenu = findViewById(R.id.bottomnav_home_nav);

        findViewById(R.id.img_to_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SettingActivity.class));
            }
        });

        childrensFragment = new ChildrensFragment();
        healthSpaceFragment = new HealthSpaceFragment();
        rdvFragment = new RDVFragment();
        fatherProfileFragment = new FatherProfileFragment() ;


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

                    case R.id.nav_home_appoinment:
                        setFragment(rdvFragment);
                        return true;

                    case R.id.nav_home_father_profile:
                        setFragment(fatherProfileFragment);
                        return true;
                    default:
                        return false;

                }
            }

        });

        findViewById(R.id.img_notification_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
            }
        });


    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_home,fragment);
        fragmentTransaction.commit();
    }


}
