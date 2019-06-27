package com.tarek.vaccins.records;


import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Vaccin;
import com.tarek.vaccins.model.Vaccination;

import java.util.List;

public class VaccinationAdapter extends RecyclerView.Adapter<VaccinationAdapter.MyViewHolderVaccin> {

    private Context context ;
   // private List<Vaccin> vaccinsList;
    private List<Vaccination> vaccinationsList ;

    public VaccinationAdapter(Context context,List<Vaccination> vaccinations) {
        this.context = context;
      //  this.vaccinsList = vaccinsList;
        this.vaccinationsList = vaccinations;
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
    public void onBindViewHolder(@NonNull final MyViewHolderVaccin holder, final int pos) {

        holder.vaccinName.setText(vaccinationsList.get(pos).getVaccin());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     Toast.makeText(context,"poly "+vaccinationsList.get(pos).getPolycliniqueId()  +
                             "vaccin "+vaccinationsList.get(pos).getVaccin(),Toast.LENGTH_SHORT).show();

                //before inflating the custom alert dialog layout, we will get the current activity viewgroup

                //then we will inflate the custom alert dialog xml that we created
                View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_vaccination,holder.viewGroup ,false);


        //        holder.polyclincName.setText(vaccinationsList.get(pos).getPolycliniqueId());
           //     holder.date.setText(vaccinationsList.get(pos).getVaccin());

                    //              Toast.makeText(context,"succcesssssss",Toast.LENGTH_LONG).show();
                //Now we need an AlertDialog.Builder object
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                //setting the view of the builder to our custom view that we already inflated
                builder.setView(dialogView);

                //finally creating the alert dialog and displaying it
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });


    }

    @Override
    public int getItemCount() {
      //  return vaccinsList.size();
        return  vaccinationsList.size();
    }


    public static class MyViewHolderVaccin extends RecyclerView.ViewHolder{


        private TextView vaccinName ,polyclincName,date ;
        private CardView cardView ;

        ViewGroup viewGroup ;

        public MyViewHolderVaccin(@NonNull View itemView) {
            super(itemView);
            vaccinName = itemView.findViewById(R.id.txt_vaccin_name);
            cardView = itemView.findViewById(R.id.card_vaccination);
            polyclincName = itemView.findViewById(R.id.txt_poly_name_vaccination);
            date = itemView.findViewById(R.id.txt_date_vaccination);

            viewGroup = itemView.findViewById(android.R.id.content);
        }
    }
}
