package com.tarek.vaccins.home;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.RDV;

import java.util.List;

public class RDVAdapter extends RecyclerView.Adapter<RDVAdapter.MyViewHolderRDV> {


    private Context context;
    List<RDV> rdvList;

    public RDVAdapter(Context context, List<RDV> rdvList) {
        this.context = context;
        this.rdvList = rdvList;
    }

    @NonNull
    @Override
    public MyViewHolderRDV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_rdv, parent, false);
        return new MyViewHolderRDV(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderRDV holder, int position) {

        holder.rdvDate.setText(rdvList.get(position).getRdvDate());
        holder.childName.setText(rdvList.get(position).getChildName());
        holder.acte.setText(rdvList.get(position).getActe());
        holder.polyName.setText(rdvList.get(position).getPolyName());
        holder.polyAdr.setText(rdvList.get(position).getPolyAdr());

    }


    @Override
    public int getItemCount() {
        return rdvList.size();
    }

    public static class MyViewHolderRDV extends RecyclerView.ViewHolder {

        private TextView rdvDate, childName, acte, polyName, polyAdr;
        private CardView cardView;


        public MyViewHolderRDV(@NonNull View itemView) {

            super(itemView);

            rdvDate = itemView.findViewById(R.id.txt_rdv_date);
            childName = itemView.findViewById(R.id.txt_child_name_rdv);
            acte = itemView.findViewById(R.id.txt_child_acte_rdv);
            polyName = itemView.findViewById(R.id.txt_poly_name_rdv);
            polyAdr = itemView.findViewById(R.id.txt_poly_adr_rdv);

        }
    }

}

