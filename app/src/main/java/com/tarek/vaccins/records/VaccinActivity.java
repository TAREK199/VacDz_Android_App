package com.tarek.vaccins.records;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Vaccin;
import com.tarek.vaccins.polyclinic.PolyclinicActivity;

import java.util.ArrayList;
import java.util.List;

public class VaccinActivity extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private List<Vaccin> vaccins ;
    private ImageView imgFromVaccin ;
    private Button btnAppointement ;
    private Dialog dialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccin);

        imgFromVaccin =  findViewById(R.id.img_arrow_from_vaccin);
        btnAppointement = findViewById(R.id.btn_vaccin_appointement);


        imgFromVaccin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccinActivity.this,RecordsActivity.class));
            }
        });

        btnAppointement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccinActivity.this, PolyclinicActivity.class));
            }
        });




        viewData();
    }

    public void viewData(){

        recyclerView = findViewById(R.id.recycle_vaccin);
        vaccins = new ArrayList<>();

        Toast.makeText(VaccinActivity.this,"in viewDta",Toast.LENGTH_LONG).show();


        vaccins.add(new Vaccin("Hépatit"));
        vaccins.add(new Vaccin("BCG"));
        vaccins.add(new Vaccin("Titanos"));


        VaccinAdapter vaccinAdapter = new VaccinAdapter(VaccinActivity.this,vaccins);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(VaccinActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(vaccinAdapter);
    }



}
