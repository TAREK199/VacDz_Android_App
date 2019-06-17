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
import com.tarek.vaccins.model.Rdv;
import com.tarek.vaccins.response.RdvResponse;
import com.tarek.vaccins.service.FatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentRdv extends Fragment {

    private View view ;
    private RecyclerView recyclerView ;
    private List<Rdv> rdvList ;

    SharedPrefManager sharedPrefManager ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_rdv,container,false);

        sharedPrefManager = new SharedPrefManager(getActivity());

        getRdvs();

     //   viewData();

        final ChildrensFragment childrensFragment = new ChildrensFragment();

        view.findViewById(R.id.btn_from_fragment_rdv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.relative_rdv_fragment, childrensFragment).commit();
            }
        });
        return view;

    }


    public void viewData(){

        recyclerView = view.findViewById(R.id.recycle_rdv);
    //    rdvList = new ArrayList<Rdv>();
/*
        rdvList.add(new Rdv("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));
        rdvList.add(new Rdv("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));
        rdvList.add(new Rdv("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));
        rdvList.add(new Rdv("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));
        rdvList.add(new Rdv("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));

*/

        RdvAdapter rdvAdapter = new RdvAdapter(getActivity(),rdvList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rdvAdapter);
    }


    public void getRdvs(){

        String token = sharedPrefManager.getToken();
        int id = sharedPrefManager.getIdFather();

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.getRdvs("Bearer "+token,id).enqueue(new Callback<RdvResponse>() {
            @Override
            public void onResponse(Call<RdvResponse> call, Response<RdvResponse> response) {

                Boolean success = response.body().getSuccess() ;


                Toast.makeText(getActivity(),"result : "+success,Toast.LENGTH_LONG).show();



                rdvList = new ArrayList<Rdv>();

                for (int i = 0; i<response.body().getData().size();i++){

                    rdvList.add(new Rdv(  response.body().getData().get(i).getId(),
                                          response.body().getData().get(i).getDateRdv(),
                                          response.body().getData().get(i).getEnfant(),
                                          response.body().getData().get(i).getPolyclinique()      )) ;
                }

                viewData();

              //  rdvList =response.body().getData();

/*
                for (int i=0;i<response.body().getData().size();i++){

                    String rdvDate = response.body().getData().get(i).getDateRdv();
                    String child = response.body().getData().get(i).getEnfant();
                    String poly = response.body().getData().get(i).getPolyclinique();


                    rdvList.add(new Rdv(rdvDate,child,poly));
                }
                */
            }

            @Override
            public void onFailure(Call<RdvResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"problem : "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }


}
