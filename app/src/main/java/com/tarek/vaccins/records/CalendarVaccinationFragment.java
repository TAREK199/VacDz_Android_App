package com.tarek.vaccins.records;

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
import com.tarek.vaccins.model.CalendarVaccination;

import java.util.ArrayList;
import java.util.List;


public class CalendarVaccinationFragment extends Fragment {

    private View view ;
    private RecyclerView recyclerView ;
    private List<CalendarVaccination> vaccinations ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar_vaccination,container,false);

        viewData();
        return view;
    }



    public void viewData(){

        recyclerView = view.findViewById(R.id.recycle_calendar_vaccination);
        vaccinations = new ArrayList<>();

        Toast.makeText(getActivity(),"in viewDta",Toast.LENGTH_LONG).show();

        vaccinations.add(new CalendarVaccination("Naissance","5 April",1));
        vaccinations.add(new CalendarVaccination("2 mois","5 April",1));
        vaccinations.add(new CalendarVaccination("3 mois","5 April",1));
        vaccinations.add(new CalendarVaccination("5 mois","5 April",1));
        vaccinations.add(new CalendarVaccination("6 mois","5 April",1));
        vaccinations.add(new CalendarVaccination("1 an ","5 April",1));
        vaccinations.add(new CalendarVaccination("18 mois","5 April",1));

        CalendarVaccinationAdapter calendarVaccinationAdapter = new CalendarVaccinationAdapter(getActivity(),vaccinations);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(calendarVaccinationAdapter);
    }
}
