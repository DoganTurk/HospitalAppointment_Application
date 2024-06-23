package com.example.hospital_application;


import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder>{

    Context ctx;
    List<Entry> data;

    public EntryAdapter(Context ctx, List<Entry> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(ctx).inflate(R.layout.appointment_row_layout, parent, false);

        EntryViewHolder holder = new EntryViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {

        Entry entry = data.get(position);

        holder.txtDoctorName.setText(entry.getDoctor_name());
        holder.txtHospital.setText(entry.getHospital_name());
        holder.txtDate.setText(entry.getDate());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class EntryViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout row;

        TextView txtDoctorName;
        TextView txtHospital;
        TextView txtDate;


        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDoctorName = itemView.findViewById(R.id.txtDoctorName);
            txtHospital = itemView.findViewById(R.id.txtHospital);
            txtDate = itemView.findViewById(R.id.txtDate);
            //row = itemView.findViewById(R.id.row_list);
            row = (ConstraintLayout) itemView;
        }
    }

}

