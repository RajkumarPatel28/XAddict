package com.reduceabuse.xaddict;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Bundle;

public class DrugsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String DrugName[], DrugDescripition[];
    int Images [] ={R.drawable.cocaine, R.drawable.heroin, R.drawable.marijuana, R.drawable.mdma, R.drawable.meth, R.drawable.opioids};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_list);

        recyclerView = findViewById(R.id.recyclerView);

        DrugName = getResources().getStringArray(R.array.DrugsList_DrugName);
        DrugDescripition = getResources().getStringArray(R.array.DrugsList_DrugDescripition);

        AdapterDrugList AdapterDrugList = new AdapterDrugList(this,DrugName, DrugDescripition, Images);
        recyclerView.setAdapter(AdapterDrugList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}