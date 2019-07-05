package com.tarek.vaccins.register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Wilaya;
import com.tarek.vaccins.response.CommuneResponse;
import com.tarek.vaccins.service.FatherService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WilayaAdapterRegister extends RecyclerView.Adapter<WilayaAdapterRegister.MyViewHolderWilayaRegister>{

    private Context context ;
    List<Wilaya> mData ;

    SharedPrefManager sharedPrefManager ;
    ArrayAdapter<String> arrayAdapter ;

    int wilaya_id ;

    public WilayaAdapterRegister(Context context, List<Wilaya> mData) {
        this.context = context;
        this.mData = mData;

        sharedPrefManager = new SharedPrefManager(context);
    }

    @NonNull
    @Override
    public MyViewHolderWilayaRegister onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view ;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.item_wilaya,viewGroup,false);


        return new MyViewHolderWilayaRegister(view);    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolderWilayaRegister holder, final int pos) {

        holder.wilayaNumber.setText(String.valueOf(mData.get(pos).getId()));
        holder.wilayaName.setText(mData.get(pos).getNom());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wilaya_id = mData.get(pos).getId();

                showCommune();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void getCommune(){

        FatherService fatherService = RetrofitInstance.fatherInstance();

        fatherService.getCommunesRegister(wilaya_id).enqueue(new Callback<CommuneResponse>() {
            @Override
            public void onResponse(Call<CommuneResponse> call, Response<CommuneResponse> response) {

                Boolean success = response.body().getSuccess();


                if (success) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        arrayAdapter.add(response.body().getData().get(i).getNom());
                    }
                }else {
                    Toast.makeText(context,"no commune to display ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CommuneResponse> call, Throwable t) {

                Toast.makeText(context,"problem "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void showCommune(){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context,R.style.AlertDialogTheme);
        //      builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Selectionner une commune :");

        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice);
        getCommune();
        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String communeName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(context,R.style.AlertDialogTheme);
                builderInner.setMessage(communeName);
                builderInner.setTitle("Commune selectionnée");
                sharedPrefManager.storeCommune(communeName);

                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();

    }



    public static class MyViewHolderWilayaRegister extends RecyclerView.ViewHolder{

        private TextView wilayaNumber, wilayaName;
        private RelativeLayout relativeLayout ;

        public MyViewHolderWilayaRegister(@NonNull View itemView) {
            super(itemView);
            wilayaNumber = itemView.findViewById(R.id.txt_wilaya_number);
            wilayaName = itemView.findViewById(R.id.txt_wilaya_name);
            relativeLayout = itemView.findViewById(R.id.relative_wilaya_item);
        }
    }

}
