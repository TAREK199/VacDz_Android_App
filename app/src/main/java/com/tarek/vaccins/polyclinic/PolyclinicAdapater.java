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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Polyclinic;

import java.util.ArrayList;
import java.util.List;

public class PolyclinicAdapater extends RecyclerView.Adapter<PolyclinicAdapater.MyViewHolderPoly> implements Filterable {


    private Context context ;
    private List<Polyclinic>  mData ,filterPoly,polyListFiltered;

    CustomFilter filter ;




    public PolyclinicAdapater(Context context, List<Polyclinic> mData) {
        this.context = context;
        this.mData = mData;
    //    this.filterPoly = mData;
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
    public void onBindViewHolder(@NonNull MyViewHolderPoly holder, final int pos) {


        holder.polyName.setText(mData.get(pos).getPolyName());
        holder.polyAdr.setText(mData.get(pos).getPolyAdress());
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent myIntent = new Intent(context, PolyclincProfileActivity.class);
          myIntent.putExtra("poly_id", mData.get(pos).getId());
          context.startActivity(myIntent);
      }
  });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new CustomFilter();
        }
        return filter;
    }




    public static class MyViewHolderPoly extends RecyclerView.ViewHolder{


        private TextView polyName, polyAdr,polyId ;
        private Button btnDetails ;


        public MyViewHolderPoly(@NonNull View itemView) {
            super(itemView);

            polyAdr = itemView.findViewById(R.id.txt_poly_name);
            polyName = itemView.findViewById(R.id.txt_poly_adr);
            polyId = itemView.findViewById(R.id.txt_poly_id);
            btnDetails = itemView.findViewById(R.id.btn_details_poly);
        }
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults() ;

            if( constraint != null && constraint.length() > 0)
            {
                //upper constraint
                constraint = constraint.toString().toUpperCase();

                // declare a new array
                ArrayList<Polyclinic> filters = new ArrayList<>();

                // get the result
                for (int i = 0; i< filters.size(); i++)
                {
                    if (filters.get(i).getPolyAdress().toUpperCase().contains(constraint)){

                        Polyclinic polyclinic = new Polyclinic(filterPoly.get(i).getPolyName());

                        filters.add(polyclinic);
                    }
                }
                results.count = filters.size();
                results.values = filters ;
            }
            else
            {
                results.count = filterPoly.size();
                results.values = filterPoly;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData = (ArrayList<Polyclinic>) results.values;
            notifyDataSetChanged(); ; // refrech our grid view
        }


    }

}
