package com.tarek.vaccins.records;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Vaccin;
import com.tarek.vaccins.model.Vaccination;
import com.tarek.vaccins.response.VaccinationResponse;

import java.util.List;

public class VaccinationAdapter extends RecyclerView.Adapter<VaccinationAdapter.MyViewHolderVaccin> {

    private Context context ;
    private List<Vaccin> mData ;
    private List<Vaccination> vaccinations ;

    public VaccinationAdapter(Context context, List<Vaccin> mData,List<Vaccination> vaccinations) {
        this.context = context;
        this.mData = mData;
        this.vaccinations = vaccinations;
    }

    @NonNull
    @Override
    public MyViewHolderVaccin onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {

        View view ;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.item_vaccin,viewGroup,false);


        return new MyViewHolderVaccin(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderVaccin holder, final int pos) {

        holder.vaccinName.setText(mData.get(pos).getVaccin());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,vaccinations.get(pos).getDateVaccination(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderVaccin extends RecyclerView.ViewHolder{


        private TextView vaccinName ;
        private CardView cardView ;

        public MyViewHolderVaccin(@NonNull View itemView) {
            super(itemView);
            vaccinName = itemView.findViewById(R.id.txt_vaccin_name);
            cardView = itemView.findViewById(R.id.card_vaccination);
        }
    }
}
