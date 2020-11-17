package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Objects;

public class StatusActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    RadioButton rbYes, rbNo;
    SeekBar sbCrave, sbHungry, sbAngry, sbLonely, sbTired, sbAnxiety, sbPain, sbOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rbYes = findViewById(R.id.rbYes);
        rbNo = findViewById(R.id.rbNo);
        sbCrave = findViewById(R.id.sbCrave);
        sbHungry = findViewById(R.id.sbHungry);
        sbAngry = findViewById(R.id.sbAngry);
        sbLonely = findViewById(R.id.sbLonely);
        sbTired = findViewById(R.id.sbTired);
        sbAnxiety = findViewById(R.id.sbAnxiety);
        sbPain = findViewById(R.id.sbPain);
        sbOther = findViewById(R.id.sbOther);

        loadData();

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(StatusActivity.this, getString(R.string.status_savemessage), Toast.LENGTH_SHORT).show();
            }
        });

        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetData();
                Toast.makeText(StatusActivity.this, getString(R.string.status_resetmessage), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        rbYes.setChecked(sharedPreferences.getBoolean(getString(R.string.status_yesradio), false));
        rbNo.setChecked(sharedPreferences.getBoolean(getString(R.string.status_noradio), false));
        sbCrave.setProgress(sharedPreferences.getInt(getString(R.string.status_crave), 0));
        sbHungry.setProgress(sharedPreferences.getInt(getString(R.string.status_hungry), 0));
        sbAngry.setProgress(sharedPreferences.getInt(getString(R.string.status_angry), 0));
        sbLonely.setProgress(sharedPreferences.getInt(getString(R.string.status_lonely), 0));
        sbTired.setProgress(sharedPreferences.getInt(getString(R.string.status_tired), 0));
        sbAnxiety.setProgress(sharedPreferences.getInt(getString(R.string.status_anxiety), 0));
        sbPain.setProgress(sharedPreferences.getInt(getString(R.string.status_pain), 0));
        sbOther.setProgress(sharedPreferences.getInt(getString(R.string.status_other), 0));
    }

    public void saveData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.status_yesradio), rbYes.isChecked());
        editor.putBoolean(getString(R.string.status_noradio), rbNo.isChecked());
        editor.putInt(getString(R.string.status_crave), sbCrave.getProgress());
        editor.putInt(getString(R.string.status_hungry), sbHungry.getProgress());
        editor.putInt(getString(R.string.status_angry), sbAngry.getProgress());
        editor.putInt(getString(R.string.status_lonely), sbLonely.getProgress());
        editor.putInt(getString(R.string.status_tired), sbTired.getProgress());
        editor.putInt(getString(R.string.status_anxiety), sbAnxiety.getProgress());
        editor.putInt(getString(R.string.status_pain), sbPain.getProgress());
        editor.putInt(getString(R.string.status_other), sbOther.getProgress());
        editor.apply();
    }

    public void resetData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        loadData();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StatusActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}