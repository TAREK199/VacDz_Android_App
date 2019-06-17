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
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Children;
import com.tarek.vaccins.response.ChildrenResponse;
import com.tarek.vaccins.service.FatherService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildrensFragment extends Fragment {

    private View view;
    private List<Children> childrens ;
    private RecyclerView recyclerView ;
    SharedPrefManager sharedPrefManager ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_childrens,container,false);



        sharedPrefManager = new SharedPrefManager(getActivity());
         getChildrens();
        return view;
    }


    public void viewData(){

        recyclerView = view.findViewById(R.id.recycle_childrens);

        ChildrensAdapter childrensAdapter = new ChildrensAdapter(getActivity(),childrens);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(childrensAdapter);
    }


    public  void  getChildrens() {

        String token =  sharedPrefManager.getToken();
        int id = sharedPrefManager.getIdFather();



        FatherService fatherService = RetrofitInstance.fatherInstance();
        fatherService.getChildrensById("Bearer "+token,id).enqueue(new Callback<ChildrenResponse>() {
            @Override
            public void onResponse(Call<ChildrenResponse> call, Response<ChildrenResponse> response) {


                Boolean success = response.body().getSuccess();


                if(success){

                    childrens = response.body().getData().getEnfants();
                    viewData();

                }else{
                    Toast.makeText(getActivity(),"no result : "+success,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ChildrenResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"problem : "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }




}
