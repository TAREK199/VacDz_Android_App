package com.tarek.vaccins.polyclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Programme;

import java.util.ArrayList;
import java.util.List;

public class PolyclincProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private List<Programme> programmes ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polyclinc_profile);

        findViewById(R.id.img_arrow_from_profile_poly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PolyclincProfileActivity.this,PolyclinicActivity.class));
            }
        });

        viewData();
    }



    public void viewData(){

        recyclerView = findViewById(R.id.recycle_profile_poly);
        programmes = new ArrayList<>();


        programmes.add(new Programme("Dimanche","2Mois + 4Mois"));
        programmes.add(new Programme("Lundi","1Mois + 3mois"));
        programmes.add(new Programme("Mardi","2Mois"));
        programmes.add(new Programme("Mercredi","2Mois"));
        programmes.add(new Programme("jeudi","2Mois"));







        ProgrammPolyHorizontalAdapter programmPolyHorizontalAdapter = new ProgrammPolyHorizontalAdapter(PolyclincProfileActivity.this,programmes);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(PolyclincProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
     //   recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setLayoutManager(horizontalLayoutManager);
       // RecyclerView.LayoutManager layoutManager = new GridLayoutManager(PolyclincProfileActivity.this,1);
        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(programmPolyHorizontalAdapter);
    }

}
