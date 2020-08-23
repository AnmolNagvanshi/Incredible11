package com.example.anmolnagvanshi.incredible11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

public class LogIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }
}
