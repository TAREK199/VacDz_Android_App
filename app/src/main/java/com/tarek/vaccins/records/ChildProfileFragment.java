package com.tarek.vaccins.records;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.response.ChildrenProfileResponse;
import com.tarek.vaccins.service.ChildrenService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildProfileFragment extends Fragment {


    private View view ;
    SharedPrefManager sharedPrefManager ;
    private TextView nameView ,birthdayView,birthPlaceView,sexeView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_child_profile,container,false);

        sharedPrefManager = new SharedPrefManager(getActivity());


        nameView = view.findViewById(R.id.txt_children_name_profile);
        birthdayView = view.findViewById(R.id.txt_birth_date_children_profile);
        birthPlaceView = view.findViewById(R.id.txt_birth_place_children_profile);
        sexeView = view.findViewById(R.id.txt_sexe_children_profile);

        getChildrenInfo();
        return view;
    }


    public void getChildrenInfo(){

        String token = sharedPrefManager.getToken();
        int id = sharedPrefManager.getIdChild();
        ChildrenService childrenService = RetrofitInstance.childrenInstance();

        childrenService.getChildrenEnfant("Bearer "+token,id).enqueue(new Callback<ChildrenProfileResponse>() {
            @Override
            public void onResponse(Call<ChildrenProfileResponse> call, Response<ChildrenProfileResponse> response) {

                Boolean success = response.body().getSuccess();




                if (success) {
                    String firstName = response.body().getData().getEnfant().getNom();
                    String lastName = response.body().getData().getEnfant().getPrenom();
                    String birthday = response.body().getData().getEnfant().getDateNaissance();
                    String birthPlace = response.body().getData().getEnfant().getLieuNaissance();
                    int sexe = response.body().getData().getEnfant().getSex();

                    nameView.setText(firstName+" "+lastName);
                    birthdayView.setText(birthday);
                    birthPlaceView.setText(birthPlace);

                    if(sexe==1){
                        sexeView.setText("Masculin");
                    }else {
                        sexeView.setText("Femminin");
                    }



                }
                else
                    Toast.makeText(getActivity(),"success : "+success,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ChildrenProfileResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"problem : "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }
}
