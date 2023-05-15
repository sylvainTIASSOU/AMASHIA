package com.example.androidproject.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

public class PatientListHolder extends RecyclerView.ViewHolder
{
    public TextView lastMessage;
    public TextView fullName;
    public CardView cardView;

    public PatientListHolder(@NonNull View itemView) {
        super(itemView);

        lastMessage = itemView.findViewById(R.id.last_massage);
        fullName = itemView.findViewById(R.id.full_name);
        cardView = itemView.findViewById(R.id.liste_patient_card);
    }
}
