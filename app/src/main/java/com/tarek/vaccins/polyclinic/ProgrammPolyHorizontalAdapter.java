package com.tarek.vaccins.polyclinic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Programme;


import java.util.List;

public class ProgrammPolyHorizontalAdapter extends RecyclerView.Adapter<ProgrammPolyHorizontalAdapter.ViewHolderProgramme> {

    private Context context;
    private List<Programme> programmes;

    public ProgrammPolyHorizontalAdapter(Context context, List<Programme> programmes) {
        this.context = context;
        this.programmes = programmes;
    }

    @NonNull
    @Override
    public ViewHolderProgramme onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderProgramme(LayoutInflater.from(context).inflate(R.layout.item_programm, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProgramme holder, int position) {
        holder.jour.setText(programmes.get(position).getJour());
        holder.acte.setText(programmes.get(position).getActe());
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
