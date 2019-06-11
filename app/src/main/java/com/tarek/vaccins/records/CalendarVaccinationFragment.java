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
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Calendar;
import com.tarek.vaccins.response.CalendarResponse;
import com.tarek.vaccins.service.ChildrenService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CalendarVaccinationFragment extends Fragment {

    private View view ;
    private RecyclerView recyclerView ;
    private List<Calendar> calendarList;
    SharedPrefManager sharedPrefManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar_vaccination,container,false);

        sharedPrefManager = new SharedPrefManager(getActivity());
        calendarList = new ArrayList<>();
        getCalendar();
        return view;
    }


    public void viewData(){

        recyclerView = view.findViewById(R.id.recycle_calendar_vaccination);

        CalendarVaccinationAdapter calendarVaccinationAdapter = new CalendarVaccinationAdapter(getActivity(), calendarList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(calendarVaccinationAdapter);
    }


    public void getCalendar(){

        String token = sharedPrefManager.getToken();
        int id = sharedPrefManager.getIdChild();

        ChildrenService childrenService = RetrofitInstance.childrenInstance();

        childrenService.getCalendar("Bearer "+token,id).enqueue(new Callback<CalendarResponse>() {
            @Override
            public void onResponse(Call<CalendarResponse> call, Response<CalendarResponse> response) {

                Boolean success = response.body().getSuccess();

                if (success){
                     calendarList = response.body().getData().getCalendrier();
                     viewData();

                }else {
                    Toast.makeText(getActivity(),"No data is available "+success,Toast.LENGTH_LONG).show();
                }


            }
            @Override
            public void onFailure(Call<CalendarResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"problem : "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }


}
