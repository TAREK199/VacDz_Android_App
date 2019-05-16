package com.tarek.vaccins.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.login.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {


    private TextView txtSeConnecter ;
    private Button btnNewAccount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtSeConnecter = findViewById(R.id.txt_seconnecter);
        btnNewAccount = findViewById(R.id.btn_new_account);


        txtSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();return;
            }
        });

        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, PhoneRegisterActivity.class));
                finish();return;
            }
        });

    }
}
