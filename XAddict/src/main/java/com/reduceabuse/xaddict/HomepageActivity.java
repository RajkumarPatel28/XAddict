package com.reduceabuse.xaddict;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Objects.requireNonNull(getSupportActionBar()).hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.btnDrugs:
                        intent = new Intent(HomepageActivity.this, DrugsListActivity.class);
                        break;
                    case R.id.btnSchedule:
                        intent = new Intent(HomepageActivity.this, ScheduleActivity.class);
                        break;
                    case R.id.btnStatus:
                        intent = new Intent(HomepageActivity.this, StatusActivity.class);
                        break;
                    default:
                        intent = new Intent(HomepageActivity.this, SettingsActivity.class);
                        break;
                }
                startActivity(intent);
                return true;
            }
        });
    }
}