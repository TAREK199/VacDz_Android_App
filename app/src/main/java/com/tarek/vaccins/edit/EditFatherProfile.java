package com.tarek.vaccins.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.home.HomeActivity;
import com.tarek.vaccins.model.Father;
import com.tarek.vaccins.response.FatherProfileResponse;
import com.tarek.vaccins.service.FatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFatherProfile extends AppCompatActivity {

    private Button saveEdit ;
    private TextWatcher textWatcher ;
    private EditText firstName,lastName,adresse,phoneNumber1,phoneNumber2;
    private TextView identityNumber;
    private String firstNameChar,lastnameChar,adresseChar,phoneNmbr1Char,phoneNbr2Char,identityNumberChar;

    SharedPrefManager sharedPrefManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_father_profile);

        sharedPrefManager = new SharedPrefManager(EditFatherProfile.this);



        firstName = findViewById(R.id.edt_edit_profile_firsttname);
        lastName = findViewById(R.id.edt_edit_profile_lastname);
        adresse = findViewById(R.id.edt_edit_profile_adresse);
        phoneNumber1 = findViewById(R.id.edit_edit_profile_phone1);
        phoneNumber2 = findViewById(R.id.edit_edit_profile_phone2);
        identityNumber = findViewById(R.id.txt_edit_profile_identity);


        saveEdit = findViewById(R.id.btn_save_edit_father_profile);
        saveEdit.setEnabled(false);

     findViewById(R.id.btn_from_edit_father).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditFatherProfile.this, HomeActivity.class));
            }
        });

     saveEdit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             editProfile();
         }
     });


     getProfileInfo();


     textWatcher = new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

              saveEdit.setEnabled(true);
         }

         @Override
         public void afterTextChanged(Editable s) {

         }
     };

     firstName.addTextChangedListener(textWatcher);
     lastName.addTextChangedListener(textWatcher);
     adresse.addTextChangedListener(textWatcher);
     phoneNumber1.addTextChangedListener(textWatcher);
     phoneNumber2.addTextChangedListener(textWatcher);

    }

    public void getProfileInfo(){

         String token = sharedPrefManager.getToken();

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.getFatherProfile("Bearer "+token).enqueue(new Callback<FatherProfileResponse>() {
            @Override
            public void onResponse(Call<FatherProfileResponse> call, Response<FatherProfileResponse> response) {

                Boolean success = response.body().getSuccess();


                if (success){

                    firstName.setText(response.body().getData().getUser().getName());
                    lastName.setText(response.body().getData().getPere().getPrenom());
                    phoneNumber1.setText(response.body().getData().getPere().getTel1());
                    phoneNumber2.setText(response.body().getData().getPere().getTel2());
                    adresse.setText(response.body().getData().getPere().getWilaya());
                    identityNumber.setText(Integer.toString(response.body().getData().getPere().getFatherId()));

                }
                else{
                    Toast.makeText(EditFatherProfile.this,"no data to display ",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<FatherProfileResponse> call, Throwable t) {

                Toast.makeText(EditFatherProfile.this,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public  void editProfile(){

        firstNameChar = firstName.toString().trim();
        lastnameChar = lastName.toString().trim();
        adresseChar = adresse.toString().trim();
        phoneNbr2Char = phoneNumber1.toString().trim();
        phoneNmbr1Char = phoneNumber2.toString().trim();
        identityNumberChar = identityNumber.toString().trim();

        //identity =

        String token = sharedPrefManager.getToken();
        int userId = sharedPrefManager.getUserId();
        String email = sharedPrefManager.getEmail();





        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.editFatherProfile("Bearer "+token,userId,new Father(123,email,firstNameChar,lastnameChar,phoneNmbr1Char,phoneNbr2Char,adresseChar,"45")).enqueue(new Callback<FatherProfileResponse>() {
            @Override
            public void onResponse(Call<FatherProfileResponse> call, Response<FatherProfileResponse> response) {

                Boolean success = response.body().getSuccess() ;


                Toast.makeText(EditFatherProfile.this,"success here is "+success,Toast.LENGTH_LONG).show();

                if (success){

              //      startActivity(new Intent(EditFatherProfile.this, HomeActivity.class));

                }

            }

            @Override
            public void onFailure(Call<FatherProfileResponse> call, Throwable t) {
                Toast.makeText(EditFatherProfile.this,"problm "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }


}
