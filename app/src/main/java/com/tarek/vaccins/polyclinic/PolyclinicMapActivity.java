package com.tarek.vaccins.polyclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tarek.vaccins.R;

public class PolyclinicMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polyclinic_map);


        findViewById(R.id.img_from_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PolyclinicMapActivity.this,PolyclinicActivity.class));
            }
        });
    }
}
