package com.android.up;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class Animation extends AppCompatActivity {
    private Button letsgo;
    android.view.animation.Animation fromleft;
    ImageView photo;
    String j;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery);
        photo = (ImageView) findViewById(R.id.photo_delivery);
        fromleft = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
        photo.setAnimation(fromleft);
        final android.view.animation.Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slideup);
        letsgo = findViewById(R.id.start_btn_delivery);
        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                letsgo.setVisibility(View.VISIBLE);
                                letsgo.setAnimation(slideUp);
                            }
                        });
                    }
                },
                2000
        );
        letsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToLogin();
            }
        });
    }


    public void GoToLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
