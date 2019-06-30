package com.tarek.vaccins.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Rdv;
import com.tarek.vaccins.response.RdvResponse;
import com.tarek.vaccins.service.FatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RdvActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private List<Rdv> rdvList ;
    SharedPrefManager sharedPrefManager ;
    private TextView textView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdv);

        sharedPrefManager = new SharedPrefManager(RdvActivity.this);

        textView = findViewById(R.id.txt_nordv_rdv);

        getRdvs();

       findViewById(R.id.img_arrow_from_notification).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(RdvActivity.this, HomeActivity.class));
           }
       });

    }

    public void viewData(){

        recyclerView = findViewById(R.id.recycle_rdv);

        RdvAdapter rdvAdapter = new RdvAdapter(RdvActivity.this,rdvList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(RdvActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rdvAdapter);
    }
    public void getRdvs(){

        String token = sharedPrefManager.getToken();
        int id = sharedPrefManager.getIdFather();

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.getRdvs("Bearer "+token,id).enqueue(new Callback<RdvResponse>() {
            @Override
            public void onResponse(Call<RdvResponse> call, Response<RdvResponse> response) {

                Boolean success = response.body().getSuccess() ;

                rdvList = new ArrayList<>();

                if (response.body().getData().size()==0) {
                    textView.setVisibility(View.VISIBLE);
                }else
                    textView.setVisibility(View.GONE);
                for (int i = 0; i<response.body().getData().size();i++){

                    rdvList.add(new Rdv(  response.body().getData().get(i).getId(),
                            response.body().getData().get(i).getDateRdv(),
                            response.body().getData().get(i).getEnfant(),
                            response.body().getData().get(i).getPolyclinique()      )) ;
                }
                viewData();

            }

            @Override
            public void onFailure(Call<RdvResponse> call, Throwable t) {
                Toast.makeText(RdvActivity.this,"rdv problem : "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }



}
