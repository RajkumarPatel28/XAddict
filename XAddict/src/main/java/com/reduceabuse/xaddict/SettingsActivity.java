package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ViewFlipper;

public class SettingsActivity extends AppCompatActivity {

    ViewFlipper vfSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Setting");

        vfSettings = findViewById(R.id.vfSettings);
        vfSettings.setDisplayedChild(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settingsoverflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                setTitle(getString(R.string.profile_title));
                vfSettings.setDisplayedChild(1);
                return true;
            case R.id.theme:
                setTitle(getString(R.string.theme_title));
                vfSettings.setDisplayedChild(2);
                return true;
            case R.id.aboutUs:
                setTitle(getString(R.string.aboutus_title));
                vfSettings.setDisplayedChild(3);
                return true;
            case R.id.appFeedback:
                setTitle(getString(R.string.appfeedback_title));
                vfSettings.setDisplayedChild(4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}