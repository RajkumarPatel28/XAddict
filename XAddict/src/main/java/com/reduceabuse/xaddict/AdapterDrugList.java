package com.reduceabuse.xaddict;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public void onBindViewHolder(@NonNull ViewDrugList holder, final int position) {

       final int positvalue = position;
        holder.DrugNameText.setText(DataDrugName[position]);
        holder.DrugDescripitionText.setText(DataDrugDescripition[position]);
        holder.DrugImages.setImageResource(Images[position]);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (position)
                {
                    case 0:
                        intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        break;

                    case 2:
                        intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        break;

                    case 5:
                        intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        break;


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return DataDrugName.length;
    }

    public class ViewDrugList extends RecyclerView.ViewHolder{

        TextView DrugNameText, DrugDescripitionText;
        ImageView DrugImages;
        LinearLayout linearLayout;

        public ViewDrugList(@NonNull View itemView) {
            super(itemView);

            DrugNameText = itemView.findViewById(R.id.DrugNameText);
            DrugDescripitionText = itemView.findViewById(R.id.DrugDescripitionText);
            DrugImages = itemView.findViewById(R.id.DrugImages);
            linearLayout = itemView.findViewById(R.id.linear_layout);


        }



    }
}
