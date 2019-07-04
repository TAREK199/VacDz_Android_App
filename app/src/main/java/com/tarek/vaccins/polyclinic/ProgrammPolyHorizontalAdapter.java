package com.tarek.vaccins.polyclinic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Programme;


import java.util.List;

public class ProgrammPolyHorizontalAdapter extends RecyclerView.Adapter<ProgrammPolyHorizontalAdapter.ViewHolderProgramme> {

    private Context context;
    private List<Programme> programmes;

    SharedPrefManager sharedPrefManager ;

    public ProgrammPolyHorizontalAdapter(Context context, List<Programme> programmes) {
        this.context = context;
        this.programmes = programmes;
        sharedPrefManager = new SharedPrefManager(context);
    }

    @NonNull
    @Override
    public ViewHolderProgramme onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderProgramme(LayoutInflater.from(context).inflate(R.layout.item_programm, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProgramme holder, int position) {

        int j = programmes.get(position).getJour() ;
        if(j==2){
            holder.jour.setText("Dimanche");
        }else if(j==3){
            holder.jour.setText("Lundi");
        }else if(j==4){
            holder.jour.setText("Mardi");
        }else if(j==5){
            holder.jour.setText("Mercredi");
        }else if(j==6){
            holder.jour.setText("Jeudi");
        }
        holder.acte.setText(programmes.get(position).getValue() + " ");
    }



    @Override
    public int getItemCount() {
        return programmes.size();
    }

    public class ViewHolderProgramme extends RecyclerView.ViewHolder {


        private TextView jour ;
        private TextView acte ;

        public ViewHolderProgramme(View view) {
            super(view);

            jour = view.findViewById(R.id.txt_jour_programme);
            acte = view.findViewById(R.id.txt_acte_programm);

        }
    }
}
