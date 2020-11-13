package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class MarijuanaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marijuana);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }
}