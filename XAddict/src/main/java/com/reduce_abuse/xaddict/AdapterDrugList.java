package com.reduce_abuse.xaddict;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDrugList extends RecyclerView.Adapter<AdapterDrugList.ViewDrugList> {

    String DataDrugName[], DataDrugDescripition[];
    int Images[];
    Context context;

    public AdapterDrugList(Context cont, String DrugName[], String DrugDescripition[], int Img[]){

        DataDrugName = DrugName;
        DataDrugDescripition = DrugDescripition;
        Images = Img;
        context = cont;

    }

    @NonNull
    @Override
    public ViewDrugList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.druglistrow, parent, false);
        return new ViewDrugList(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewDrugList holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewDrugList extends RecyclerView.ViewHolder {

        TextView DrugNameText, DrugDescripitionText;
        ImageView DrugImages;

        public ViewDrugList(@NonNull View itemView) {
            super(itemView);
        }
    }
}