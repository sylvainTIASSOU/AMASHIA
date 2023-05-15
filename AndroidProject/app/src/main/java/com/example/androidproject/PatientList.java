package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidproject.adapter.MessageAdapter;
import com.example.androidproject.adapter.PatientListAdapter;
import com.example.androidproject.interfaces.SelectPatientListener;
import com.example.androidproject.model.ChatModel;
import com.example.androidproject.model.PatientListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientList extends AppCompatActivity implements SelectPatientListener
{
    private CardView listPatientCard;
    private String name, mobile;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);


        RecyclerView recyclerView = findViewById(R.id.recyclerView_list_patient);
        List<PatientListModel> patientLists = new ArrayList<PatientListModel>();

        /*patientLists.add(new PatientListModel("felicity", "comment tu vas"));
        patientLists.add(new PatientListModel("allen ari k.M", "comment tu vas"));
        patientLists.add(new PatientListModel("arison wells k.M", "comment tu vas"));
        patientLists.add(new PatientListModel("oliver keen k.M", "comment tu vas"));
        patientLists.add(new PatientListModel("ralph digni k.M", "comment tu vas"));
        patientLists.add(new PatientListModel("wali wels k.M", "comment tu vas"));
*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(new PatientListAdapter(getApplicationContext(), patientLists, this));

        //recuperation de la vue

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.child("patient").getChildren())
                {
                    String name = dataSnapshot.child("namePatient").getValue(String.class);
                    PatientListModel patientListModel = new PatientListModel(name, "comment tu vas?");
                    patientLists.add(patientListModel);
                }
                recyclerView.setAdapter(new PatientListAdapter(getApplicationContext(), patientLists, PatientList.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onItemClicked(PatientListModel liste)
    {
        String name = liste.getFullName();
        Intent intent = new Intent(getApplicationContext(), ChatPage.class);
        intent.putExtra("name", name);
        startActivity(intent);

    }
}