package com.example.anmolnagvanshi.incredible11;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;

import java.util.Objects;

public class FrontScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        Objects.requireNonNull(getSupportActionBar()).hide();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

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
