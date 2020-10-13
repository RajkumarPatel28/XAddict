package com.reduce_abuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DrugsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String DrugName[], DrugDescripition[];
    int Images [] ={R.drawable.cocaine, R.drawable.herion, R.drawable.marijuana, R.drawable.mdma, R.drawable.meth, R.drawable.opiodios};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_list);

        recyclerView = findViewById(R.id.recyclerView);

        DrugName = getResources().getStringArray(R.array.DrugsList_DrugName);
        DrugDescripition = getResources().getStringArray(R.array.DrugsList_DrugDescripition);

        AdapterDrugList AdapterDrugList = new AdapterDrugList(this,DrugName, DrugDescripition, Images);

    }
}