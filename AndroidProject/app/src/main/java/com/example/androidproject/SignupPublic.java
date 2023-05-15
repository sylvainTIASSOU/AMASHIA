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

public class SignupPublic extends AppCompatActivity
{
    //creation de l'instence de la base de donnée
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    EditText userName, email, mobile, pass, confirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_public);

        Button signup  = (Button) findViewById(R.id.btnSignupPublic);
        userName = (EditText) findViewById(R.id.userName);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        pass = (EditText) findViewById(R.id.password);
        confirmPass = (EditText) findViewById(R.id.confirm_password);


        signup.setOnClickListener(actionSignup);
    }

    /**
     * Action when we click on the button of signup
     **/
    View.OnClickListener actionSignup = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            String name = userName.getText().toString();
            String emailTxt = email.getText().toString();
            String mobileTxt = mobile.getText().toString();
            String password = pass.getText().toString();
            String confirm_pass = confirmPass.getText().toString();

            //on verifie si tous les champs sont bien remplit
            if(name.equals("") || emailTxt.equals("") || mobileTxt.equals("") || password.equals("") || confirm_pass.equals(""))
            {
                Toast.makeText(SignupPublic.this, "Touts les champs sont obligatoirs", Toast.LENGTH_SHORT).show();
            }
            //si les champs sont bien rempli
            else
            {
                //On verifie si les password sont conforme
                if (password.equals(confirm_pass))
                {
                    //on fait l'enregistrement dans la base




                    database.child("patient").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(mobileTxt))
                            {
                                Toast.makeText(SignupPublic.this, "user already existe", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                database.child("patient").child(mobileTxt).child("namepatient").setValue(name);
                                database.child("patient").child(mobileTxt).child("emailPatient").setValue(emailTxt);
                                //database.child("patient").child(mobileTxt).child("mobilePatient").setValue(mobileTxt);
                                database.child("patient").child(mobileTxt).child("passwordpatient").setValue(password);

                                Toast.makeText(SignupPublic.this, "user registre succefuly", Toast.LENGTH_SHORT).show();
                                //save data to memory
                                MemoryData.saveMobile(mobileTxt, getApplicationContext());
                                MemoryData.saveName(name, getApplicationContext());
                                MemoryData.saveUser("public", getApplicationContext());

                                database.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(!snapshot.hasChild(mobileTxt))
                                        {
                                            database.child("user").child(mobileTxt).child("name").setValue(name);
                                            database.child("user").child(mobileTxt).child("userMode").setValue(MemoryData.getUser(SignupPublic.this));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                userName.setText("");
                                email.setText("");
                                mobile.setText("");
                                pass.setText("");
                                confirmPass.setText("");
                                //on ouvre l'activiter suivant
                                Intent intent = new Intent(SignupPublic.this, Login.class);
                                startActivity(intent);
                                intent.putExtra("mobile", mobileTxt);
                                intent.putExtra("name", name);

                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(SignupPublic.this, "les mots de passes ne sont pas conforme", Toast.LENGTH_SHORT).show();
                }
            }



        }
    };
}