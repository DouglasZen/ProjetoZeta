package com.app.tarsus.projetozetaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.facebook.AccessToken;

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
                if(AccessToken.getCurrentAccessToken() == null) {
                    Intent i = new Intent(SplashScreemActivity.this, LoginActivity.class);
                    ActivityOptionsCompat opts = ActivityOptionsCompat.makeCustomAnimation(SplashScreemActivity.this, R.anim.fade_in, R.anim.fade_out);
                    ActivityCompat.startActivity(SplashScreemActivity.this, i, opts.toBundle());

                }else{
                    Intent i = new Intent(SplashScreemActivity.this, MainActivity.class);
                    ActivityOptionsCompat opts = ActivityOptionsCompat.makeCustomAnimation(SplashScreemActivity.this, R.anim.fade_in, R.anim.fade_out);
                    ActivityCompat.startActivity(SplashScreemActivity.this, i, opts.toBundle());
                }

            }
        }, 2000);
    }
}
