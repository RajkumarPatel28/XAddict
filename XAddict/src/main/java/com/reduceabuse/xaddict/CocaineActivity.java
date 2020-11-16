package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class CocaineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocaine);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.canada.ca/en/health-canada/services/substance-use/controlled-illegal-drugs/cocaine-crack.html"));
                startActivity(intent);
            }
        });
    }
}