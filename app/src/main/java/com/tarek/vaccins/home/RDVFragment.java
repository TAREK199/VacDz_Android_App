package com.tarek.vaccins.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.RDV;
import com.tarek.vaccins.polyclinic.PolyclinicActivity;

import java.util.ArrayList;
import java.util.List;


public class RDVFragment extends Fragment {

    private View view ;
    private RecyclerView recyclerView ;
    private List<RDV> rdvList ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_rdv,container,false);

        viewData();

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
        rdvList = new ArrayList<>();

        rdvList.add(new RDV("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));
        rdvList.add(new RDV("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));
        rdvList.add(new RDV("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));
        rdvList.add(new RDV("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));
        rdvList.add(new RDV("jeudi, Apr","Rebai Omar","2 MOIS","Annad Ahmed","Fermatou"));



        RDVAdapter rdvAdapter = new RDVAdapter(getActivity(),rdvList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rdvAdapter);
    }
}
