package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegistreMode extends AppCompatActivity
{
    private ImageButton back;
    private Button hospitalModeBtn, patientModeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre_mode);

        back = (ImageButton) findViewById(R.id.btn_modeback);
        hospitalModeBtn = (Button) findViewById(R.id.btn_resiterHostipal);
        patientModeBtn = (Button) findViewById(R.id.btn_resiterPatient);

        back.setOnClickListener(backAction);
        hospitalModeBtn.setOnClickListener(actionhostitalBtn);
        patientModeBtn.setOnClickListener(actionPatienBtn);
    }


    /**
     * Action whene we click on the button of registre as patient
     **/
    View.OnClickListener actionPatienBtn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegistreMode.this, SignupPublic.class);
            startActivity(intent);
            finish();
        }
    };

    /**
     * Action whene we click on the button of registre as hospital
     **/
    View.OnClickListener actionhostitalBtn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegistreMode.this, SignupDoctor.class);
            startActivity(intent);
        }
    };



    /**
     * if we click on the back button
     **/
    View.OnClickListener backAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegistreMode.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}