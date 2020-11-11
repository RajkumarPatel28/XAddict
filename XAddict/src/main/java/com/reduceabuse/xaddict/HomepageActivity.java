package com.reduceabuse.xaddict;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomepageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.homeBtn:
                        break;

                    case R.id.drugBtn:
                        Intent intent2 = new Intent(HomepageActivity.this, DrugsListActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.notesBtn:
                        Intent intent3 = new Intent(HomepageActivity.this, PersonalNotesActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.settingsBtn:
                        Intent intent4 = new Intent(HomepageActivity.this, SettingsActivity.class);
                        startActivity(intent4);
                        break;
                }
                return true;
            }

        });


    }




    }

