package com.tarek.vaccins.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.home.HomeActivity;
import com.tarek.vaccins.R;
import com.tarek.vaccins.model.UserLogin;
import com.tarek.vaccins.notification.TestNotification;
import com.tarek.vaccins.polyclinic.PolyclincProfileActivity;
import com.tarek.vaccins.register.PhoneRegisterActivity;
import com.tarek.vaccins.response.LoginResponse;
import com.tarek.vaccins.service.FatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin ;
    private TextView txtNewAccount ;

    private EditText email,password;
    private String emailChar,passwordChar;
    private SharedPrefManager sharedPrefManager ;


    public static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "Simplified Coding";
    private static final String CHANNEL_DESC = "Simplified Coding Notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_seconnecter);
        txtNewAccount = findViewById(R.id.txt_newAccount);
        email = findViewById(R.id.edit_email_login);
        password = findViewById(R.id.edit_password_login);


        sharedPrefManager =  new SharedPrefManager(LoginActivity.this);

        if (sharedPrefManager.isLoggedIn()){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        txtNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PhoneRegisterActivity.class));
                finish();return;
            }
        });

        findViewById(R.id.txt_forget_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,TestNotification.class));
                finish();return;

            }
        });


        FirebaseApp.initializeApp(LoginActivity.this);
        FirebaseInstanceId.getInstance().getInstanceId()
                          .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                              @Override
                              public void onComplete(@NonNull Task<InstanceIdResult> task) {

                                  if (task.isSuccessful()){

                                      String token = task.getResult().getToken();

                                      sharedPrefManager.storeFireBaseToken(token);
                                   //   Toast.makeText(LoginActivity.this," firebase token is : "+sharedPrefManager.getFireBaseToken(),Toast.LENGTH_LONG).show();
                                  //    Log.d("my token",token);

                                  }else {
                                      Toast.makeText(LoginActivity.this,"error : "+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                  }
                              }
                          });
    }


    public void login(){


        emailChar = email.getText().toString();
        passwordChar = password.getText().toString();


        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.fatherLogin(new UserLogin(emailChar,passwordChar)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                Boolean success = response.body().getSuccess();

                if (success) {

                    Toast.makeText(LoginActivity.this, "is logged in", Toast.LENGTH_LONG).show();
                    sharedPrefManager.fatherLogin(response.body().getData().getPere().getFatherId(),
                            response.body().getData().getUser().getId(),
                            response.body().getData().getUser().getEmail(),
                            response.body().getData().getToken());


                       startActivity(new Intent(LoginActivity.this, HomeActivity.class));


                }
                else {
                    Toast.makeText(LoginActivity.this, "compte n'existe pas ", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this,"problem : "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
