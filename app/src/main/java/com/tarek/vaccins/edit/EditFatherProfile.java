package com.tarek.vaccins.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tarek.vaccins.R;
import com.tarek.vaccins.home.HomeActivity;

public class EditFatherProfile extends AppCompatActivity {

    private Button saveEdit ;
    private TextWatcher textWatcher ;
    private EditText lastName,adresse,phoneNumber1,phoneNumber2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_father_profile);


        saveEdit = findViewById(R.id.btn_save_edit_father_profile);
        lastName = findViewById(R.id.edit_father_lastname);
        adresse = findViewById(R.id.edit_father_adresse);
        phoneNumber1 = findViewById(R.id.edit_father_phone1);
        phoneNumber2 = findViewById(R.id.edit_father_phone2);


        saveEdit.setEnabled(false);

     findViewById(R.id.btn_from_edit_father).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditFatherProfile.this, HomeActivity.class));
            }
        });



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

     lastName.addTextChangedListener(textWatcher);
     adresse.addTextChangedListener(textWatcher);
     phoneNumber1.addTextChangedListener(textWatcher);
     phoneNumber2.addTextChangedListener(textWatcher);



    }
}
