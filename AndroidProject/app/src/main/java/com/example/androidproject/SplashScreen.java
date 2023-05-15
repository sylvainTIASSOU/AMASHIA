package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.androidproject.memoryData.MemoryData;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //getSupportActionBar().hide();

        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }
   private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(3000);
            } catch (Exception e) {
            }

            if(!MemoryData.getMobile(getApplicationContext()).isEmpty() && MemoryData.getUser(getApplicationContext()).equals("public"))
            {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("mobile", MemoryData.getMobile(getApplicationContext()));
                intent.putExtra("name", MemoryData.getName(getApplicationContext()));
                startActivity(intent);
                finish();

            } else if (!MemoryData.getMobile(getApplicationContext()).isEmpty() && MemoryData.getUser(getApplicationContext()).equals("docteur"))
            {
                Intent intent = new Intent(getApplicationContext(), MenuDoctor.class);
                intent.putExtra("mobile", MemoryData.getMobile(getApplicationContext()));
                intent.putExtra("name", MemoryData.getName(getApplicationContext()));
                startActivity(intent);
                finish();

            }
            else {
                // Start main activity
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }


        }
    }


}