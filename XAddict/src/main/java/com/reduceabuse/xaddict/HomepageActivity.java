package com.reduceabuse.xaddict;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse(getString(R.string.homepage_telephone)));
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnBar);
        bottomNavigationView.getMenu().setGroupCheckable(0, false, true);
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

    @Override
    public void onBackPressed() {
        Toast.makeText(HomepageActivity.this, R.string.homepage_message, Toast.LENGTH_SHORT).show();
    }
}