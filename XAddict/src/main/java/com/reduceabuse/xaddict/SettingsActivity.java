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
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    ViewFlipper vfSettings;
    EditText etFullName, etPhoneNumber, etName, etReview;
    Button btnProfileSubmit, btnLogout, btnFeedbackSubmit;
    String fullName, phoneNumber, data, rating, feedback;
    RatingBar rbFeedback;
    FirebaseDatabase database;
    DatabaseReference ref;

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
        etName = findViewById(R.id.etName);
        etReview = findViewById(R.id.etReview);
        rbFeedback = findViewById(R.id.rbFeedback);
        btnFeedbackSubmit = findViewById(R.id.btnFeedbackSubmit);

        SharedPreferences saveData = PreferenceManager.getDefaultSharedPreferences(this);
        String fullName1 = saveData.getString(getString(R.string.settings_fullnamevalue), "");
        String phoneNumber1 = saveData.getString(getString(R.string.settings_phonenumbervalue), "");
        etFullName.setText(fullName1);
        etPhoneNumber.setText(phoneNumber1);

        database = FirebaseDatabase.getInstance();

        btnProfileSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullName = etFullName.getText().toString();
                phoneNumber = etPhoneNumber.getText().toString();

                SharedPreferences saveData = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor editor = saveData.edit();
                editor.putString(getString(R.string.settings_fullnamevalue), fullName);
                editor.putString(getString(R.string.settings_phonenumbervalue), phoneNumber);
                editor.apply();

                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(SettingsActivity.this, getString(R.string.settings_fullnameerror), Toast.LENGTH_SHORT).show();
                } else {
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

        btnFeedbackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = etName.getText().toString();
                ref = database.getReference(getString(R.string.settings_reviewstitle)).child(data);
                rating = String.valueOf(rbFeedback.getRating());
                feedback = etReview.getText().toString();

                ref.child(getString(R.string.settings_ratingtitle)).setValue(rating);
                ref.child(getString(R.string.settings_feedbacktitle)).setValue(feedback);

                if (TextUtils.isEmpty(feedback)) {
                    Toast.makeText(SettingsActivity.this, getString(R.string.settings_feedbackerror), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, getString(R.string.settings_feedbackmessage), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SettingsActivity.this, HomepageActivity.class);
                    startActivity(intent);
                }
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