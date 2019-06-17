package com.tarek.vaccins.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.edit.EditAccountActivity;
import com.tarek.vaccins.login.LoginActivity;

public class SettingActivity extends AppCompatActivity {



    private TextView email;
    SharedPrefManager sharedPrefManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sharedPrefManager = new SharedPrefManager(SettingActivity.this);


        email = findViewById(R.id.txt_email_settings);


        findViewById(R.id.img_from_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,HomeActivity.class));
            }
        });

        findViewById(R.id.linear_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPrefManager.logOut();
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
            }
        });

        findViewById(R.id.img_from_setting_to_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialogBuilder = new AlertDialog.Builder(SettingActivity.this).create();
                LayoutInflater inflater = SettingActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_check_password, null);

                final EditText checkPassword = dialogView.findViewById(R.id.edt_comment);
                Button confirmer = dialogView.findViewById(R.id.buttonSubmit);
                Button annuler = dialogView.findViewById(R.id.buttonCancel);

                annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                confirmer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // DO SOMETHINGS

                        if (checkPassword.getText().toString().toString().equals("0000")) {
                            startActivity(new Intent(SettingActivity.this, EditAccountActivity.class));
                            dialogBuilder.dismiss();
                        }else {
                            checkPassword.setError("mot de passe incorect !");
                        }
                    }

                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();
            }
        });


        String emailChar = sharedPrefManager.getEmail();
        email.setText(emailChar);
    }


}
