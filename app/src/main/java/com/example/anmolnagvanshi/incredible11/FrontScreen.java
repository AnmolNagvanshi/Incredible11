package com.example.anmolnagvanshi.incredible11;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class FrontScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }

    public void letsPlay(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void userLogIn(View view) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

}
