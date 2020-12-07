package com.reduceabuse.xaddict;

// Reduce Abuse

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterDrugList extends RecyclerView.Adapter<AdapterDrugList.ViewDrugList> {

    final String[] drugName;
    final int[] drugImg;
    final Context context;

    public AdapterDrugList(Context cont, String[] name, int[] img) {
        drugName = name;
        drugImg = img;
        context = cont;
    }

    public static class ViewDrugList extends RecyclerView.ViewHolder {

        final LinearLayout llList;
        final TextView tvDrugName;
        final ImageView ivDrug;

        public ViewDrugList(@NonNull View itemView) {
            super(itemView);

            llList = itemView.findViewById(R.id.llList);
            tvDrugName = itemView.findViewById(R.id.tvDrugName);
            ivDrug = itemView.findViewById(R.id.ivDrug);
        }
    }

    @NonNull
    @Override
    public ViewDrugList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.drug_list_row, parent, false);
        return new ViewDrugList(view);
    }

    @Override
    public int getItemCount() {
        return drugName.length;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDrugList holder, final int position) {
        holder.tvDrugName.setText(drugName[position]);
        holder.ivDrug.setImageResource(drugImg[position]);
        holder.llList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(context, CocaineActivity.class);
                        break;

                    case 1:
                        intent = new Intent(context, SyntheticsActivity.class);
                        break;

                    case 2:
                        intent = new Intent(context, HeroinActivity.class);
                        break;

                    case 3:
                        intent = new Intent(context, MarijuanaActivity.class);
                        break;

                    case 4:
                        intent = new Intent(context, MethActivity.class);
                        break;

                    default:
                        intent = new Intent(context, OpioidsActivity.class);
                        break;
                }
                context.startActivity(intent);
            }
        });
    }
}