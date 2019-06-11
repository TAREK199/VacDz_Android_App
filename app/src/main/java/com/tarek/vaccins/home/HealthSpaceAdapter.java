package com.tarek.vaccins.home;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.HealthSpace;

import java.util.List;

public class HealthSpaceAdapter extends RecyclerView.Adapter<HealthSpaceAdapter.MyViewHolderHealthSpace> {

    private Context context;
    List<HealthSpace> healthSpaceList;

    public HealthSpaceAdapter(Context context ,List<HealthSpace> healthSpaceList){
        this.context = context;
        this.healthSpaceList = healthSpaceList;
    }

    @NonNull
    @Override
    public MyViewHolderHealthSpace onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_health_space,parent,false);
        return new MyViewHolderHealthSpace(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderHealthSpace holder, int position) {

        holder.title.setText((CharSequence) healthSpaceList.get(position).getTitle());
        holder.imageView.setImageAlpha(healthSpaceList.get(position).getImage());

        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, VaccinInfosActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return healthSpaceList.size();
    }


    public static class MyViewHolderHealthSpace extends RecyclerView.ViewHolder{

        private TextView title;
        private ImageView imageView ;
        private RelativeLayout relative;


        public MyViewHolderHealthSpace(@NonNull View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.txt_health_space);
            imageView = itemView.findViewById(R.id.img_health_space);
            relative = itemView.findViewById(R.id.relative_health_space);

        }
    }


}
