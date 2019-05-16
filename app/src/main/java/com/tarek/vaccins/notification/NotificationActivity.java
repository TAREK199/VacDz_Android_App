package com.tarek.vaccins.notification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tarek.vaccins.R;
import com.tarek.vaccins.home.HomeActivity;
import com.tarek.vaccins.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private List<Notification> notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

       findViewById(R.id.img_arrow_from_notification).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(NotificationActivity.this, HomeActivity.class));
           }
       });
        viewData();

    }

    public void viewData(){

        recyclerView = findViewById(R.id.recycle_notification);
        notifications = new ArrayList<>();

        Toast.makeText(NotificationActivity.this,"in viewDta",Toast.LENGTH_LONG).show();

        notifications.add(new Notification("2 Mois","cette semaine"));
        notifications.add(new Notification("2 Mois","cette semaine"));
        notifications.add(new Notification("2 Mois","cette semaine"));
        notifications.add(new Notification("2 Mois","cette semaine"));
        notifications.add(new Notification("2 Mois","cette semaine"));
        notifications.add(new Notification("2 Mois","cette semaine"));
        notifications.add(new Notification("2 Mois","cette semaine"));
        notifications.add(new Notification("2 Mois","cette semaine"));
        notifications.add(new Notification("2 Mois","cette semaine"));






        NotificationAdapter notificationAdapter = new NotificationAdapter(NotificationActivity.this,notifications);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(NotificationActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(notificationAdapter);
    }

}
