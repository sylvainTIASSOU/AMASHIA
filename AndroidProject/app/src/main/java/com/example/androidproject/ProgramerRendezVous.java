package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidproject.model.ChatModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProgramerRendezVous extends AppCompatActivity
{
    EditText dateEdit, timeEdit, motifEdit;
    Button valide;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programer_rendez_vous);

        dateEdit = (EditText) findViewById(R.id.date);
        timeEdit = (EditText) findViewById(R.id.time);
        motifEdit = (EditText) findViewById(R.id.motif);
        valide = (Button) findViewById(R.id.valide);


        valide.setOnClickListener(actionValide);

  //teste inedi
        database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //travaill
                for (DataSnapshot messageSnapshot: snapshot.child("chat").child("1").child("message").getChildren())
                {
                    //motifEdit.setText("mon message de paix de for");

                    if(messageSnapshot.hasChild("msg") && messageSnapshot.hasChild("mobile"))
                    {
                        String messageTimeStamps = messageSnapshot.getKey();
                        String getMobile = messageSnapshot.child("mobile").getValue(String.class);
                        String getMsg = messageSnapshot.child("msg").getValue(String.class);

                        Timestamp timestamp = new Timestamp(Long.parseLong(messageTimeStamps));
                        Date date = new Date(timestamp.getTime());

                        motifEdit.setText(" "+getMsg);



                    }


                    //motifEdit.setText("mon message de paix3");
                }

                //travail
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    View.OnClickListener    actionValide = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String date = dateEdit.getText().toString();
            String time = timeEdit.getText().toString();
            String motif = motifEdit.getText().toString();

            if (date.isEmpty() || time.isEmpty() || motif.isEmpty())
            {
                Toast.makeText(ProgramerRendezVous.this, "tous les champs sont requisent", Toast.LENGTH_SHORT).show();
            }
            else
            {

            }
        }
    };
}