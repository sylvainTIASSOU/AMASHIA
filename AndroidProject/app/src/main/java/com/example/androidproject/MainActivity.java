package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button btnLogin, btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_logon);

        btnRegister.setOnClickListener(registerAction);
        btnLogin.setOnClickListener(loginAction);
    }

    private View.OnClickListener registerAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, RegistreMode.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener loginAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    };
}