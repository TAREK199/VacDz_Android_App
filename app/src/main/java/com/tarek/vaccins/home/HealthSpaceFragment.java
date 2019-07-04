package com.tarek.vaccins.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.HealthSpace;
import com.tarek.vaccins.response.HealthSpaceResponse;
import com.tarek.vaccins.service.FatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HealthSpaceFragment extends Fragment {

    private View view ;
    private RecyclerView recyclerView ;
    private List<HealthSpace> healthspaces ;
    private CarouselView carouselView ;
    int[] sampleImage = {R.drawable.img10, R.drawable.img12, R.drawable.img13, R.drawable.img14, R.drawable.img15};

    SharedPrefManager sharedPrefManager ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_health_space,container,false);


        carouselView = view.findViewById(R.id.carousselView_home);

        carouselView.setPageCount(sampleImage.length);
        carouselView.setImageListener(imageListener);

        sharedPrefManager = new SharedPrefManager(getActivity());

        getHealthSpace();

       // viewData();
        return view;

    }


    private void viewData() {
        recyclerView = view.findViewById(R.id.recycle_health_space);
/*
        healthAdvices.add(new HealthSpace("VACCIN",R.drawable.syringe));
        healthAdvices.add(new HealthSpace("ROR",R.drawable.syringe));
        healthAdvices.add(new HealthSpace("DTC",R.drawable.syringe));
        healthAdvices.add(new HealthSpace("VPO",R.drawable.syringe));
        healthAdvices.add(new HealthSpace("BCF",R.drawable.syringe));
        healthAdvices.add(new HealthSpace("HVB",R.drawable.syringe));
        healthAdvices.add(new HealthSpace("PNEUMO",R.drawable.syringe));
        healthAdvices.add(new HealthSpace("HIB",R.drawable.syringe));

*/
        HealthSpaceAdapter healthSpaceAdapter = new HealthSpaceAdapter(getActivity(),healthspaces);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(healthSpaceAdapter);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImage[position]);
        }
    };


    public void getHealthSpace(){

        String token = sharedPrefManager.getToken() ;

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.getHealthSpace("Bearer "+token).enqueue(new Callback<HealthSpaceResponse>() {
            @Override
            public void onResponse(Call<HealthSpaceResponse> call, Response<HealthSpaceResponse> response) {


                Boolean success = response.body().getSuccess() ;
                healthspaces = new ArrayList();

                if (success){
                    healthspaces = response.body().getData() ;
                    viewData();
                }else {
                    Toast.makeText(getActivity(),"no data is available",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<HealthSpaceResponse> call, Throwable t) {
               //    Toast.makeText(getActivity(),"problem : "+t.getMessage(),Toast.LENGTH_LONG).show();

                if (t instanceof IOException) {
                    Toast.makeText(getActivity(), "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(getActivity(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }

}
