package com.tarek.vaccins.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tarek.vaccins.R;

public class RegisterActivity extends AppCompatActivity  {


    private Button continueToAdr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        continueToAdr = findViewById(R.id.btn_to_adrs);


        continueToAdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, InfoAdressActivity.class));
            }
        });

     /*   Bundle bundle = getIntent().getExtras();

        String phNumber = bundle.getString("phNumber") ;

        if(phNumber!= null)
        {
            //TODO here get the string stored in the string variable and do
            // setText() on userName
            Toast.makeText(RegisterActivity.this,phNumber,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(RegisterActivity.this,"empty",Toast.LENGTH_LONG).show();
        }
*/

    }






}
