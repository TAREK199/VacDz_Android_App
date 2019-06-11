package com.tarek.vaccins.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tarek.vaccins.R;

public class RegisterActivity extends AppCompatActivity  {


    private Button continueToAdr;
    private EditText firsName ,lastName,email,password,confirmPassword;
    private String firsNameChar ,lastNameChar,emailChar,passwordChar,confirmPasswordChar,phNumber;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        continueToAdr = findViewById(R.id.btn_to_adrs);

        firsName =  findViewById(R.id.edt_firstname);
        lastName = findViewById(R.id.edt_lastname);
        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password_register);
        confirmPassword = findViewById(R.id.edit_confirm_password);




        continueToAdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();

            }
        });

        Bundle bundle = getIntent().getExtras();
        phNumber = bundle.getString("phNumber") ;

    }

    public void sendData(){


        firsNameChar = firsName.getText().toString();
        lastNameChar = lastName.getText().toString();
        emailChar = email.getText().toString().trim();
        passwordChar = password.getText().toString();

        Intent myIntent = new Intent(this, InfoAdressActivity.class);
        myIntent.putExtra("firstName", firsNameChar);
        myIntent.putExtra("lastName", lastNameChar);
        myIntent.putExtra("phoneNumber", phNumber);
        myIntent.putExtra("email", emailChar);
        myIntent.putExtra("password", passwordChar);
        startActivity(myIntent);
    }






}
