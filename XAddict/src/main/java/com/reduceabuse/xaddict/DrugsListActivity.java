package com.reduceabuse.xaddict;

// Reduce Abuse

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class DrugsListActivity extends AppCompatActivity {

    RecyclerView rvDrugList;
    String[] drugName;
    final int[] drugImages = {R.drawable.cocaine, R.drawable.mdma, R.drawable.heroin, R.drawable.marijuana, R.drawable.meth, R.drawable.opioids};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_list);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rvDrugList = findViewById(R.id.rvDrugList);

        drugName = getResources().getStringArray(R.array.drugslist_drugname);

        AdapterDrugList adapterDrugList = new AdapterDrugList(DrugsListActivity.this, drugName, drugImages);
        rvDrugList.setAdapter(adapterDrugList);
        rvDrugList.setLayoutManager(new LinearLayoutManager(DrugsListActivity.this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DrugsListActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}