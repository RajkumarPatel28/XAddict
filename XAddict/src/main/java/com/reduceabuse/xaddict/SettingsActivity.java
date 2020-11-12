package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ViewFlipper;

public class SettingsActivity extends AppCompatActivity {

    ViewFlipper settingsVf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Setting");

        settingsVf = findViewById(R.id.settingsVf);
        settingsVf.setDisplayedChild(0);
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
            case R.id.account:
                setTitle(getString(R.string.account_title));
                settingsVf.setDisplayedChild(1);
                return true;
            case R.id.privacy:
                setTitle(getString(R.string.privacy_title));
                settingsVf.setDisplayedChild(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}