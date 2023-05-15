package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidproject.memoryData.MemoryData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupDoctor extends AppCompatActivity
{
    private Button signup;
    private EditText lastName, firstName, mobile, service, hospital, doctorEmail, pass, confirmPass;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_doctor);

        signup = (Button) findViewById(R.id.btnSignupDoctor);

        lastName = (EditText) findViewById(R.id.lastName);
        firstName = (EditText) findViewById(R.id.firstName);
        mobile = (EditText) findViewById(R.id.mobileDoctor);
        service = (EditText) findViewById(R.id.service);
        hospital = (EditText) findViewById(R.id.hospitalName);
        pass = (EditText) findViewById(R.id.pass);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        signup.setOnClickListener(actionSignup);
    }

    View.OnClickListener actionSignup = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            //recuperation des donnnées des champs
            String lName = lastName.getText().toString();
            String fName = firstName.getText().toString();
            String mobileDoctor = mobile.getText().toString();
            String serviceHospital = service.getText().toString();
            String hospitalName = hospital.getText().toString();
            String password = pass.getText().toString();
            String confirmPassword = confirmPass.getText().toString();


            //on verifie si tous les champs so n bient remplit
            if(lName.isEmpty() || fName.isEmpty() || mobileDoctor.isEmpty() || serviceHospital.isEmpty() || hospitalName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            {
                Toast.makeText(SignupDoctor.this, "All filds is required", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //on verifi si les passWord sont conforms
                if (password.equals(confirmPassword))
                {

                    ProgressDialog prog = new ProgressDialog(getApplicationContext());
                    prog.setCancelable(false);
                    prog.setMessage("chargement...");

                    //on enregistre les donnée
                    database.child("doctor").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(mobileDoctor))
                            {
                                Toast.makeText(SignupDoctor.this, "mobile number already exist", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                prog.show();
                                database.child("doctor").child(mobileDoctor).child("lastNameDoctor").setValue(lName);
                                database.child("doctor").child(mobileDoctor).child("firstNameDoctor").setValue(fName);
                                database.child("doctor").child(mobileDoctor).child("service").setValue(serviceHospital);
                                database.child("doctor").child(mobileDoctor).child("password").setValue(password);
                                database.child("doctor").child(mobileDoctor).child("hospitalName").setValue(hospitalName);


                                //save data
                                MemoryData.saveMobile(mobileDoctor, getApplicationContext());
                                MemoryData.saveName(fName, getApplicationContext());
                                MemoryData.saveUser("docteur", getApplicationContext());

                                database.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(!snapshot.hasChild(mobileDoctor))
                                        {
                                            database.child("user").child(mobileDoctor).child("name").setValue(fName);
                                            database.child("user").child(mobileDoctor).child("userMode").setValue(MemoryData.getUser(SignupDoctor.this));
                                            prog.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                        prog.dismiss();
                                    }
                                });
                                Toast.makeText(SignupDoctor.this, "hospital registre succefuly", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignupDoctor.this, MenuDoctor.class);
                                startActivity(intent);
                                intent.putExtra("mobile", mobileDoctor);
                                intent.putExtra("name", fName);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            prog.dismiss();
                        }
                    });

                    //ouvertur de l'intention suivant

                }
                else
                {
                    Toast.makeText(SignupDoctor.this, "Passwords is not conform", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}