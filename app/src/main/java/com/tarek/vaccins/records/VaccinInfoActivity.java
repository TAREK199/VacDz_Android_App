package com.tarek.vaccins.records;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;

public class VaccinInfoActivity extends AppCompatActivity {

    private TextView vaccinPoly,vaccinDate ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccin_info);

        vaccinPoly = findViewById(R.id.txt_vaccin_poly_vac);
        vaccinDate = findViewById(R.id.txt_vaccin_date_vac);

        Intent intent = getIntent();
       String  polyVac = intent.getStringExtra("poly_vac");
       String  dateVac = intent.getStringExtra("date_vac");

       vaccinPoly.setText(polyVac);
       vaccinDate.setText(dateVac);

       findViewById(R.id.img_arrow_from_vaccin_info).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(VaccinInfoActivity.this,VaccinationActivity.class));
           }
       });

      //  Toast.makeText(VaccinInfoActivity.this,polyVac + " "+dateVac,Toast.LENGTH_LONG).show();
    }
}
