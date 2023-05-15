package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.androidproject.memoryData.MemoryData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity
{
    private ImageButton back;
    private Button loginbtn;
    private EditText mobileEdit,passEdi;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mobileEdit = (EditText)findViewById(R.id.edit_mobile);
        passEdi= (EditText)findViewById(R.id.edit_loginPassword);
        loginbtn = (Button) findViewById(R.id.btn_login1);
        back = (ImageButton) findViewById(R.id.btn_back);

        back.setOnClickListener(backAction);
        loginbtn.setOnClickListener(loginAction);

    }

    View.OnClickListener loginAction = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            //recuperation des donné
            String mobile = mobileEdit.getText().toString();
            String pass = passEdi.getText().toString();

            //on vérifi si les champs sont pas vide
            if(mobile.isEmpty() || pass.isEmpty())
            {
                Toast.makeText(Login.this, "Tous les champs sont obligatoir", Toast.LENGTH_LONG).show();
            }
            else
            {
               /* ProgressDialog prog = new ProgressDialog(getApplicationContext());
                prog.setCancelable(false);
                prog.setMessage("chargement...");
*/
                database.child("patient").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //on verifi si le mot de passe existe
                        if(snapshot.hasChild(mobile))
                        {
                            //recuperation deu mot de pass
                            //prog.show();
                            String getPass = snapshot.child(mobile).child("passwordpatient").getValue(String.class);
                            //on verifi si les mot de passe sont conforme
                           // prog.dismiss();
                            if(getPass.equals(pass))
                            {
                                mobileEdit.setText("");
                                passEdi.setText("");

                                if(MemoryData.getDoctorUser(getApplicationContext()).isEmpty())
                                {
                                    Intent intent = new Intent(Login.this, DoctorList.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Intent intent = new Intent(getApplicationContext(), MenuPublic.class);
                                    startActivity(intent);
                                    intent.putExtra("nameDoctor", MemoryData.getDoctorUser(getApplicationContext()));
                                    finish();
                                }


                            }
                            else
                                Toast.makeText(Login.this, "Mot de passe incorrecte", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Login.this, "le numero n'existe pas", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                       // prog.dismiss();
                    }
                });
            }

        }
    };


    View.OnClickListener backAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}