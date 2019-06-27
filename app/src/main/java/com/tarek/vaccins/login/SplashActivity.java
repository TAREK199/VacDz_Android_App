package com.tarek.vaccins.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.tarek.vaccins.R;

public class SplashActivity extends AppCompatActivity {


    ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        imageView = findViewById(R.id.splash_image_id);

        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.myanimation);

        imageView.startAnimation(animation);

        final Intent intent = new Intent(SplashActivity.this, LoginActivity.class);

        Thread timer = new Thread(){
            @Override
            public void run() {

                try {
                       sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }
}
