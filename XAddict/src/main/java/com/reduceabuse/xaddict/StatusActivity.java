package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StatusActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}