package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class HeroinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroin);
    }

    public void callIntent(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.heroinButton:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.drugabuse.gov/publications/drugfacts/heroin"));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}