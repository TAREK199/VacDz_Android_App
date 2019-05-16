package com.tarek.vaccins.polyclinic;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.home.HomeActivity;
import com.tarek.vaccins.service.PolyclinicService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PolyclinicActivity extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private List<Polyclinic> polyclinicList;
    private Toolbar toolbar ;
    private MaterialSearchView searchView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polyclinic);

        toolbar = findViewById(R.id.toolbar_poly_search);
        searchView =  findViewById(R.id.searview_poly);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Trouver votre polyclinique");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));



        viewData();
        getPolycliniques();

        findViewById(R.id.img_from_poly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PolyclinicActivity.this, HomeActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_item,menu);
        MenuItem item = menu.findItem(R.id.item_poly_search);
        searchView.setMenuItem(item);
        return true;
    }

    public void viewData(){

        recyclerView = findViewById(R.id.recycle_poly);
        polyclinicList = new ArrayList<>();

        Toast.makeText(PolyclinicActivity.this,"in viewDta",Toast.LENGTH_LONG).show();

        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));
        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));
        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));
        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));
        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));
        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));
        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));
        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));
        polyclinicList.add(new Polyclinic("saadna abdennor","city belair"));



        PolyclinicAdapater polyclinicAdapater = new PolyclinicAdapater(PolyclinicActivity.this, polyclinicList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(PolyclinicActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(polyclinicAdapater);
    }


    public void getPolycliniques(){

        PolyclinicService polyclinicService = RetrofitInstance.getPolycliniqueService();

        polyclinicService.getPolycliniques().enqueue(new Callback<Polyclinic>() {
            @Override
            public void onResponse(Call<Polyclinic> call, Response<Polyclinic> response) {
                Toast.makeText(PolyclinicActivity.this,"résult is : "+response.body().getPolyAdress(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Polyclinic> call, Throwable t) {
                Toast.makeText(PolyclinicActivity.this,"problem : "+t.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }


}
