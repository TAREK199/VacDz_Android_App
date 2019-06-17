package com.tarek.vaccins.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.edit.EditFatherProfile;
import com.tarek.vaccins.response.FatherProfileResponse;
import com.tarek.vaccins.service.FatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FatherProfileFragment extends Fragment {


    private View view ;

    SharedPrefManager sharedPrefManager ;

    private TextView nameView,adresseView,phoneNumberView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_father_profile,container,false);


        sharedPrefManager =  new SharedPrefManager(getActivity());


        nameView = view.findViewById(R.id.txt_father_name_profile);
        adresseView = view.findViewById(R.id.txt_father_wilaya_profile);
        phoneNumberView = view.findViewById(R.id.txt_father_phone_number);


        view.findViewById(R.id.btn_edit_father_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditFatherProfile.class));
            }
        });

        getFatherProfile();
        return view;

    }

    public void getFatherProfile(){

        String token = sharedPrefManager.getToken();

        FatherService fatherService = RetrofitInstance.fatherInstance();


        fatherService.getFatherProfile("Bearer "+token).enqueue(new Callback<FatherProfileResponse>() {
            @Override
            public void onResponse(Call<FatherProfileResponse> call, Response<FatherProfileResponse> response) {

                Boolean success = response.body().getSuccess();

                if (success){
                    String firstName = response.body().getData().getUser().getName();
                    String lastName = response.body().getData().getPere().getPrenom();
                    String phoneNumber = response.body().getData().getPere().getTel1()        ;
                    String adr = response.body().getData().getPere().getWilaya();

                    nameView.setText(firstName + " "+lastName);
                    phoneNumberView.setText(phoneNumber);
                    adresseView.setText(adr);


                }
                else {
                    Toast.makeText(getActivity(),"no data to display "+success,Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<FatherProfileResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"problem "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
