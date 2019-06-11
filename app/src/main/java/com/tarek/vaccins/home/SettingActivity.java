package com.tarek.vaccins.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tarek.vaccins.R;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.login.LoginActivity;

public class SettingActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPrefManager = new SharedPrefManager(SettingActivity.this);

        findViewById(R.id.img_from_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,HomeActivity.class));
            }
        });

        findViewById(R.id.linear_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPrefManager.logOut();
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
            }
        });
    }
}
