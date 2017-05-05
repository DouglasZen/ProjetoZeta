package com.app.tarsus.projetozetaapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;

public class SplashScreemActivity extends AppCompatActivity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screem);

        image = (ImageView) findViewById(R.id.imageCirculo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // COLOCAR A VERIFICACAO DE USUÁRIO LOGADO AQUI




                Animation animation = AnimationUtils.loadAnimation(SplashScreemActivity.this, R.anim.zoom_circle);
                image.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent(SplashScreemActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                }, 150);
                //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        }, 2000);
    }
}
