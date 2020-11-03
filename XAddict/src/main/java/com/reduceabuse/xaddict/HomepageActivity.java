package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomepageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button btnCall = findViewById(R.id.btnCall);

        ImageButton btnHome = findViewById(R.id.homeBtn);
        ImageButton btnDrugs = findViewById(R.id.drugsBtn);
        ImageButton btnNotes = findViewById(R.id.notesBtn);
        ImageButton btnSettings = findViewById(R.id.settingsBtn);

        btnCall.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnDrugs.setOnClickListener(this);
        btnNotes.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch(v.getId())
        {
            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:18006686868"));
                break;
            case R.id.homeBtn:
                intent = new Intent(HomepageActivity.this, HomepageActivity.class);
                break;
            case R.id.drugsBtn:
                intent = new Intent(HomepageActivity.this, DrugsListActivity.class);
                break;
            case R.id.notesBtn:
                intent = new Intent(HomepageActivity.this, PersonalNotesActivity.class);
                break;
            case R.id.settingsBtn:
                intent = new Intent(HomepageActivity.this, SettingsActivity.class);
                break;
        }
        startActivity(intent);
    }
}