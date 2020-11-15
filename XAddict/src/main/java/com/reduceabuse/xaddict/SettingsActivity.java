package com.reduceabuse.xaddict;

import androidx.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class SettingsActivity extends AppCompatActivity {

    ViewFlipper vfSettings;
    EditText etFullName, etPhoneNumber;
    Button btnProfileSubmit, btnLogout;
    String fullName, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(getString(R.string.settings_title));

        vfSettings = findViewById(R.id.vfSettings);
        vfSettings.setDisplayedChild(0);

        etFullName = findViewById(R.id.etFullName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnProfileSubmit = findViewById(R.id.btnProfileSubmit);
        btnLogout = findViewById(R.id.btnLogout);

        SharedPreferences saveData = PreferenceManager.getDefaultSharedPreferences(this);
        String fullName1 = saveData.getString(getString(R.string.settings_fullnamevalue),"");
        String phoneNumber1 = saveData.getString(getString(R.string.settings_phonenumbervalue),"");
        etFullName.setText(fullName1);
        etPhoneNumber.setText(phoneNumber1);

        btnProfileSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullName = etFullName.getText().toString();
                phoneNumber = etPhoneNumber.getText().toString();

                SharedPreferences saveData = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor editor = saveData.edit();
                editor.putString(getString(R.string.settings_fullnamevalue),fullName);
                editor.putString(getString(R.string.settings_phonenumbervalue),phoneNumber);
                editor.apply();

                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(SettingsActivity.this, getString(R.string.settings_fullnameerror), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SettingsActivity.this, getString(R.string.settings_updatedmessage), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, getString(R.string.settings_logoutmessage), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                setTitle(getString(R.string.settings_updateprofiletitle));
                vfSettings.setDisplayedChild(1);
                return true;
            case R.id.appFeedback:
                setTitle(getString(R.string.settings_feedbacktitle));
                vfSettings.setDisplayedChild(2);
                return true;
            case R.id.aboutUs:
                setTitle(getString(R.string.settings_aboutustitle));
                vfSettings.setDisplayedChild(3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingsActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}