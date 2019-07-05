package com.tarek.vaccins.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.home.SettingActivity;
import com.tarek.vaccins.response.UserResponse;
import com.tarek.vaccins.service.FatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountActivity extends AppCompatActivity {


    private EditText email ,password;
    private String emailChar ,passwordChar;
    SharedPrefManager sharedPrefManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        sharedPrefManager = new SharedPrefManager(EditAccountActivity.this);
        email = findViewById(R.id.edt_email_edit_account);
        password = findViewById(R.id.edt_password_edit_account);

        email.setText(sharedPrefManager.getEmail());


        findViewById(R.id.img_arrow_from_edit_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditAccountActivity.this, SettingActivity.class));
            }
        });

        findViewById(R.id.btn_edit_email_edit_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail();
            }
        });

        findViewById(R.id.btn_edit_password_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }

    public void updateEmail(){

        String token = sharedPrefManager.getToken();
        emailChar = email.getText().toString().trim();
        int userId = sharedPrefManager.getUserId();

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.updateEmail("Bearer "+token,userId,emailChar).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                Boolean success = response.body().getSuccess();

                sharedPrefManager.storeEmail(response.body().getData().getUser().getEmail());
                Toast.makeText(EditAccountActivity.this,"votre email est a jour",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(EditAccountActivity.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });



    }


    public void updatePassword(){

        String token = sharedPrefManager.getToken();
        passwordChar = password.getText().toString().trim();
        int userId = sharedPrefManager.getUserId();

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.updatePassword("Bearer "+token,userId,passwordChar).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                Toast.makeText(EditAccountActivity.this,"votre mot de passe est a jour",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {


                Toast.makeText(EditAccountActivity.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

    }
}
