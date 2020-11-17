package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    ViewFlipper vfSettings;
    EditText etFullName, etPhoneNumber, etReview;
    Button btnProfileSubmit, btnLogout, btnFeedbackSubmit;
    String fullName, phoneNumber, rating, review, userID;
    RatingBar rbRating;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        vfSettings = findViewById(R.id.vfSettings);
        vfSettings.setDisplayedChild(0);
        setTitle(getString(R.string.settings_updateprofiletitle));

        etFullName = findViewById(R.id.etFullName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnProfileSubmit = findViewById(R.id.btnProfileSubmit);
        btnLogout = findViewById(R.id.btnLogout);
        etReview = findViewById(R.id.etReview);
        rbRating = findViewById(R.id.rbRating);
        btnFeedbackSubmit = findViewById(R.id.btnFeedbackSubmit);

        database = FirebaseDatabase.getInstance();
        userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        btnProfileSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                saveReview();
                Toast.makeText(SettingsActivity.this, getString(R.string.settings_feedbackmessage), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void saveReview() {
        ref = database.getReference(getString(R.string.settings_reviewstitle)).child(userID);
        rating = String.valueOf(rbRating.getRating());
        review = etReview.getText().toString();

        ref.child(getString(R.string.settings_ratingtitle)).setValue(rating);
        ref.child(getString(R.string.settings_feedbacktitle)).setValue(review);
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
                vfSettings.setDisplayedChild(0);
                return true;
            case R.id.appFeedback:
                setTitle(getString(R.string.settings_feedbacktitle));
                vfSettings.setDisplayedChild(1);
                return true;
            case R.id.aboutUs:
                setTitle(getString(R.string.settings_aboutustitle));
                vfSettings.setDisplayedChild(2);
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