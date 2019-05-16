package com.tarek.vaccins.records;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Vaccin;

import java.util.List;

public class VaccinAdapter extends RecyclerView.Adapter<VaccinAdapter.MyViewHolderVaccin> {

    private Context context ;
    private List<Vaccin> mData ;

    public VaccinAdapter(Context context, List<Vaccin> mData) {
        this.context = context;
        this.mData = mData;
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
    public void onBindViewHolder(@NonNull MyViewHolderVaccin holder, int pos) {

        holder.vaccinName.setText(mData.get(pos).getVaccinName());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderVaccin extends RecyclerView.ViewHolder{


        private TextView vaccinName ;
        public MyViewHolderVaccin(@NonNull View itemView) {
            super(itemView);

            vaccinName = itemView.findViewById(R.id.txt_vaccin_name);
        }
    }
}
