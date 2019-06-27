package com.tarek.vaccins.notification;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.model.Notification;

import java.util.List;

public class RdvAdapter extends RecyclerView.Adapter<RdvAdapter.MyViewHolderNotification> {


    private Context context ;
    private List<Notification> mData ;

    public RdvAdapter(Context context, List<Notification> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolderNotification onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view ;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.item_notification,viewGroup,false);


        return new MyViewHolderNotification(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNotification holder, int pos) {


        holder.notificationActe.setText(mData.get(pos).getNotificationActe());
        holder.notificationMsg.setText(mData.get(pos).getNotificationMsg());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    public static class MyViewHolderNotification extends RecyclerView.ViewHolder{


        private TextView notificationActe, notificationMsg ;
        private CardView cardView ;

        public MyViewHolderNotification(@NonNull View itemView) {
            super(itemView);

            notificationActe = itemView.findViewById(R.id.txt_notification_acte);
            notificationMsg = itemView.findViewById(R.id.txt_notification_msg_vaccin);
         //   cardView  = itemView.findViewById(R.id.card_polyclinic);
        }
    }


}
