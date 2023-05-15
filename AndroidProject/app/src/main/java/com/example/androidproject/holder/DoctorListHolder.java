package com.example.androidproject.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

public class DoctorListHolder extends RecyclerView.ViewHolder
{
    public TextView hospitalName, doctorName, service;
    public CardView card;

    public DoctorListHolder(@NonNull View itemView) {

        super(itemView);
        hospitalName = itemView.findViewById(R.id.hospital_name);
        doctorName = itemView.findViewById(R.id.doctor_name);
        service = itemView.findViewById(R.id.hospital_service);
        card = itemView.findViewById(R.id.doctor_card);
    }
}
