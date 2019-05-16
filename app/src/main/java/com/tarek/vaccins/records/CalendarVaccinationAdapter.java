package com.tarek.vaccins.records;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.CalendarVaccination;

import java.util.List;

public class CalendarVaccinationAdapter extends RecyclerView.Adapter<CalendarVaccinationAdapter.MyViewHolderCalendarVaccination>{


    private Context context ;
    List<CalendarVaccination> mData ;


    public CalendarVaccinationAdapter(Context context, List<CalendarVaccination> mData) {
        this.context = context;
        this.mData = mData;
    }



    @NonNull
    @Override
    public MyViewHolderCalendarVaccination onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view ;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.item_calendar,viewGroup,false);


        return new MyViewHolderCalendarVaccination(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCalendarVaccination holder, int pos) {

        holder.vaccinationTime.setText(mData.get(pos).getVaccinationTime());
        holder.vaccinationDate.setText(mData.get(pos).getVaciinationDate());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"so beaut",Toast.LENGTH_LONG).show();

                context.startActivity(new Intent(context,VaccinActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderCalendarVaccination extends RecyclerView.ViewHolder{

        private TextView vaccinationTime ;
        private TextView vaccinationDate ;
        private ImageView vaccinationstate ;
        private CardView cardView ;


        public MyViewHolderCalendarVaccination(@NonNull View itemView) {
            super(itemView);

            vaccinationTime = itemView.findViewById(R.id.txt_calendar_vaccination_time_name);
            vaccinationDate = itemView.findViewById(R.id.txt_calendar_vaccination_date);
            vaccinationstate = itemView.findViewById(R.id.imgview_vaccination_state);
            cardView = itemView.findViewById(R.id.card_calendar_vaccination);
        }
    }
}
