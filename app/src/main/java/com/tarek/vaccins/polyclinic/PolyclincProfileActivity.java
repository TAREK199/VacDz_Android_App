package com.tarek.vaccins.polyclinic;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.home.HomeActivity;
import com.tarek.vaccins.model.Programme;
import com.tarek.vaccins.model.Rdv;
import com.tarek.vaccins.response.PolyclinicResponse;
import com.tarek.vaccins.response.RdvAddResponse;
import com.tarek.vaccins.service.FatherService;
import com.tarek.vaccins.service.PolyclinicService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PolyclincProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private List<Programme> programmes ;
    private Button prendrRdv , confirmeRdv ;
    private String date ;
    private TextView polyName,polyAdress,polyTel ;
    private static final String TAG = "datepicker";
    private DatePickerDialog.OnDateSetListener mDateSetListener ;
    SharedPrefManager sharedPrefManager ;
    private int polyId ;
    private  String token ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polyclinc_profile);

        polyName = findViewById(R.id.txt_poly_name_profile);
        polyAdress = findViewById(R.id.txt_poly_adr_profile);
        polyTel = findViewById(R.id.txt_poly_tel_profile);
        prendrRdv = findViewById(R.id.btn_vaccin_appointement);
        confirmeRdv = findViewById(R.id.btn_vaccin_appointement_confirm);

        sharedPrefManager = new SharedPrefManager(PolyclincProfileActivity.this);
        token = sharedPrefManager.getToken();
        Intent intent = getIntent() ;
        polyId = intent.getIntExtra("poly_id",0);

        findViewById(R.id.img_arrow_from_profile_poly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PolyclincProfileActivity.this,PolyclinicActivity.class));
            }
        });

        viewData();
        getInfo();

        prendrRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PolyclincProfileActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
               // Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date = month + "-" + day+"-" +year+"T13:34:00.000";
              //  mDisplayDate.setText(date);

                prendrRdv.setVisibility(View.GONE);
                confirmeRdv.setVisibility(View.VISIBLE);
                Toast.makeText(PolyclincProfileActivity.this,date,Toast.LENGTH_LONG).show();
            }
        };

        confirmeRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                takeAppoinment();

                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        PolyclincProfileActivity.this, R.style.AlertDialogTheme);

                // Setting Dialog Title
                alertDialog2.setTitle("Confirmer RDV");

// Setting Dialog Message
                alertDialog2.setMessage("vous etes sur de prendre cet RDV ?");

// Setting Icon to Dialog
                alertDialog2.setIcon(android.R.drawable.ic_dialog_alert);

// Setting Positive "Yes" Btn
                alertDialog2.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                               takeAppoinment();
                            }
                        });

// Setting Negative "NO" Btn
                alertDialog2.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog

                                dialog.cancel();
                            }
                        });

// Showing Alert Dialog
                alertDialog2.show();


            }
        });
    }

    //     .setIcon(android.R.drawable.ic_dialog_alert);


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
   public void getInfo() {

        PolyclinicService polyclinicService = RetrofitInstance.polyclinicInstance();

        polyclinicService.getPolycliniqueInfo("Bearer "+token,polyId).enqueue(new Callback<PolyclinicResponse>() {
            @Override
            public void onResponse(Call<PolyclinicResponse> call, Response<PolyclinicResponse> response) {

                Boolean success = response.body().getSuccess();

                if (success){

                    polyName.setText(response.body().getData().getPolyclinique().getPolyName());
                    polyAdress.setText(response.body().getData().getPolyclinique().getPolyAdress());
                    polyTel.setText(response.body().getData().getPolyclinique().getTel());
                }
                else {
                    Toast.makeText(PolyclincProfileActivity.this,"no data to display",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<PolyclinicResponse> call, Throwable t) {
                Toast.makeText(PolyclincProfileActivity.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
   public void takeAppoinment() {

            int childId = sharedPrefManager.getIdChild();

            FatherService fatherService = RetrofitInstance.fatherInstance();

            fatherService.prenRdv("Bearer " +token, new Rdv(childId, polyId, "2019-06-15T13:34:00.000\n")).enqueue(new Callback<RdvAddResponse>() {
                @Override
                public void onResponse(Call<RdvAddResponse> call, Response<RdvAddResponse> response) {

                    Boolean success = response.body().getSuccess();
                    Toast.makeText(PolyclincProfileActivity.this, "votre RDV est pris en charge", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(PolyclincProfileActivity.this, HomeActivity.class));
                }

                @Override
                public void onFailure(Call<RdvAddResponse> call, Throwable t) {
                    Toast.makeText(PolyclincProfileActivity.this, "problem : " + t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });


        }



}
