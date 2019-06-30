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

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Calendar;

import java.util.List;

public class CalendarVaccinationAdapter extends RecyclerView.Adapter<CalendarVaccinationAdapter.MyViewHolderCalendarVaccination>{


    private Context context ;
    List<Calendar> mData ;


    public CalendarVaccinationAdapter(Context context, List<Calendar> mData) {
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
    public void onBindViewHolder(@NonNull MyViewHolderCalendarVaccination holder, final int pos) {


        if (mData.get(pos).getAge()==0){
            holder.vaccinationTime.setText("Naissance");
        }else
          holder.vaccinationTime.setText(Integer.toString(mData.get(pos).getAge())+" MOIS"  );



          if(mData.get(pos).getValue()==1){
              holder.vaccinationstate.setImageResource(R.drawable.green_button);

          }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                         Intent  intent = new Intent(context, VaccinationActivity.class);
                         intent.putExtra("age",mData.get(pos).getAge());
                         intent.putExtra("state",mData.get(pos).getValue());

                    context.startActivity(intent);
                }
            });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderCalendarVaccination extends RecyclerView.ViewHolder{

        private TextView vaccinationTime ;
        private ImageView vaccinationstate ;
        private CardView cardView ;


        public MyViewHolderCalendarVaccination(@NonNull View itemView) {
            super(itemView);

            vaccinationTime = itemView.findViewById(R.id.txt_calendar_vaccination_time_name);
            vaccinationstate = itemView.findViewById(R.id.imgview_vaccination_state);
            cardView = itemView.findViewById(R.id.card_calendar_vaccination);
        }
    }
}
