package com.tarek.vaccins.polyclinic;

import android.app.SearchManager;
import android.content.Context;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Polyclinic;
import com.tarek.vaccins.records.VaccinationActivity;
import com.tarek.vaccins.service.PolyclinicService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PolyclinicActivity extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private List<Polyclinic> polyclinicList;
    PolyclinicAdapater polyclinicAdapater ;
    private Toolbar toolbar ;
    private MaterialSearchView searchView ;
    private SearchView search ;
    SharedPrefManager sharedPrefManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polyclinic);

        toolbar = findViewById(R.id.toolbar_poly_search);
        searchView =  findViewById(R.id.searchview_poly);

       //    search = findViewById(R.id.searchview_poly_new);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Trouver votre polyclinique");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        sharedPrefManager = new SharedPrefManager(PolyclinicActivity.this);




        getPolycliniques();
        findViewById(R.id.img_from_poly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PolyclinicActivity.this, VaccinationActivity.class));
            }
        });


        polyclinicAdapater = new PolyclinicAdapater(PolyclinicActivity.this,polyclinicList);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                polyclinicAdapater.getFilter().filter(query);

                Toast.makeText(PolyclinicActivity.this,"eeh text change " +query,Toast.LENGTH_LONG).show();

                return false;
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                polyclinicAdapater.getFilter().filter(query);

                Toast.makeText(PolyclinicActivity.this,"eeh text change " +query,Toast.LENGTH_LONG).show();

                return false;
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

        PolyclinicAdapater polyclinicAdapater = new PolyclinicAdapater(PolyclinicActivity.this, polyclinicList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(PolyclinicActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(polyclinicAdapater);
    }
    public void getPolycliniques(){


        String token = sharedPrefManager.getToken();
        String commune = sharedPrefManager.getCommune();

        PolyclinicService polyclinicService = RetrofitInstance.polyclinicInstance();
        polyclinicService.getPolycliniques("Bearer "+token,commune).enqueue(new Callback<List<Polyclinic>>() {
            @Override
            public void onResponse(Call<List<Polyclinic>> call, Response<List<Polyclinic>> response) {


                if (response.body().size()!=0) {
                    polyclinicList = new ArrayList<>();
                    polyclinicList = response.body();
                    viewData();
                }else {
                    Toast.makeText(PolyclinicActivity.this,"aucune polycliniques dans ce lieu",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Polyclinic>> call, Throwable t) {
                Toast.makeText(PolyclinicActivity.this,"problem : "+t.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }


}
