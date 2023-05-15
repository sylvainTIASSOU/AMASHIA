package com.example.androidproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.ChatPage;
import com.example.androidproject.R;
import com.example.androidproject.holder.PatientListHolder;
import com.example.androidproject.interfaces.SelectPatientListener;
import com.example.androidproject.model.PatientListModel;

import java.util.ArrayList;
import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListHolder>
{
    Context context;
    List<PatientListModel> patientListModels;
    SelectPatientListener listener;

    public PatientListAdapter(Context context, List<PatientListModel> patientListModels, SelectPatientListener listener) {
        this.context = context;
        this.patientListModels = patientListModels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PatientListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PatientListHolder(LayoutInflater.from(context).inflate(R.layout.liste_patient, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientListHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.lastMessage.setText(patientListModels.get(position).getLastMesssage());
        holder.fullName.setText(patientListModels.get(position).getFullName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listener.onItemClicked(patientListModels.get(position));
            }
        });


        //
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatPage.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return patientListModels.size();
    }
}
