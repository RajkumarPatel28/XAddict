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
        setContentView(R.layout.activity_home_page);
        Objects.requireNonNull(getSupportActionBar()).hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.btnDrugs:
                        Intent intent1 = new Intent(HomepageActivity.this, DrugsListActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.btnSchedule:
                        Intent intent2 = new Intent(HomepageActivity.this, PersonalNotesActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.btnStatus:
                        Intent intent3 = new Intent(HomepageActivity.this, PersonalNotesActivity.class);
                        startActivity(intent3);
                        break;


                    case R.id.btnSettings:
                        Intent intent4 = new Intent(HomepageActivity.this, SettingsActivity.class);
                        startActivity(intent4);
                        break;
                }
                return true;
            }

        });
    }
}