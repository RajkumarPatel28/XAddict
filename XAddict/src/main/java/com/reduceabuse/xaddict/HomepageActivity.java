package com.reduceabuse.xaddict;

// Reduce Abuse

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class HomepageActivity extends AppCompatActivity {

    String userID;
    Intent intent = null;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Objects.requireNonNull(getSupportActionBar()).hide();

        database = FirebaseDatabase.getInstance();
        userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        Button btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnBar);
        bottomNavigationView.getMenu().setGroupCheckable(0, false, true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btnDrugs:
                        intent = new Intent(HomepageActivity.this, DrugsListActivity.class);
                        break;
                    case R.id.btnSchedule:
                        checkProfile();
                        break;
                    case R.id.btnStatus:
                        intent = new Intent(HomepageActivity.this, StatusActivity.class);
                        break;
                    default:
                        intent = new Intent(HomepageActivity.this, SettingsActivity.class);
                        finishActivity(0);
                        break;
                }
                if (intent != null)
                    startActivity(intent);
                return true;
            }
        });
    }

    public void phoneCall() {
        if (ContextCompat.checkSelfPermission(HomepageActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomepageActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse(getString(R.string.homepage_telephone)));
            startActivity(intent);
        }
    }

    public void checkProfile() {
        ref = database.getReference().child(getString(R.string.settings_profiletitle)).child(userID);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(getString(R.string.settings_phonenumberhint)).exists()) {
                    intent = new Intent(HomepageActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.llHomepage), getString(R.string.homepage_updateprofilemessage), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                phoneCall();

            else
                Toast.makeText(this, getString(R.string.homepage_permissiondeniedmessage), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(HomepageActivity.this, getString(R.string.homepage_message), Toast.LENGTH_SHORT).show();
    }
}