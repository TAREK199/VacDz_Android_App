package com.tarek.vaccins.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tarek.vaccins.home.HomeActivity;
import com.tarek.vaccins.R;
import com.tarek.vaccins.register.PhoneRegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin ;
    private TextView txtNewAccount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = findViewById(R.id.btn_seconnecter);
        txtNewAccount = findViewById(R.id.txt_newAccount);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();return;
            }
        });
        txtNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PhoneRegisterActivity.class));
                finish();return;
            }
        });
    }
}
