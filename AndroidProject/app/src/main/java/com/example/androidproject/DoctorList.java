package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidproject.adapter.DoctorListAdapter;
import com.example.androidproject.adapter.PatientListAdapter;
import com.example.androidproject.interfaces.SelectItemDoctoList;
import com.example.androidproject.memoryData.MemoryData;
import com.example.androidproject.model.DoctorListModel;
import com.example.androidproject.model.PatientListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorList extends AppCompatActivity implements SelectItemDoctoList
{

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    List<DoctorListModel> doctorListModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.doctor_recycler);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.child("doctor").getChildren())
                {
                    String name = dataSnapshot.child("firstNameDoctor").getValue(String.class);
                    String hospitaname = dataSnapshot.child("hospitalName").getValue(String.class);
                    String service = dataSnapshot.child("service").getValue(String.class);

                    DoctorListModel doctorListModel = new DoctorListModel(hospitaname, service,name);

                    doctorListModelList.add(doctorListModel);
                }
                recyclerView.setAdapter(new DoctorListAdapter(getApplicationContext(), doctorListModelList, DoctorList.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onItemCliked(DoctorListModel doctorListModel)
    {
        String name = doctorListModel.getDoctorName();
        Intent intent = new Intent(getApplicationContext(), MenuPublic.class);
        startActivity(intent);
        intent.putExtra("nameDoctor", name);
        MemoryData.saveDoctorUser(name, getApplicationContext());
        finish();

    }
}