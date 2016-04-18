package br.edu.ifpb.Classes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ed.com.evento.meuappevento.R;


public class SplashActivity extends AppCompatActivity implements Runnable{

    private static final long Tempo_limite= 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        Handler SplashScreen = new Handler();
        SplashScreen.postDelayed( this, Tempo_limite);
    }

    public void run() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
