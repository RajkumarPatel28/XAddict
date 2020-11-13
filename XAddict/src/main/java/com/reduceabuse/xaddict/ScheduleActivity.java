package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScheduleActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}