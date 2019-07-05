package com.tarek.vaccins.home;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Rdv;
import com.tarek.vaccins.response.AnnulerRdvResponse;
import com.tarek.vaccins.response.RdvResponse;
import com.tarek.vaccins.service.FatherService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RdvAdapter extends RecyclerView.Adapter<RdvAdapter.MyViewHolderRDV> {


    private Context context;
    List<Rdv> rdvList;
    SharedPrefManager sharedPrefManager ;
    private int rdvId ;

    public RdvAdapter(Context context, List<Rdv> rdvList) {
        this.context = context;
        this.rdvList = rdvList;

        sharedPrefManager = new SharedPrefManager(context);
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

        holder.rdvDate.setText(rdvList.get(position).getDateRdv());
        holder.childName.setText(rdvList.get(position).getEnfant());
        holder.polyName.setText(rdvList.get(position).getPolyclinique());


        rdvId = rdvList.get(position).getId();

        holder.annulerRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        context, R.style.AlertDialogTheme);

                // Setting Dialog Title
                alertDialog2.setTitle("Annuler RDV");

// Setting Dialog Message
                alertDialog2.setMessage("vous etes sur d'annuler cet RDV ?");

// Setting Icon to Dialog
                alertDialog2.setIcon(android.R.drawable.ic_dialog_alert);

// Setting Positive "Yes" Btn
                alertDialog2.setPositiveButton("OUI",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                annulerRdv();
                            }
                        });

// Setting Negative "NO" Btn
                alertDialog2.setNegativeButton("NON",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog

                                dialog.cancel();
                            }
                        });

// Showing Alert Dialog
                alertDialog2.show();


            }
        });

    }

    public void annulerRdv(){

        String token = sharedPrefManager.getToken() ;

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.annulerRdvs("Bearer "+token,rdvId).enqueue(new Callback<AnnulerRdvResponse>() {
            @Override
            public void onResponse(Call<AnnulerRdvResponse> call, Response<AnnulerRdvResponse> response) {

                Boolean success = response.body().getSuccess() ;

                Toast.makeText(context,"votre rdv est annuler ",Toast.LENGTH_LONG).show();

                context.startActivity(new Intent(context,HomeActivity.class));
            }

            @Override
            public void onFailure(Call<AnnulerRdvResponse> call, Throwable t) {
                Toast.makeText(context,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return rdvList.size();
    }

    public static class MyViewHolderRDV extends RecyclerView.ViewHolder {

        private TextView rdvDate, childName, acte, polyName, polyAdr;
        private CardView cardView;
        private Button annulerRdv ;


        public MyViewHolderRDV(@NonNull View itemView) {

            super(itemView);

            rdvDate = itemView.findViewById(R.id.txt_rdv_date);
            childName = itemView.findViewById(R.id.txt_child_name_rdv);
            acte = itemView.findViewById(R.id.txt_child_acte_rdv);
            polyName = itemView.findViewById(R.id.txt_poly_name_rdv);
            annulerRdv = itemView.findViewById(R.id.btn_annuler_rdv);

        }
    }

}

