package com.tarek.vaccins.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tarek.vaccins.R;

public class InfosActivity extends AppCompatActivity {
    private Button btnContinue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);


        btnContinue  = findViewById(R.id.btn_new_account_continue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfosActivity.this, RegisterActivity.class));
                finish();return;
            }
        });
    }
}
