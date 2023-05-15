package com.example.androidproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.holder.DoctorListHolder;
import com.example.androidproject.holder.PatientListHolder;
import com.example.androidproject.interfaces.SelectItemDoctoList;
import com.example.androidproject.model.DoctorListModel;

import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListHolder>
{
    Context context;
    List<DoctorListModel> listDoctor;
    SelectItemDoctoList selectItemDoctoList;

    public DoctorListAdapter(Context context, List<DoctorListModel> listDoctor, SelectItemDoctoList selectItemDoctoList) {
        this.context = context;
        this.listDoctor = listDoctor;
        this.selectItemDoctoList = selectItemDoctoList;
    }

    @NonNull
    @Override
    public DoctorListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoctorListHolder(LayoutInflater.from(context).inflate(R.layout.liste_doctor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorListHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.service.setText(listDoctor.get(position).getService());
        holder.hospitalName.setText(listDoctor.get(position).getHospitalName());
        holder.doctorName.setText(listDoctor.get(position).getDoctorName());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItemDoctoList.onItemCliked(listDoctor.get(position));

            }
        });


    }

    @Override
    public int getItemCount() {
        return listDoctor.size();
    }
}
