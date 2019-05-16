package com.tarek.vaccins.polyclinic;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tarek.vaccins.R;

import java.util.List;

public class PolyclinicAdapater extends RecyclerView.Adapter<PolyclinicAdapater.MyViewHolderPoly> {


    private Context context ;
    private List<Polyclinic>  mData ;

    public PolyclinicAdapater(Context context, List<Polyclinic> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolderPoly onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view ;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.item_polyclinic,viewGroup,false);


        return new MyViewHolderPoly(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderPoly holder, int pos) {


        holder.polyName.setText(mData.get(pos).getPolyName());
        holder.polyAdr.setText(mData.get(pos).getPolyAdress());

  /*      holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context,PolyclincProfileActivity.class));
            }
        });*/

  holder.btnDetails.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          context.startActivity(new Intent(context,PolyclincProfileActivity.class));
      }
  });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderPoly extends RecyclerView.ViewHolder{


        private TextView polyName, polyAdr ;
        private CardView cardView ;
        private Button btnDetails ;


        public MyViewHolderPoly(@NonNull View itemView) {
            super(itemView);

            polyAdr = itemView.findViewById(R.id.txt_poly_name);
            polyName = itemView.findViewById(R.id.txt_poly_adr);
            cardView  = itemView.findViewById(R.id.card_polyclinic);
            btnDetails = itemView.findViewById(R.id.btn_details_poly);
        }
    }

}
